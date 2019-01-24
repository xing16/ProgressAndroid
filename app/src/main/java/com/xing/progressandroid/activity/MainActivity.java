package com.xing.progressandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xing.progressandroid.R;
import com.xing.progressandroid.utils.IntentUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_bezier:
                IntentUtil.startActivity(this, BezierActivity.class);
                break;
            case R.id.btn_xfermode:
                IntentUtil.startActivity(this, XfermodeActivity.class);
                break;
            case R.id.btn_shader:
                IntentUtil.startActivity(this, ShaderActivity.class);
                break;
        }
    }
}
