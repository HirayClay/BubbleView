package com.viewkers.hiray;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by CJJ on 2017/5/15.
 */

public class PopHelper implements View.OnClickListener {
    private static final String TAG = "PopHelper";
    Context context;

    @IdRes
    private static final int parentId = 10;
    @IdRes
    private static final int bubbleId = 11;
    private WindowManager wm;
    private FrameLayout parent;
    private int statusBarHeight;

    public PopHelper(Context context) {
        this.context = context;
    }

    /**
     * 显示在某一点
     *
     * @param bubble ...
     * @param x      ..
     * @param y      ..
     */
    public void showAtPoint(BubbleView bubble, int x, int y) {
        wm = ((Activity) context).getWindowManager();

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        parent = new FrameLayout(context);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.format = PixelFormat.TRANSPARENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        layoutParams.gravity = Gravity.START | Gravity.TOP;
        layoutParams.x = -statusBarHeight;
        layoutParams.y = 0;
//        parent.setBackgroundColor(Color.argb(100, 256, 256, 256));
        parent.setId(parentId);
        bubble.setId(bubbleId);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        //default top align
        lp.leftMargin = (int) (x - bubble.getArrowPosition() - bubble.getArrowAnglePosition());
        lp.topMargin = y - statusBarHeight;
        lp.gravity = Gravity.START | Gravity.TOP;
        parent.addView(bubble, lp);

        parent.setOnClickListener(this);
        bubble.setOnClickListener(this);
        wm.addView(parent, layoutParams);
        parent.setFocusableInTouchMode(true);
        parent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    public void showAtView(BubbleView bubble, View view) {
        int[] xy = new int[2];
        view.getLocationInWindow(xy);
        int x = xy[0], y;
        switch (bubble.getArrowAlign()) {
            case TOP:

            case BOTTOM:
                x += (int) (bubble.getArrowAnglePosition() + bubble.getArrowPosition());
                break;
        }
        y = xy[1] + view.getHeight();
        showAtPoint(bubble, x, y);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case parentId:
                Log.i(TAG, "onClick: Parent");
                wm.removeView(parent);
                break;
            case bubbleId:
                Log.i(TAG, "onClick: Parent");
                break;
        }
    }

    public void dismiss() {
        if (parent != null && parent.getParent() != null)
            wm.removeView(parent);
    }
}
