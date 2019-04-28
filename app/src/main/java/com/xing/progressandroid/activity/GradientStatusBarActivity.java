package com.xing.progressandroid.activity;

import android.os.Bundle;

import com.xing.progressandroid.R;
import com.xing.progressandroid.utils.StatusBarUtil;

public class GradientStatusBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_status_bar);


    }


    @Override
    public void setStatusBar() {
        StatusBarUtil.setDrawable(this, R.drawable.gradient_status_bg);

    }
}
