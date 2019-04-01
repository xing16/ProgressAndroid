package com.xing.progressandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.base.BaseActivity;
import com.xing.progressandroid.customview.flowlayout.FlowAdapter;
import com.xing.progressandroid.customview.flowlayout.FlowLayout;
import com.xing.progressandroid.customview.flowlayout.FlowLayout2;

public class FlowLayoutActivity extends BaseActivity {

    private String[] data = {"Android", "Java", "Python", "linux", "iOS", "SpringBoot2.x",
            "MacBookPro", "windows", "RxJava2.x", "插件化组件化", "反射，动态代理"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        FlowLayout flowLayout = findViewById(R.id.fl_flow);
        FlowLayout2 flowLayout2 = findViewById(R.id.fl_flow2);
        flowLayout.setAdapter(new FlowAdapter() {
            @Override
            public int getCount() {
                return data.length;
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                TextView textView = new TextView(FlowLayoutActivity.this);
                textView.setText(data[position]);
                textView.setBackgroundResource(R.drawable.shape_flow_item_bg);
                return textView;
            }
        });

        flowLayout2.setAdapter(new FlowAdapter() {
            @Override
            public int getCount() {
                return data.length;
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                TextView textView = new TextView(FlowLayoutActivity.this);
                textView.setText(data[position]);
                textView.setTextColor(Color.parseColor("#ff555555"));
                textView.setBackgroundResource(R.drawable.shape_flow_item_bg);
                if (position == 4) {
                    textView.setVisibility(View.GONE);
                }
                return textView;
            }
        });
    }
}
