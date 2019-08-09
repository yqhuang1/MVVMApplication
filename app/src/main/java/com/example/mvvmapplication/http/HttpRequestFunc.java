package com.example.mvvmapplication.http;

import com.example.mvvmapplication.entity.HttpVideoResponse;

import rx.functions.Func1;

/**
 * Created by seph_von on 2018/3/25.
 */

public class HttpRequestFunc<T> implements Func1<HttpVideoResponse<T>, T> {
    @Override
    public T call(HttpVideoResponse<T> tHttpResponse) {
        System.out.println("======map2======" + tHttpResponse.toString());
        if (tHttpResponse.isResult()) {
            System.out.println("=======VideoResponseString2=======" + tHttpResponse.getData().toString());
            return tHttpResponse.getData();
        } else if (!UrlsFiled.RESPONSE_OVER_TIME.equals(tHttpResponse.getMsg())) {
            System.out.println("=======VideoResponseMsg2=======" + tHttpResponse.msg);
            throw new OverTimeException(tHttpResponse.getMsg());
        } else {
            throw new ResponseErrorException(tHttpResponse.getMsg() + ":code", tHttpResponse.getMsg() + ":desc");
        }
    }
}