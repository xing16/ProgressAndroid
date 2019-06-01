package com.xing.progressandroid.customview;

import android.content.Context;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MyScrollView extends ScrollView {

    private static final String TAG = "MyScrollView";
    private ListView listView;
    private TextView textView;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListView(ListView listView, TextView textView) {
        this.listView = listView;
        this.textView = textView;
    }


    private int mLastXIntercept;
    private int mLastYIntercept;


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                super.onInterceptTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                //横坐标位移增量
                int deltaX = x - mLastXIntercept;
                //纵坐标位移增量
                int deltaY = y - mLastYIntercept;
                // 下拉，
                if (textView.getBottom() <= 0 && deltaY > 0) {
                    Log.e(TAG, "onInterceptTouchEvent: 11111");
                    intercept = true;
                } else if (listView.getFirstVisiblePosition() == 0 && deltaY < 0) {    // 上拉
                    Log.e(TAG, "onInterceptTouchEvent:22222 ");
                    intercept = true;
                }

                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
        }
        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercept;
    }
}
