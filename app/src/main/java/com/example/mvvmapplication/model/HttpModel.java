package com.example.mvvmapplication.model;

import com.example.mvvmapplication.entity.BaseVideoEntity;
import com.example.mvvmapplication.entity.HttpVideoResponse;
import com.example.mvvmapplication.utils.ApiService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HttpModel {

    private static final String baseUrl = "http://apps.ifeimo.com/home/";
    private Subscription mFansListSubscription;

    public void requestFansList(Action0 preAction, Subscriber<List<BaseVideoEntity>> subscriber, String page) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //addCallAdapterFactory影响的就是Call或者Observable，
        // Call类型是默认支持的(内部由DefaultCallAdapterFactory支持)，
        // 而如果要支持Observable，我们就需要自己添加 addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        //---------------------
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getVideoListObservable(page)
                .map(new Func1<HttpVideoResponse<List<BaseVideoEntity>>, List<BaseVideoEntity>>() {
                    @Override
                    public List<BaseVideoEntity> call(HttpVideoResponse<List<BaseVideoEntity>> listHttpVideoResponse) {
                        System.out.println("======map======" + listHttpVideoResponse.toString());
                        if (listHttpVideoResponse.isResult()) {
                            System.out.println("=======VideoResponseString=======" + listHttpVideoResponse.getData().toString());
                            return listHttpVideoResponse.getData();
                        } else {
                            System.out.println("=======VideoResponseMsg=======" + listHttpVideoResponse.msg);
                            throw new RuntimeException(listHttpVideoResponse.msg);
                        }
                    }
                })
                //Observable.Transformer 将 List<BaseVideoEntity> 转化为 Observable<List<BaseVideoEntity>>
                //compose指定请求网络和结果回调的线程
                .compose(new Observable.Transformer<List<BaseVideoEntity>, List<BaseVideoEntity>>() {
                    @Override
                    public Observable<List<BaseVideoEntity>> call(Observable<List<BaseVideoEntity>> listObservable) {
                        return listObservable.subscribeOn(Schedulers.io())
                                .unsubscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                //preAction，在请求之前做一些前置操作
                .doOnSubscribe(preAction)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void cancelRequest() {
        if (mFansListSubscription != null && !mFansListSubscription.isUnsubscribed()) {
            mFansListSubscription.unsubscribe();
        }
    }

}
