package com.xing.progressandroid.customview.gridpage;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.GridCategoryAdapter;
import com.xing.progressandroid.customview.IndicatorView;
import com.xing.progressandroid.customview.gridpage.adapter.GridOuterAdapter;

import java.util.List;

/**
 * 网格分页控件
 */
public class GridPageView extends LinearLayout {

    private Context context;
    private RecyclerView recyclerView;
    private IndicatorView indicatorView;

    public GridPageView(Context context) {
        this(context, null);
    }

    public GridPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_grid_page, this, true);
        recyclerView = view.findViewById(R.id.rv_grid);
        indicatorView = view.findViewById(R.id.iv_indicator);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

    }

    public <T> void setDataList(final List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("the list ca't be null");
        }
        GridOuterAdapter<T> adapter = new GridOuterAdapter<>(context, list);
        indicatorView.setCount(adapter.getItemCount());
        indicatorView.setSelectedPosition(0);
        adapter.setOnItemClickListener(new GridCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                T data = list.get(position);
                if (listener != null) {
                    listener.onGridPageItemClick(position, data, view);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public interface OnGridPageItemClickListener<T> {
        void onGridPageItemClick(int position, T data, View view);
    }

    OnGridPageItemClickListener listener;

    public void setOnGridPageClickListener(OnGridPageItemClickListener listener) {
        this.listener = listener;
    }

}


