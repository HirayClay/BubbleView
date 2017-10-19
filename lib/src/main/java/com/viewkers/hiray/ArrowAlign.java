package com.viewkers.hiray;

import android.support.annotation.IntRange;

/**
 * Created by CJJ on 2017/5/12.mm
 */

public enum ArrowAlign {
    LEFT,
    TOP,
    RIGHT,
    BOTTOM;

    public static ArrowAlign parseInt(@IntRange(from = 0,to = 3) int valueIndex) {
        if (valueIndex >= 0 && valueIndex <= 3)
            return ArrowAlign.values()[valueIndex];
        else return BOTTOM;
    }
}
