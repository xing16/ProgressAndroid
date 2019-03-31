package com.xing.progressandroid.upgrade;

import android.support.v4.app.FragmentActivity;

public class AppUpgradeManager {

    /**
     * 可选升级
     */
    public static final int UPGRADE_OPTIONAL = 1;

    /**
     * 强制升级
     */
    public static final int UPGRADE_FORCE = 2;
    /**
     * apk 下载 URL
     */
    private String apkUrl;
    /**
     * 升级模式
     */
    private int mode;

    private FragmentActivity activity;

    private HttpManager httpManager;


    public void showUpgradeDialog() {
        UpgradeAppBean upgradeAppBean = new UpgradeAppBean(apkUrl, mode == UPGRADE_FORCE, httpManager);
        UpgradeDialogFragment dialogFragment = UpgradeDialogFragment.newInstance(upgradeAppBean);
        dialogFragment.show(activity.getSupportFragmentManager(), "appupgrade");
    }


    public static class Builder {
        String apkUrl;
        int mode;
        FragmentActivity activity;
        HttpManager httpManager;

        public Builder with(FragmentActivity activity) {
            this.activity = activity;
            return this;
        }

        public Builder url(String apkUrl) {
            this.apkUrl = apkUrl;
            return this;
        }

        public Builder upgradeMode(int mode) {
            this.mode = mode;
            return this;
        }

        public Builder setHttpManager(HttpManager httpManager) {
            this.httpManager = httpManager;
            return this;
        }

        public AppUpgradeManager upgrade() {
            AppUpgradeManager appUpgradeManager = new AppUpgradeManager();
            appUpgradeManager.activity = activity;
            appUpgradeManager.apkUrl = apkUrl;
            appUpgradeManager.mode = mode;
            appUpgradeManager.httpManager = httpManager;
            return appUpgradeManager;
        }
    }
}
