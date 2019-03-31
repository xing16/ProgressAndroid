package com.xing.progressandroid.upgrade;

import com.xing.progressandroid.http.ApiService;
import com.xing.progressandroid.http.RetrofitClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class TestHttpManager implements HttpManager {


    @Override
    public void download(String url, final String destPath, final String fileName, final UpgradeDownloadListener listener) {
        ApiService apiService = RetrofitClient.getInstance().getApiService();
        Disposable disposable = apiService.downloadFile(url)
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        writeToSdcard(responseBody, destPath, fileName, listener);
                    }
                });
    }

    private void writeToSdcard(ResponseBody responseBody, String destPath, String fileName, UpgradeDownloadListener listener) {
        if (responseBody == null) {
            listener.onDownloadFailure("apk 请求失败");
            return;
        }
        File destFile = new File(destPath, fileName);
        File parentFile = destFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        InputStream inputStream = responseBody.byteStream();
        long totalSize = responseBody.contentLength();
        long currentSize = 0;
        BufferedOutputStream bos = null;
        byte[] buffer = new byte[1024 * 2];
        int len;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                currentSize += len;
                listener.onDownloadProgress((int) (100 * currentSize / totalSize), totalSize);
            }
            bos.flush();
            // 下载完成
            listener.onDownloadComplete(destFile);
        } catch (FileNotFoundException e) {
            listener.onDownloadFailure("DestFile not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            listener.onDownloadFailure("IOException");
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
