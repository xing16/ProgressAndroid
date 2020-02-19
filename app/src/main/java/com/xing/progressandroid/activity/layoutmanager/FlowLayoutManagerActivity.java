package com.xing.progressandroid.activity.layoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.progressandroid.R;
import com.xing.progressandroid.activity.LayoutManagerActivity;
import com.xing.progressandroid.layoutmanager.FlowLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class FlowLayoutManagerActivity extends AppCompatActivity {

    private String[] tags = {"Apple", "Mac OS", "MacBook pro", "Appcompat", "LayoutManager", "Android Studio", "VS Code", "iOS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout_manager);
        RecyclerView flowRecyclerView = findViewById(R.id.rv_flow);
        // 流式布局
        final List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            stringList.add(tags[i % tags.length]);
        }
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager(this);
        flowRecyclerView.setLayoutManager(flowLayoutManager);
        BaseQuickAdapter flowAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_flow, stringList) {

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_text_title, item);
            }
        };
        flowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(FlowLayoutManagerActivity.this, stringList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        flowRecyclerView.setAdapter(flowAdapter);
    }
}
