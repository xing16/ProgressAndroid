package com.xing.progressandroid.customview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class OutterViewPager extends ViewPager {
    public OutterViewPager(@NonNull Context context) {
        super(context);
    }

    public OutterViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 父容器拦截除 Action_down 外的事件，因为 Action_down 拦截了，后序的事件序列就都分发给父容器了，子 View 不能获取到事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            super.onInterceptTouchEvent(ev);
            return false;
        }
        return true;
    }
}
