package com.xing.progressandroid.customview;

import android.view.View;
import android.view.ViewGroup;

public class ViewWrapper {

    private View view;
    private int width;

    public ViewWrapper(View view) {
        this.view = view;
    }

    public void setWidth(int width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
    }

    public int getWidth() {
        return view.getLayoutParams().width;
    }
}
