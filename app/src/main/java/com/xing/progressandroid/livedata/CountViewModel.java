package com.xing.progressandroid.livedata;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

public class CountViewModel extends ViewModel {

    private static final String TAG = "CountViewModel";
    CountLiveData countLiveData;

    public CountViewModel() {
        countLiveData = new CountLiveData();
    }


    public CountLiveData getCountLiveData() {
        return countLiveData;
    }

    /**
     * Activity / Fragment 销毁时回调该方法
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e(TAG, "onCleared: ");
    }
}
