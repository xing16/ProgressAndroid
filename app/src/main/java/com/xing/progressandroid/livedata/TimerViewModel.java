package com.xing.progressandroid.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TimerViewModel extends ViewModel {

    private static final String TAG = "TimerViewModel";
    private static final int ONE_SECOND = 1000;
    private MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();
    private final Long initTime;

    public TimerViewModel() {
        initTime = SystemClock.elapsedRealtime();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                long num = (SystemClock.elapsedRealtime() - initTime) / 1000;
                mutableLiveData.postValue(num);
            }
        }, ONE_SECOND, ONE_SECOND);
    }

    public MutableLiveData<Long> getMutableLiveData() {
        return mutableLiveData;
    }

    /**
     * 当 Activity 或 Fragment 销毁时，会回调该方法
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e(TAG, "onCleared: ");
    }
}
