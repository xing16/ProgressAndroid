package com.xing.progressandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xing.progressandroid.R;
import com.xing.progressandroid.customview.RippleAnimationView;

public class RippleAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple_animation);
    }

    public void click(View view) {
        RippleAnimationView.create(view).start();
    }
}
