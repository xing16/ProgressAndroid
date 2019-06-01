package com.xing.progressandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.RecyclerAdapter;
import com.xing.progressandroid.bean.ItemBean;
import com.xing.progressandroid.customview.flowlayout.FlowAdapter;
import com.xing.progressandroid.layoutmanager.FlowLayoutManager;
import com.xing.progressandroid.layoutmanager.card.CardLayoutManager;
import com.xing.progressandroid.layoutmanager.card.CardLayoutManagerSettings;
import com.xing.progressandroid.layoutmanager.card.CardSwipeCallback;

import java.util.ArrayList;
import java.util.List;

public class LayoutManagerActivity extends AppCompatActivity {

    private String[] tags = {"Apple", "Mac OS", "MacBook pro", "Appcompat", "LayoutManager", "Android Studio", "VS Code", "iOS"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager);
        RecyclerView recyclerView = findViewById(R.id.rv_layout_manager);
        RecyclerView flowRecyclerView = findViewById(R.id.rv_flow);
        List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int resId;
            if (i % 4 == 0) {
                resId = R.color.red_dark;
            } else if (i % 4 == 1) {
                resId = R.color.green_dark;
            } else if (i % 4 == 2) {
                resId = R.color.orange;
            } else {
                resId = R.color.green_light;
            }
            list.add(new ItemBean(resId, "test data = " + i));
        }
        // 卡片
        CardLayoutManagerSettings settings = CardLayoutManagerSettings.newBuilder()
                .setMaxVisibleCount(4)
                .setScale(0.1f)
                .setTranslationY(dp2px(30))
                .build();
        CardLayoutManager cardLayoutManager = new CardLayoutManager(settings);
        recyclerView.setLayoutManager(cardLayoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CardSwipeCallback<>(list, adapter, settings));
        itemTouchHelper.attachToRecyclerView(recyclerView);


        // 流式布局
        final List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
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
                Toast.makeText(LayoutManagerActivity.this, stringList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        flowRecyclerView.setAdapter(flowAdapter);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
