package com.xing.progressandroid.utils;

import android.content.Context;
import android.util.TypedValue;

public class DensityUtil {
    private DensityUtil() {
        throw new UnsupportedOperationException("can't be instance");
    }

    public static int dp2Px(Context context, int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

}
