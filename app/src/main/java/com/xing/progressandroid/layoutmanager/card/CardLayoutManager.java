package com.xing.progressandroid.layoutmanager.card;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CardLayoutManager extends RecyclerView.LayoutManager {

    private static final String TAG = "CardLayoutManager";

    private CardLayoutManagerSettings settings;

    public CardLayoutManager(CardLayoutManagerSettings settings) {
        this.settings = settings;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (getItemCount() == 0 || state.isPreLayout()) {
            return;
        }
        //将当前Recycler中的view全部移除并放到报废缓存里,之后优先重用缓存里的view
        detachAndScrapAttachedViews(recycler);
        for (int i = getItemCount() - 1; i >= 0; i--) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int decoratedWidth = getDecoratedMeasuredWidth(view);
            int decoratedHeight = getDecoratedMeasuredHeight(view);
            int left = (getWidth() - getPaddingLeft() - getPaddingRight() - decoratedWidth) >> 1;
            int top = (getHeight() - getPaddingTop() - getPaddingBottom() - decoratedHeight) >> 1;
            layoutDecorated(view, left, top, left + decoratedWidth, top + decoratedHeight);
            if (i < settings.getMaxVisibleCount()) {
                view.setTranslationY(i * settings.getTranslationY());
                view.setScaleX(1 - i * settings.getScale());
                view.setScaleY(1 - i * settings.getScale());
            } else {
                view.setScaleX(1 - settings.getMaxVisibleCount() * settings.getScale());
                view.setScaleY(1 - settings.getMaxVisibleCount() * settings.getScale());
                view.setTranslationY((settings.getMaxVisibleCount() - 1) * settings.getTranslationY());
            }
        }
    }
}
