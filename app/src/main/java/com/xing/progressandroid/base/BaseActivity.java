package com.xing.progressandroid.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xing.progressandroid.windowtip.NetworkChangeReceiver;

public class BaseActivity extends AppCompatActivity {

    private NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerNetworkChangeReceiver();
    }

    private void registerNetworkChangeReceiver() {
        receiver = new NetworkChangeReceiver(this);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver.onDestroy();
            receiver = null;
        }
    }
}
