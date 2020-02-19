package com.xing.library.recommend;

import android.content.Context;

public class Resources {

    private static Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public static int getLayout(String name) {
        return context.getResources().getIdentifier(name, "layout", context.getPackageName());
    }


    public static int getId(String name) {
        return context.getResources().getIdentifier(name, "id", context.getPackageName());
    }


    public static int getString(String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }


    public static int getDrawable(String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public static int getStyle(String name) {
        return context.getResources().getIdentifier(name, "style", context.getPackageName());
    }


}
