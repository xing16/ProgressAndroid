package com.xing.progressandroid.upgrade;

import java.io.File;

public interface OnDownloadListener {

    /**
     * 下载开始
     */
    void onDownloadStart();

    /**
     * 下载完成
     *
     * @param apkFile
     */
    void onDownloadComplete(File apkFile);

    /**
     * 下载失败
     */
    void onDownloadFailure(String msg);

    /**
     * 下载进度
     *
     * @param progress  当前进度的百分比
     * @param totalSize
     */
    void onDownloadProgress(int progress, long totalSize);

}
