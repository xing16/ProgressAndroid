package com.xing.progressandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.progressandroid.R;
import com.xing.progressandroid.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class SnapHelperActivity extends AppCompatActivity {

    private int[] colors = {R.color.red_dark, R.color.green_dark, R.color.orange, R.color.green_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_helper);
        RecyclerView linearSnapRecyclerView = findViewById(R.id.rv_linear_snap);
        RecyclerView pagerSnapRecyclerView = findViewById(R.id.rv_pager_snap);

        linearSnapRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        pagerSnapRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        final List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            list.add(new ItemBean(colors[i], "test data  = " + i));
        }
        BaseQuickAdapter adapter = new BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.item_img, list) {

            @Override
            protected void convert(BaseViewHolder helper, ItemBean item) {
                if (helper.getAdapterPosition() == 0) {
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) helper.itemView.getLayoutParams();
                    layoutParams.leftMargin = 30;
                    helper.itemView.setLayoutParams(layoutParams);
                }
                helper.setImageDrawable(R.id.iv_image, getResources().getDrawable(item.getResId()));
            }
        };
        linearSnapRecyclerView.setAdapter(adapter);
        pagerSnapRecyclerView.setAdapter(adapter);

        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(linearSnapRecyclerView);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(pagerSnapRecyclerView);


    }
}
