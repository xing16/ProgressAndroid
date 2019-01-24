package com.xing.progressandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xing.progressandroid.R;
import com.xing.progressandroid.customview.ComposeShaderView;

public class ShaderActivity extends AppCompatActivity {

    private boolean animating = false;
    private ComposeShaderView loadingView;
    private Button loadingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shader);
        loadingBtn = findViewById(R.id.btn_loading);
        loadingView = findViewById(R.id.cslv_loading);
        loadingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animating = !animating;
                if (animating) {
                    loadingView.startAnimator();
                    loadingBtn.setText("Stop");
                } else {
                    loadingView.stopAnimator();
                    loadingBtn.setText("Start");
                }
            }
        });
    }
}
