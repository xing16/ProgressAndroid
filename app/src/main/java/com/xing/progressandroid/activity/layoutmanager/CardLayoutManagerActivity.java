package com.xing.progressandroid.activity.layoutmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.widget.Toast;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.RecyclerAdapter;
import com.xing.progressandroid.bean.ItemBean;
import com.xing.progressandroid.layoutmanager.card.CardLayoutManager;
import com.xing.progressandroid.layoutmanager.card.CardLayoutManagerSettings;
import com.xing.progressandroid.layoutmanager.card.CardSwipeCallback;

import java.util.ArrayList;
import java.util.List;

public class CardLayoutManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_layout_manager);
        RecyclerView recyclerView = findViewById(R.id.rv_layout_manager);
        List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
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
                .setTranslationY(dp2px(22))
                .build();
        CardLayoutManager cardLayoutManager = new CardLayoutManager(settings);
        recyclerView.setLayoutManager(cardLayoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ItemBean itemBean, int position) {
                Toast.makeText(CardLayoutManagerActivity.this, itemBean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CardSwipeCallback<>(list, adapter, settings));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

}
