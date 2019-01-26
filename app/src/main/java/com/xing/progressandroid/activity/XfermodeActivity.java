package com.xing.progressandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xing.progressandroid.R;
import com.xing.progressandroid.customview.HighlightProgressImageView;
import com.xing.progressandroid.utils.IntentUtil;

public class XfermodeActivity extends AppCompatActivity {

    private HighlightProgressImageView progressImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfermode);
        progressImageView = findViewById(R.id.hpiv_image);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_xfermode_bitmap_list:
                IntentUtil.startActivity(this, XfermodeBitmapSampleActivity.class);
                break;
            case R.id.btn_xfermode_color_list:
                IntentUtil.startActivity(this, XfermodeColorSampleActivity.class);
                break;
            case R.id.btn_xfermode_loading:
                progressImageView.start();
                break;

        }
    }
}
