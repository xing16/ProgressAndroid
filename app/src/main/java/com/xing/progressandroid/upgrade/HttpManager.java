package com.xing.progressandroid.upgrade;

import java.io.Serializable;

public interface HttpManager extends Serializable {

    void download(String url, String destPath, String fileName, UpgradeDownloadListener listener);
}
