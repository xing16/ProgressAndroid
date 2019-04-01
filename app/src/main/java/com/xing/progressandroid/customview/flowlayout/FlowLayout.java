package com.xing.progressandroid.customview.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义 ViewGroup， 实现流式布局
 */
public class FlowLayout extends ViewGroup {

    private int horizontalMargin = 20;
    private int verticalMargin = 20;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();


        int width = 0;
        int height = 0;
        int lineWidth = 0;
        int lineHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == View.GONE) {
                continue;
            }
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            // 测量子控件
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            int childViewWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childViewHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            int availableWidth = widthSize - paddingLeft - paddingRight - lineWidth;
            if (availableWidth >= childViewWidth) {   // 不换行
                lineWidth += (childViewWidth + horizontalMargin);
                width = Math.max(width, lineWidth);
                lineHeight = Math.max(lineHeight, childViewHeight);
            } else {   // 换行
                lineWidth = childViewWidth + horizontalMargin;
                width = Math.max(width, lineWidth);
                height += lineHeight + verticalMargin;
                lineHeight = childViewHeight;
            }
        }
        int finalWidth = (widthMode == MeasureSpec.EXACTLY) ? widthSize : paddingLeft + paddingRight + width  - horizontalMargin;
        int finalHeight = (heightMode == MeasureSpec.EXACTLY) ? heightSize : lineHeight + height + paddingTop + paddingBottom;
        setMeasuredDimension(finalWidth, finalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int lineWidth = 0;
        int lineHeight = 0;
        int height = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == View.GONE) {
                continue;
            }
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childViewWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childViewHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            int availableWidth = getWidth() - paddingLeft - paddingRight - lineWidth;
            if (availableWidth >= childViewWidth) {   // 不换行
                childView.layout(lineWidth, height, lineWidth + childViewWidth, height + childViewHeight);
                lineWidth += horizontalMargin + childViewWidth;
                lineHeight = Math.max(lineHeight, childViewHeight);
            } else {   // 换行
                height += lineHeight + verticalMargin;
                childView.layout(0, height, childViewWidth, height + childViewHeight);
                lineWidth = childViewWidth + horizontalMargin;
            }

        }
    }


    /**
     * 对外提供设置适配器，动态添加 view 至该 ViewGroup
     *
     * @param adapter
     */
    public void setAdapter(FlowAdapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("adapter can't not null");
        }
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, this);
            addView(view);
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }


}
