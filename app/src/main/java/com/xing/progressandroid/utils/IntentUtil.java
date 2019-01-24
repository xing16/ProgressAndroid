package com.xing.progressandroid.utils;

import android.content.Context;
import android.content.Intent;

public class IntentUtil {
    private IntentUtil() {
        throw new UnsupportedOperationException("can't be instance");
    }

    public static void startActivity(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
