package com.xing.progressandroid.activity;

import android.content.Intent;
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
import com.xing.progressandroid.activity.layoutmanager.CardLayoutManagerActivity;
import com.xing.progressandroid.activity.layoutmanager.CustomLinearLayoutManagerActivity;
import com.xing.progressandroid.activity.layoutmanager.FlowLayoutManagerActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void click(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.btn_custom_linear_manager) {
            intent.setClassName(getPackageName(), CustomLinearLayoutManagerActivity.class.getName());
        } else if (view.getId() == R.id.btn_card_layout_manager) {
            intent.setClassName(getPackageName(), CardLayoutManagerActivity.class.getName());
        } else if (view.getId() == R.id.btn_flow_layout_manager) {
            intent.setClassName(this, FlowLayoutManagerActivity.class.getName());
        }
        startActivity(intent);
    }
}
