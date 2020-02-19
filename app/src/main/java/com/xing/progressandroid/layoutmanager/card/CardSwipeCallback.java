package com.xing.progressandroid.layoutmanager.card;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.RecyclerAdapter;

import java.util.List;

public class CardSwipeCallback<T> extends ItemTouchHelper.Callback {

    private static final String TAG = "CardSwipeCallback";
    private CardLayoutManagerSettings settings;
    private List<T> dataList;
    private RecyclerAdapter adapter;

    public CardSwipeCallback(List<T> list, RecyclerAdapter adapter, CardLayoutManagerSettings settings) {
        this.dataList = list;
        this.adapter = adapter;
        this.settings = settings;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof CardLayoutManager) {
            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    /**
     * public static final int UP = 1;
     * public static final int DOWN = 2;
     * public static final int LEFT = 4;
     * public static final int RIGHT = 8;
     *
     * @param viewHolder
     * @param i          滑动方向
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        dataList.remove(0);
        adapter.notifyDataSetChanged();
        if (onCardSwipeListener != null) {
            onCardSwipeListener.onCardSwipe();
        }
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        // 滑动距离
        float distance = Math.max(Math.abs(dX), Math.abs(dY));
        float threshold = getThreshold(recyclerView, viewHolder);
        // 滑动比例
        float ratio = distance / threshold;
        if (ratio > 1.0f) {
            ratio = 1.0f;
        }
        Log.e(TAG, "onChildDraw: ratio " + ratio);
        int itemCount = recyclerView.getLayoutManager().getChildCount();
        Log.e("111111", "onChildDraw: " + itemCount);

        /**
         * 因为 CardLayoutManager 中 onLayoutChildren() 是从 itemCount - 1 到 0 依次 addView 到 recyclerview 中的，
         * 所以在获取子 view (即 getChildAt(i)) 时，第 0 个子 View 对应的数据是 dataList 中最后一个，同时他的位移和缩放都是最大的。
         */
        for (int i = 0; i < itemCount; i++) {
            // i = 0  =>  第 0 层 , translationY = 0,  test data = 0 ，移动时不进行缩放处理
            // i = 1  =>  第 1 层(test data = 1) , 1 * translationY 变为 0 , 1 - 1* scale -> 1
            // i = 2  =>  第 2 层(test data = 2) , 2 * translationY 变为 1 * translationY,   1 - 2*scale - > 1 - scale
            // i = 3  =>  第 3 层(test data = 3) , 3 * translationY 变为 2 * translationY,
            // i = 4  =>  第 4 层(test data = 4) , 4 * translationY 变为 3 * translationY,
            // i = 5  =>  第 5 层(test data = 5) , 5 * translationY 变为 4 * translationY,
            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            Log.e("debudebug", "i = " + i + ", view == " + (view == null));
            if (i == 0) {
                // 旋转动画
                view.setRotation((dX < 0 ? -1 : 1) * 15 * ratio);
            } else {
                // 缩放，位移动画
                TextView textView = view.findViewById(R.id.tv_title);
                String text = textView.getText().toString();
                if (i < settings.getMaxVisibleCount()) {
                    float v = settings.getTranslationY() * i - settings.getTranslationY() * ratio;
                    view.setTranslationY(v);
                    view.setScaleX(1.0f - i * settings.getScale() + settings.getScale() * ratio);
                    view.setScaleY(1.0f - i * settings.getScale() + settings.getScale() * ratio);
                }
            }
        }
    }

    /**
     * 滑出屏幕的阀值,即滑动的距离超过 RecyclerView 宽度的一半，就会划出屏幕
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    private float getThreshold(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.e(TAG, "getSwipeThreshold() =  " + getSwipeThreshold(viewHolder));
        Log.e(TAG, "getWidth() =  " + recyclerView.getWidth());
//        return recyclerView.getWidth() * getSwipeThreshold(viewHolder);
        return recyclerView.getWidth() * 0.5f;
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setRotation(0f);
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.2f;
    }

    interface OnCardSwipeListener {
        void onCardSwipe();
    }

    private OnCardSwipeListener onCardSwipeListener;

    public void setOnCardSwipeListener(OnCardSwipeListener listener) {
        this.onCardSwipeListener = listener;
    }


}
