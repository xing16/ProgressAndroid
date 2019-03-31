package com.xing.progressandroid.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {

    @Streaming //大文件时要加不然会OOM
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
