package com.xing.progressandroid.activity;

import android.os.Bundle;
import android.view.View;

import com.xing.progressandroid.R;
import com.xing.progressandroid.upgrade.AppUpgradeManager;
import com.xing.progressandroid.upgrade.TestHttpManager;

public class AppUpgradeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_upgrade);
    }


    public void click(View view) {
        AppUpgradeManager upgrade = new AppUpgradeManager.Builder()
                .with(this)
                .url("https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/apk/sample-debug.apk")
                .upgradeMode(AppUpgradeManager.UPGRADE_OPTIONAL)
                .setHttpManager(new TestHttpManager())
                .upgrade();
        upgrade.showUpgradeDialog();
    }
}
