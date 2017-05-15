package com.example.hirayclay.bubbleview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by CJJ on 2017/5/12.
 */

public class ArrowDrawable extends Drawable {

    private ArrowAlign mArrowAlign;
    private final boolean mExtraCornerPadding;
    private float mArrowPosition;//arrow的起始位置
    private float mArrowAnglePostion;//arrow 尖距离arrow其实位置的距离
    private float mArrowWidth;//arrow宽度
    private float mArrowHeight;//arrow 高度
    //圆角半径
    private float mLtCorner;
    private float mRtCorner;
    private float mLbCorner;
    private float mRbCorner;
    private Paint mPaint;
    Rect rect;
    Path mPath;


    public ArrowDrawable(ArrowAlign mArrowAlign, boolean mExtraCornerPadding, float mArrowWidth, float mArrowHeight, float mArrowPosition,
                         float mArrowAnglePosition,
                         float ltCorner, float rtCorner, float lbCorner, float rbCorner,
                         int left, int top, int right, int bottom) {
        this.mArrowAlign = mArrowAlign;
        this.mExtraCornerPadding = mExtraCornerPadding;
        this.mArrowPosition = mArrowPosition;
        this.mArrowHeight = mArrowHeight;
        this.mArrowWidth = mArrowWidth;
        this.mArrowAnglePostion = mArrowAnglePosition;
        this.mLtCorner = ltCorner;
        this.mRtCorner = rtCorner;
        this.mLbCorner = lbCorner;
        this.mRbCorner = rbCorner;
        this.rect = new Rect(left, top, right, bottom);
        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.parseColor("#ff00ff"));
        mPaint.setStrokeWidth(2);

        //left to right matrix
        Matrix l2rMatrix = new Matrix();
        l2rMatrix.setValues(new float[]{-1, 0, rect.width(), 0, 1, 0, 0, 0,0});

        //top to bottom matrix
        Matrix t2bMatrix = new Matrix();
        t2bMatrix.setValues(new float[]{1, 0, 0, 0, -1, rect.height(), 0, 0, 0});

        switch (mArrowAlign) {
            default:
            case LEFT:
                mPath = generateLeftAlignPath();
                break;
            case RIGHT:
                mPath = generateLeftAlignPath();
                mPath.transform(l2rMatrix);
                break;
            case TOP:
                mPath = generateTopAlignPath();
                break;
            case BOTTOM:
                mPath = generateTopAlignPath();
                mPath.transform(t2bMatrix);
                break;
        }

    }

    private Path generateLeftAlignPath() {
        Path path;
        path = new Path();
        path.moveTo(rect.left + mArrowHeight, rect.top + mLtCorner);
        path.rQuadTo(0, -mLtCorner, mLtCorner, -mLtCorner);

        path.rLineTo(rect.width() - mArrowHeight - mLtCorner - mRtCorner, 0);
        path.rQuadTo(mRtCorner, 0, mRtCorner, mRtCorner);

        path.rLineTo(0, rect.height() - mRtCorner - mRbCorner);
        path.rQuadTo(0, mRbCorner, -mRbCorner, mRbCorner);

        path.rLineTo(-(rect.width() - mArrowHeight - mLbCorner - mRbCorner), 0);
        path.rQuadTo(-mLbCorner, 0, -mLbCorner, -mLbCorner);

        path.rLineTo(0, -(rect.height() - mArrowPosition - mArrowWidth - mLbCorner));
        path.rLineTo(-mArrowHeight, -(mArrowWidth - mArrowAnglePostion));
        path.rLineTo(mArrowHeight, -mArrowAnglePostion);

        float space = mArrowPosition - mLtCorner > 0 ? mArrowPosition - mLtCorner : 0;
        path.rLineTo(0, -space);
        path.close();
        return path;
    }

    /**
     * just do some easy work on {@link #generateLeftAlignPath} to write this method
     *
     * @return
     */
    private Path generateTopAlignPath() {
        Path path;
        path = new Path();
        path.moveTo(rect.left, rect.top + mArrowHeight + mLtCorner);
        path.rQuadTo(0, -mLtCorner, mLtCorner, -mLtCorner);

        float space = mArrowPosition - mLtCorner > 0 ? mArrowPosition - mLtCorner : 0;

        path.rLineTo(space, 0);
        path.rLineTo(mArrowAnglePostion, -mArrowHeight);
        path.rLineTo(mArrowWidth - mArrowAnglePostion, mArrowHeight);
        path.rLineTo(rect.width() - mRtCorner - mArrowPosition - mArrowWidth, 0);
        path.rQuadTo(mRtCorner, 0, mRtCorner, mRtCorner);

        path.rLineTo(0, rect.height() - mArrowHeight - mRtCorner - mRbCorner);
        path.rQuadTo(0, mRbCorner, -mRbCorner, mRbCorner);

        path.rLineTo(-(rect.width() - mLbCorner - mRbCorner), 0);
        path.rQuadTo(-mLbCorner, 0, -mLbCorner, -mLbCorner);

        path.rLineTo(0, -(rect.height() - mLbCorner - mLtCorner - mArrowHeight));
        path.close();
        return path;
    }

    @Override
    public void setBounds(@NonNull Rect bounds) {
        super.setBounds(rect);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (mPath != null && !mPath.isEmpty())
            canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getIntrinsicHeight() {
        return rect.height();
    }

    @Override
    public int getIntrinsicWidth() {
        return rect.width();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
