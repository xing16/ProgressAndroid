package com.xing.progressandroid.customview.flowlayout;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 流式布局，适配器
 */
public abstract class FlowAdapter<T> {

    private List<T> dataList;

    public FlowAdapter(List<T> list) {
        this.dataList = list;
    }

    public FlowAdapter(T[] list) {
        this.dataList = new ArrayList<>(Arrays.asList(list));
    }

    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public T getItem(int position) {
        return dataList.get(position);
    }

    public abstract View getView(int position, T data, ViewGroup parent);
}
