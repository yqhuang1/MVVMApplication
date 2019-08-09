package com.example.mvvmapplication.utils;

import com.example.mvvmapplication.entity.BaseVideoEntity;
import com.example.mvvmapplication.entity.HttpResponse;
import com.example.mvvmapplication.entity.HttpVideoResponse;
import com.example.mvvmapplication.entity.VideoInfo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiService {

    @GET("Match/getMatchList.html/")
    Call<ResponseBody> getPage(@Query("page") String page);

    @POST("blog")
    Call<Result<VideoInfo>> createBlog(@Body VideoInfo info);

    //PDF文件Retrofit下载
    //OkHttp
    @Streaming
    @GET
    Call<ResponseBody> okHttpRetrofitDownloadFile(@Url String fileUrl);

    //PDF文件Retrofit下载
    //RxJava
    @Streaming
    @GET
    Observable<ResponseBody> rxJavaRetrofitDownloadFile(@Url String fileUrl);

    @POST("Match/getMatchList.html/?page=2")
    Observable<HttpResponse<List<BaseVideoEntity>>> requestList();

    @GET("Match/getMatchList.html/")
    Call<HttpVideoResponse<List<BaseVideoEntity>>> getVideoListCall(@Query("page") String page);

    @GET("Match/getMatchList.html/")
    Observable<HttpVideoResponse<List<BaseVideoEntity>>> getVideoListObservable(@Query("page") String page);

}