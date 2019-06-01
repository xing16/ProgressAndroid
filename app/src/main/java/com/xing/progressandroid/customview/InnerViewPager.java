package com.xing.progressandroid.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class InnerViewPager extends ViewPager {

    private float downX;

    public InnerViewPager(@NonNull Context context) {
        super(context);
    }

    public InnerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                getParent().requestDisallowInterceptTouchEvent(true);
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                int currentItem = getCurrentItem();
                float deltaX = moveX - downX;
                // 右滑内部 viewpager 第一页
                if (currentItem == 0 && deltaX > 0) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    break;
                } else if (currentItem == getAdapter().getCount() - 1 && deltaX < 0) {  // 左滑内部 viewpager 最后一页
                    getParent().requestDisallowInterceptTouchEvent(false);
                    break;
                }
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
