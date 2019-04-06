package com.xing.progressandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.GridCategoryAdapter;
import com.xing.progressandroid.bean.GridCategoryBean;
import com.xing.progressandroid.customview.IndicatorView;
import com.xing.progressandroid.customview.gridpage.GridPageView;
import com.xing.progressandroid.listener.OnRecyclerPageChangeListener;

import java.util.ArrayList;
import java.util.List;

public class GridCategoryActivity extends AppCompatActivity {

    private static final String TAG = "GridCategoryActivity";
    private IndicatorView indicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_category);
        RecyclerView recyclerView = findViewById(R.id.rv_category);
        indicatorView = findViewById(R.id.iv_indicator);
        indicatorView.setCount(6);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        final List<GridCategoryBean> list = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            list.add(new GridCategoryBean(i + "", R.drawable.meizi));
        }
        GridCategoryAdapter adapter = new GridCategoryAdapter(this, list);
        adapter.setOnItemClickListener(new GridCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Log.e(TAG, "onItemClick: " + position + " - " + list.get(position));
                Toast.makeText(GridCategoryActivity.this, list.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new OnRecyclerPageChangeListener(pagerSnapHelper, new OnRecyclerPageChangeListener.OnPageChangeListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: " + position);
                indicatorView.setSelectedPosition(position);
            }
        }));

        recyclerView.setAdapter(adapter);


        //
        GridPageView gridPageView = findViewById(R.id.gpv_grid);
        gridPageView.setOnGridPageClickListener(new GridPageView.OnGridPageItemClickListener<GridCategoryBean>() {

            @Override
            public void onGridPageItemClick(int position, GridCategoryBean data, View view) {
                Toast.makeText(GridCategoryActivity.this, data.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        gridPageView.setDataList(list);
    }
}
