package com.xing.progressandroid.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

public class CountLiveData extends MutableLiveData<Integer> {

    private static final String TAG = "CountLiveData";
    private int count;
    private boolean isRun = true;
    private final CountTask countTask;

    public CountLiveData() {
        countTask = new CountTask();
        countTask.start();
    }


    @Override
    protected void onActive() {
        super.onActive();
        Log.e(TAG, "onActive: ");
        countTask.interrupt();
        isRun = true;
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.e(TAG, "onInactive: ");
        isRun = false;
    }

    private class CountTask extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                if (!isRun) {
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
                postValue(count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
