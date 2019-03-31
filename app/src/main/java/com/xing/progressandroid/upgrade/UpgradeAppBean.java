package com.xing.progressandroid.upgrade;

import java.io.Serializable;

/**
 * 封装更新信息的实体
 */
public class UpgradeAppBean implements Serializable {
    private String apkUrl;
    private boolean isForceUpgrade;
    private HttpManager httpManager;

    public UpgradeAppBean(String apkUrl, boolean isForceUpgrade, HttpManager httpManager) {
        this.apkUrl = apkUrl;
        this.isForceUpgrade = isForceUpgrade;
        this.httpManager = httpManager;
    }

    public HttpManager getHttpManager() {
        return httpManager;
    }

    public void setHttpManager(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public boolean isForceUpgrade() {
        return isForceUpgrade;
    }

    public void setForceUpgrade(boolean forceUpgrade) {
        isForceUpgrade = forceUpgrade;
    }

    @Override
    public String toString() {
        return "UpgradeAppBean{" +
                "apkUrl='" + apkUrl + '\'' +
                ", isForceUpgrade=" + isForceUpgrade +
                '}';
    }
}
