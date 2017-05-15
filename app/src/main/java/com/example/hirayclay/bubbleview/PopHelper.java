package com.example.hirayclay.bubbleview;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

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

    public PopHelper(Context context) {
        this.context = context;
    }

    /**
     * 显示在某一点
     *
     * @param x
     * @param y
     */
    public void showAtPosition(Bubble bubble, int x, int y) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        FrameLayout parent = new FrameLayout(context);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        parent.setBackgroundColor(Color.argb(100, 256, 256, 256));
        parent.setId(parentId);
        bubble.setId(bubbleId);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(bubble.getWidth(), bubble.getHeight());
        //default top align
        lp.leftMargin = (int) (x - bubble.getArrowPosition() - bubble.getArrowAnglePosition());
        lp.topMargin = y;
        bubble.setLayoutParams(lp);
        parent.addView(bubble);
        parent.setOnClickListener(this);
        bubble.setOnClickListener(this);
        wm.addView(parent, layoutParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case parentId:
                Log.i(TAG, "onClick: Parent");
                break;
            case bubbleId:
                Log.i(TAG, "onClick: Parent");
                break;
        }
    }
}
