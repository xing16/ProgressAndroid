package com.xing.progressandroid.activity.layoutmanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.RecyclerAdapter;
import com.xing.progressandroid.bean.ItemBean;
import com.xing.progressandroid.layoutmanager.CustomLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class CustomLinearLayoutManagerActivity extends AppCompatActivity {

    private static final String TAG = "CustomLinearLayout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_linear_layout_manager);
        RecyclerView recyclerView = findViewById(R.id.rv_linear_layout);
        List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new ItemBean(R.drawable.ic_back, "test data = " + i));
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int childCount = recyclerView.getChildCount();
                    Log.e(TAG, "childCount = " + childCount);
                    for (int i = 0; i < childCount; i++) {
                        View childView = recyclerView.getChildAt(i);
                        TextView textView = childView.findViewById(R.id.tv_title);
                        String text = textView.getText().toString();
                        Log.e(TAG, "i = " + i + ", text = " + text);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new CustomLinearLayoutManager());
        recyclerView.setAdapter(new RecyclerAdapter(this, list));
    }
}
