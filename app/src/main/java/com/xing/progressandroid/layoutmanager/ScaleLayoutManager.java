package com.xing.progressandroid.layoutmanager;

import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义 LinearLayoutManager 实现线性布局
 */
public class ScaleLayoutManager extends RecyclerView.LayoutManager {

    private static final String TAG = "CustomLinearLayoutManager";
    private LinearSnapHelper linearSnapHelper;

    public ScaleLayoutManager() {
        this.linearSnapHelper = new LinearSnapHelper();

    }


    /**
     * 设置 RecyclerView 中的 item 的 LayoutParams
     *
     * @return
     */
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    /**
     * 记录所有 item 的高度
     */
    private int totalHeight = 0;

    /**
     * 确定子 item 的位置
     *
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        int offsetX = 0;
        float scaleY = 1.0f;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            scaleY += 0.2f;
            view.setScaleY(scaleY);
            addView(view);
            // 测量子 item 宽高，包括 ItemDecoration + margin
            measureChildWithMargins(view, 0, 0);
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            layoutDecorated(view, offsetX, 0, offsetX + width, height);
            offsetX += width;
        }
    }

    /**
     * 得到 Recyclerview 除去 paddingtop，paddingbottom 后的高度
     *
     * @return
     */
    private int getHeightExcludePadding() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }

    /**
     * 水平方向能滚动
     *
     * @return
     */
    @Override
    public boolean canScrollHorizontally() {
        return true;
    }
}
