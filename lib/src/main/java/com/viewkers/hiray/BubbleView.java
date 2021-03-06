package com.viewkers.hiray;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;


/**
 * Created by hiray on 2017/5/12 mm
 * 继承FrameLayout是比较好的选择，只用关心直接子节点的大小即可，设置额外的Padding为ArrowDrawable留出绘制
 * 空间
 */

public class BubbleView extends FrameLayout {

    private static final String TAG = "Bubble";
    private boolean mIsFloating;//是否作为悬浮窗口
    private boolean mCornerPadding;//是否使用圆角corner值作为padding，使得内容距离圆角有一定的间隔
    private float mExtraCornerRatio;
    private Alignment mAlignment;
    private float mArrowPosition;//arrow的起始位置
    private float mArrowAnglePosition;//arrow 尖距离arrow起始位置的距离
    private float mArrowWidth;//arrow宽度
    private float mArrowHeight;//arrow 高度

    private int mColor;

    //圆角半径
    private float mLtCorner;
    private float mRtCorner;
    private float mLbCorner;
    private float mRbCorner;

    //the drawable which draw self into a arrow also as "background"
    ArrowDrawable mArrowDrawable;

    public BubbleView(Context context) {
        super(context);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BubbleView);
        mIsFloating = array.getBoolean(R.styleable.BubbleView_is_floating, false);
        mColor = array.getColor(R.styleable.BubbleView_bubble_color, Color.GREEN);
        mLtCorner = array.getDimensionPixelSize(R.styleable.BubbleView_left_top_corner, 0);
        mRtCorner = array.getDimensionPixelSize(R.styleable.BubbleView_right_top_corner, 0);
        mLbCorner = array.getDimensionPixelSize(R.styleable.BubbleView_left_bottom_corner, 0);
        mRbCorner = array.getDimensionPixelSize(R.styleable.BubbleView_right_bottom_corner, 0);
        mArrowHeight = array.getDimensionPixelSize(R.styleable.BubbleView_arrow_height, 0);
        mArrowPosition = array.getDimensionPixelSize(R.styleable.BubbleView_arrow_start_position, 0);
        mArrowAnglePosition = array.getDimensionPixelSize(R.styleable.BubbleView_arrow_angle_position, 0);
        mArrowWidth = array.getDimensionPixelSize(R.styleable.BubbleView_arrow_width, 0);
        mAlignment = Alignment.parseInt(array.getInteger(R.styleable.BubbleView_arrow_direction, 0));
        mCornerPadding = array.getBoolean(R.styleable.BubbleView_extra_corner_padding, false);
        mExtraCornerRatio = array.getFloat(R.styleable.BubbleView_extra_corner_ratio, 0f);
        array.recycle();
        if (BuildConfig.DEBUG)
            Log.i(TAG,
                    "Bubble: mIsFloating:" + mIsFloating + "\n"
                            + "mCornerPadding" + mCornerPadding + "\n"
                            + "mAlignment:" + mAlignment + "\n"
                            + "LtCorner:" + mLtCorner + "\n"
                            + "mRtCorner:" + mRtCorner + "\n"
                            + "mRbCorner" + mRbCorner + "\n"
                            + "mArrowHeight" + mArrowHeight + "\n"
                            + "mArrowPosition：" + mArrowPosition + "\n"
                            + "mArrowWidth:" + mArrowWidth);
        setPadding();
    }

    private void setPadding() {
        int left = getPaddingLeft();
        int right = getPaddingRight();
        int top = getPaddingTop();
        int bottom = getPaddingBottom();

        int extraLeft = mCornerPadding ? (int) (mExtraCornerRatio * Math.max(mLbCorner, mLtCorner)) : 0;
        int extraTop = mCornerPadding ? (int) (mExtraCornerRatio * Math.max(mLtCorner, mRtCorner)) : 0;
        int extraRight = mCornerPadding ? (int) (mExtraCornerRatio * Math.max(mRtCorner, mRbCorner)) : 0;
        int extraBottom = mCornerPadding ? (int) (mExtraCornerRatio * Math.max(mLbCorner, mRbCorner)) : 0;
        switch (mAlignment) {
            case LEFT:
                left += mArrowHeight;
                break;
            case RIGHT:
                right += mArrowHeight;
                break;
            case TOP:
                top += mArrowHeight;
                break;
            case BOTTOM:
                bottom += mArrowHeight;
                break;
        }
        setPadding(left + extraLeft, top + extraTop, right + extraRight, bottom + extraBottom);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mArrowDrawable == null || changed)
            mArrowDrawable = new ArrowDrawable(mAlignment, mColor, mArrowWidth, mArrowHeight,
                    mArrowPosition, mArrowAnglePosition, mLtCorner, mRtCorner, mLbCorner, mRbCorner,
                    0, 0, getWidth(), getHeight());
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        //draw custom background first
        if (mArrowDrawable != null)
            mArrowDrawable.draw(canvas);
        super.dispatchDraw(canvas);

    }

    public void setColor(int mColor) {
        this.mColor = mColor;
        requestLayout();
    }

    public void setArrowAlign(Alignment mArrowAlign) {
        this.mAlignment = mArrowAlign;
        requestLayout();

    }

    public void setArrowHeight(float mArrowHeight) {
        this.mArrowHeight = mArrowHeight;
        requestLayout();
    }

    public void setArrowPosition(float mArrowPosition) {
        this.mArrowPosition = mArrowPosition;
        requestLayout();
    }

    public void setArrowWidth(float mArrowWidth) {
        this.mArrowWidth = mArrowWidth;
        requestLayout();
    }

    public void setArrowAnglePosition(float mArrowAnglePosition) {
        this.mArrowAnglePosition = mArrowAnglePosition;
        requestLayout();
    }

    public Alignment getArrowAlign() {
        return mAlignment;
    }

    public float getArrowHeight() {
        return mArrowHeight;
    }

    public float getArrowPosition() {
        return mArrowPosition;
    }

    public float getArrowAnglePosition() {
        return mArrowAnglePosition;
    }
}
