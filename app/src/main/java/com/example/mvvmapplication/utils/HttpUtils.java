package com.example.mvvmapplication.utils;

import android.content.Context;
import android.util.Log;

import com.example.mvvmapplication.entity.BaseVideoEntity;
import com.example.mvvmapplication.entity.HttpResponse;
import com.example.mvvmapplication.entity.VideoInfo;
import com.example.mvvmapplication.mylist.MeViewModel;
import com.example.mvvmapplication.mylist.MyListActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.Result;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HttpUtils {

    private static final String baseUrl = "http://apps.ifeimo.com/home/";
    private static final String mPDFDownloadUrl = "http://apps.ifeimo.com/home/";
    private static File mPDFSavedFile = new File("mPDFSavedFile.txt");

    private static Gson gson = new GsonBuilder()
            //配置你的Gson
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            //可以接收自定义的Gson，当然也可以不传
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public void getRetrofit(Context context) {

        ApiService service = retrofit.create(ApiService.class);

        Call<ResponseBody> call = service.getPage("3");
        // 用法和OkHttp的call如出一辙,
        // 不同的是如果是Android系统回调方法执行在主线程
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("CALLUrl", call.request().url().toString());
                    Log.e("GETBody", response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void postRetrofit(Context context) {
        ApiService service = retrofit.create(ApiService.class);
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setContent("新建的Blog");
        videoInfo.setTitle("测试");
        videoInfo.setAuthor("怪盗kidou");
        Call<Result<VideoInfo>> call = service.createBlog(videoInfo);
    }

    public void okHttpDownload() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.okHttpRetrofitDownloadFile(mPDFDownloadUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    //下载成功，写入文件
                    boolean bl = DownLoadManager.writeResponseBodyToDisk(mPDFSavedFile, response.body());
                    if (bl) {
                        //这一步就是对你下载下来的文件进行你想要的操作了，我这里是展示PDF
                        DownLoadManager.displayFromFile(mPDFSavedFile);
                    }
                } else {
                    //下载失败
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //下载失败
            }
        });
    }

    public void rxJavaDownload() {
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ResponseBody> responseBodyObserver = apiService.rxJavaRetrofitDownloadFile(baseUrl);
        apiService.rxJavaRetrofitDownloadFile(baseUrl)
                .map(new Func1<ResponseBody, Boolean>() {
                    @Override
                    public Boolean call(ResponseBody responseBody) {
                        //I/O 操作：读写文件
                        return DownLoadManager.writeResponseBodyToDisk(mPDFSavedFile, responseBody);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                               @Override
                               public void call(Boolean aBoolean) {
                                   if (aBoolean) {
                                       //这一步就是对你下载下来的文件进行你想要的操作了，我这里是展示PDF
                                       DownLoadManager.displayFromFile(mPDFSavedFile);
                                   }
                               }
                           }
                        , new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                //onError
                                Log.d("Throwable", throwable.getMessage());
                            }
                        })
        ;
    }

    public void requestList(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Subscription subscription = apiService.requestList()
                .map(new Func1<HttpResponse<List<BaseVideoEntity>>, List<BaseVideoEntity>>() {
                    @Override
                    public List<BaseVideoEntity> call(HttpResponse<List<BaseVideoEntity>> listHttpResponse) {
                        Log.e("requestListCall", listHttpResponse.getContent().toString());
                        return listHttpResponse.getContent();
                    }
                })
                .compose(new Observable.Transformer<List<BaseVideoEntity>, List<BaseVideoEntity>>() {
                    @Override
                    public Observable<List<BaseVideoEntity>> call(Observable<List<BaseVideoEntity>> listObservable) {
                        return listObservable.observeOn(Schedulers.io())
                                .unsubscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        // showLoadingView();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BaseVideoEntity>>() {
                    @Override
                    public void onCompleted() {
                        // finishLoadingView();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<BaseVideoEntity> videoInfos) {
                        if (videoInfos == null) {
                            return;
                        }
                        List<MeViewModel> meViewModelList = new ArrayList<>();
                        for (BaseVideoEntity entity : videoInfos) {
                            MeViewModel meViewModel = new MeViewModel((MyListActivity) context);
                            meViewModel.setData(entity.getId(), entity.getName(), entity.getUrl());
                            meViewModelList.add(meViewModel);
                        }
                    }
                });

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

    }

}
