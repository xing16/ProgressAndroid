package com.xing.progressandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.base.BaseActivity;
import com.xing.progressandroid.customview.flowlayout.FlowAdapter;
import com.xing.progressandroid.customview.flowlayout.FlowLayout2;

import java.util.Random;

public class FlowLayoutActivity extends BaseActivity {

    private String[] data = {"Android", "Java", "Python", "linux", "iOS", "SpringBoot2.x",
            "MacBookPro", "windows", "RxJava2.x", "插件化组件化", "反射，动态代理"};
    private int[] heights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        heights = new int[]{dp2Px(20), dp2Px(30), dp2Px(40), dp2Px(25), dp2Px(60), dp2Px(50)};
        FlowLayout2 flowLayout = findViewById(R.id.fl_flow);
        FlowLayout2 flowLayout2 = findViewById(R.id.fl_flow2);
        flowLayout.setAdapter(new FlowAdapter<String>(data) {
            @Override
            public View getView(int position, String str, ViewGroup parent) {
                TextView textView = new TextView(FlowLayoutActivity.this);
                textView.setText(str);
                textView.setBackgroundResource(R.drawable.shape_flow_item_bg);
                return textView;
            }
        });

        flowLayout2.setAdapter(new FlowAdapter<String>(data) {

            @Override
            public View getView(int position, String s, ViewGroup parent) {
                TextView textView = new TextView(FlowLayoutActivity.this);
                textView.setText(s);
                textView.setBackgroundResource(R.drawable.shape_flow2_item_bg);
                textView.setTextColor(Color.parseColor("#ff555555"));
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, heights[new Random().nextInt(heights.length)]);
                textView.setLayoutParams(lp);
                if (position == 4) {
                    textView.setVisibility(View.GONE);
                }
                return textView;
            }
        });
    }


    private int dp2Px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

}
