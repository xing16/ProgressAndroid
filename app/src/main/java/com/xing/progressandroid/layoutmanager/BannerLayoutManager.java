package com.xing.progressandroid.layoutmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import java.lang.ref.WeakReference;

public class BannerLayoutManager extends LinearLayoutManager {

    private float smoothTime = 150f;
    private PagerSnapHelper pagerSnapHelper;
    private RecyclerView recyclerView;
    private LoopHandler handler;
    private int currentPosition;
    private int realCount;

    public BannerLayoutManager(Context context, RecyclerView recyclerView, int realCount) {
        super(context);
        this.pagerSnapHelper = new PagerSnapHelper();
        this.recyclerView = recyclerView;
        this.realCount = realCount;
        handler = new LoopHandler(this);
        handler.setSendMessageEnable(true);
        setOrientation(HORIZONTAL);
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        pagerSnapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        Message msg = Message.obtain();
        msg.what = currentPosition + 1;
        handler.sendMessageDelayed(msg, 1000);
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            if (pagerSnapHelper != null) {
                View view = pagerSnapHelper.findSnapView(this);
                currentPosition = getPosition(view);
                if (onBannerSelectedListener != null) {
                    onBannerSelectedListener.onBannerSelected(view, currentPosition % realCount);
                }
                Message message = Message.obtain();
                currentPosition++;
                message.what = currentPosition;
                handler.setSendMessageEnable(true);
                handler.sendMessageDelayed(message, 1000);
                if (currentPosition == getItemCount() - 1) {
                    currentPosition = 0;
                }
            }
        } else if (state == RecyclerView.SCROLL_STATE_DRAGGING) {
            handler.setSendMessageEnable(false);
        }
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        super.smoothScrollToPosition(recyclerView, state, position);
        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return smoothTime / displayMetrics.densityDpi;
            }
        };
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }


    public interface OnBannerSelectedListener {
        void onBannerSelected(View view, int position);
    }

    private OnBannerSelectedListener onBannerSelectedListener;

    public void setOnBannerSelectedListener(OnBannerSelectedListener listener) {
        this.onBannerSelectedListener = listener;
    }

    static class LoopHandler extends Handler {

        private WeakReference<BannerLayoutManager> weakReference;
        private boolean enable;

        public LoopHandler(BannerLayoutManager bannerLayoutManager) {
            weakReference = new WeakReference<>(bannerLayoutManager);
        }

        public void setSendMessageEnable(boolean enable) {
            this.enable = enable;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null && enable) {
                int position = msg.what;
                BannerLayoutManager bannerLayoutManager = weakReference.get();
                if (bannerLayoutManager != null) {
                    if (position == bannerLayoutManager.getItemCount() - 1) {
                        bannerLayoutManager.currentPosition = 0;
                    }
                    bannerLayoutManager.recyclerView.smoothScrollToPosition(position);
                }
            }
        }
    }
}
