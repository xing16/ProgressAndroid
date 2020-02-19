package com.xing.progressandroid.layoutmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayoutManager extends RecyclerView.LayoutManager {

    private Context context;
    private int horizontalMargin;
    private int verticalMargin;
    private int height;

    public FlowLayoutManager(Context context) {
        this.context = context;
        horizontalMargin = dp2Px(10);
        verticalMargin = dp2Px(10);
        setAutoMeasureEnabled(true);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

//    @Override
//    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
//        super.onMeasure(recycler, state, widthSpec, heightSpec);
//        int itemCount = getItemCount();
//        if (itemCount == 0 || state.isPreLayout()) {
//            return;
//        }
//        int widthMode = View.MeasureSpec.getMode(widthSpec);
//        int widthSize = View.MeasureSpec.getSize(widthSpec);
//        int heightMode = View.MeasureSpec.getMode(heightSpec);
//        int heightSize = View.MeasureSpec.getSize(heightSpec);
//        detachAndScrapAttachedViews(recycler);
//        int paddingLeft = getPaddingLeft();
//        int paddingRight = getPaddingRight();
//        int paddingTop = getPaddingTop();
//        int paddingBottom = getPaddingBottom();
//
//        int width = 0;
//        int height = 0;
//        int lineWidth = 0;
//        int lineHeight = 0;
//        for (int i = 0; i < itemCount; i++) {
//            View childView = recycler.getViewForPosition(i);
//            if (childView.getVisibility() == View.GONE) {
//                continue;
//            }
////            addView(childView);
//            measureChild(childView, widthSpec, heightSpec);
//            int childWidth = getDecoratedMeasuredWidth(childView);
//            int childHeight = childView.getMeasuredHeight();
//            int availableWidth = widthSize - paddingLeft - paddingRight - lineWidth;
//
//            if (availableWidth >= childWidth) {   // 不换行
//                lineWidth += (childWidth + horizontalMargin);
//                width = Math.max(width, lineWidth);
//                lineHeight = Math.max(lineHeight, childHeight);
//            } else {   // 换行
//                height += lineHeight + verticalMargin;
//                lineWidth = childWidth + horizontalMargin;
//                width = Math.max(width, lineWidth);
//                lineHeight = childHeight;
//            }
//        }
//        int finalWidth = (widthMode == View.MeasureSpec.EXACTLY) ? widthSize : paddingLeft + paddingRight + width - horizontalMargin;
//        int finalHeight = (heightMode == View.MeasureSpec.EXACTLY) ? heightSize : lineHeight + height + paddingTop + paddingBottom;
//        setMeasuredDimension(finalWidth, 700);
//    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        Log.e("deb", "onLayoutChildren: ");
        int itemCount = getItemCount();
        if (itemCount == 0 || state.isPreLayout()) {
            return;
        }
        detachAndScrapAttachedViews(recycler);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();

        int width = 0;
        height = 0;
        int lineWidth = paddingLeft;
        int lineHeight = 0;
        for (int i = 0; i < itemCount; i++) {
            View childView = recycler.getViewForPosition(i);
            if (childView.getVisibility() == View.GONE) {
                continue;
            }
            addView(childView);
            measureChildWithMargins(childView, 0, 0);
            int childWidth = getDecoratedMeasuredWidth(childView);
            int childHeight = getDecoratedMeasuredHeight(childView);
            int availableWidth = getWidth() - paddingLeft - paddingRight - lineWidth;
            if (availableWidth >= childWidth) {   // 不换行
                layoutDecorated(childView, lineWidth, height, lineWidth + childWidth, height + childHeight);
                lineWidth += (childWidth + horizontalMargin);
                lineHeight = Math.max(lineHeight, childHeight);
            } else {   // 换行
                height += lineHeight + verticalMargin;
                layoutDecorated(childView, paddingLeft, paddingTop + height, paddingLeft + childWidth, paddingTop + height + childHeight);
                lineWidth = childWidth + horizontalMargin;
                lineHeight = childHeight;
            }
        }
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    private int totalOffset = 0;

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.e("deb", "scrollVerticallyBy: ");
        int distance = dy;
        if (totalOffset + distance <= 0) {
            distance = -totalOffset;
        } else if (totalOffset + dy > height - getHeight()) {   // 判断到底了
            distance = height - getHeight() - totalOffset;
        }
        totalOffset += distance;
        offsetChildrenVertical(-distance);
        return dy;
    }

    private int dp2Px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
