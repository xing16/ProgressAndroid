package com.xing.progressandroid.customview.flowlayout;

import android.view.View;
import android.view.ViewGroup;

/**
 * 流式布局，适配器
 */
public abstract class FlowAdapter {

    public abstract int getCount();

    public abstract View getView(int position, ViewGroup parent);
}
