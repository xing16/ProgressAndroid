package com.xing.progressandroid.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xing.progressandroid.R;

public class LiveDataActivity extends AppCompatActivity {

    private TextView textView;
    private TextView countTextView;
    private TimerViewModel timerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        textView = findViewById(R.id.tv_title);
        countTextView = findViewById(R.id.tv_count);

        timerViewModel = ViewModelProviders.of(this).get(TimerViewModel.class);
        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long aLong) {
                textView.setText(String.valueOf(aLong));
            }
        };
        timerViewModel.getMutableLiveData().observe(this, observer);


        // 计数
        CountViewModel countViewModel = ViewModelProviders.of(this).get(CountViewModel.class);
        Observer<Integer> countObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                countTextView.setText("计数： " + String.valueOf(integer));
            }
        };
        countViewModel.getCountLiveData().observe(this, countObserver);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("CountLiveData", "LiveDataActivity -> onStart(): " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("CountLiveData", "LiveDataActivity -> onResume(): " );
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("CountLiveData", "LiveDataActivity -> onRestart(): " );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("CountLiveData", "LiveDataActivity -> onPause(): " );
    }

    public void click(View view) {
        startActivity(new Intent(this, LiveDataSecondActivity.class));
        timerViewModel.getMutableLiveData().postValue(100L);
    }
}
