package com.xing.progressandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xing.progressandroid.R;
import com.xing.progressandroid.event.TouchView;

public class EventTestActivity extends AppCompatActivity {

    private static final String TAG = "TouchView";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_test);
        TouchView touchView = findViewById(R.id.touch_view);
        Log.e(TAG, "isClickable: " + touchView.isClickable() );
        Log.e(TAG, "isLongClickable: " + touchView.isLongClickable() );
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        
        
        touchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " );
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "Activity dispatchTouchEvent: " );
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "Activity onTouchEvent: " );
        return super.onTouchEvent(event);
    }
}
