package com.xing.progressandroid.layoutmanager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义 LinearLayoutManager 实现线性布局
 */
public class CustomLinearLayoutManager extends RecyclerView.LayoutManager {

    private static final String TAG = "CustomLayoutManager";

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
        int offsetY = 0;
        int itemCount = getItemCount();
        Log.e(TAG, "onLayoutChildren: itemCount = " + itemCount);
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            // 测量子 item 宽高，包括 ItemDecoration + margin
            measureChildWithMargins(view, 0, 0);
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            layoutDecorated(view, 0, offsetY, width, offsetY + height);
            offsetY += height;
        }
        // 如果所有 item 高度之和没有填充 recyclerview 高度，则取 recyclerview 设置的高度
        totalHeight = Math.max(offsetY, getHeightExcludePadding());
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
     * 竖直方向能滚动
     *
     * @return
     */
    @Override
    public boolean canScrollVertically() {
        return true;
    }


    /**
     * 记录向下滑动距离和向上滑动距离的和
     * 等于 0 时，表示回到了原点位置，
     */
    private int sumDy = 0;


    /**
     * @param dy       表示手指在屏幕上滑动的位移， dy > 0 向上滑动， dy < 0 向下滑动
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.e(TAG, "scrollVerticallyBy: dy = " + dy);
        /**
         * 向上滑动： dy > 0
         * 向下滑动： dy < 0
         */
        int distance = dy;
        // 判断到顶了
        if (sumDy + distance < 0) {
            distance = -sumDy;
        } else if (sumDy + dy > totalHeight - getHeightExcludePadding()) {   // 判断到底了
            distance = totalHeight - getHeightExcludePadding() - sumDy;
        }
        sumDy += distance;
        Log.e(TAG, "scrollVerticallyBy: sumDy = " + sumDy);
        // 移动子 item
        offsetChildrenVertical(-distance);
        return dy;
    }
}
