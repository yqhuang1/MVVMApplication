package com.example.mvvmapplication.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.mvvmapplication.entity.VideoInfo;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class VideoUtils {

    private final String tag = "RXjavaTAG";

    /**
     * 获取手机外部视频
     **/
    public List<VideoInfo> getExternalVideo(Context context) {
        List<VideoInfo> list = new ArrayList<VideoInfo>();
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = new String[]{MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.MINI_THUMB_MAGIC};
        Cursor cursor = contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null,
                null, null);
        cursor.moveToFirst();
        int fileNum = cursor.getCount();
        for (int i = 0; i < fileNum; i++) {
            String str = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Video.Media.DATA));
            if (!str.contains("LuPingDaShi")) {
                VideoInfo vi = new VideoInfo();
                vi.setDisplayName(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
                vi.setPath(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                vi.setThumbnail(cursor.getString((cursor.
                        getColumnIndexOrThrow(MediaStore.Video.Media.MINI_THUMB_MAGIC))));
                list.add(vi);
            }
            cursor.moveToNext();
        }
        return list;
    }

    public void RXjavaSHow() {

        Observer<String> observer = new Observer<String>() {

            @Override
            public void onNext(String item) {
                Log.d(tag, "Item: " + item);
            }

            @Override
            public void onCompleted() {
                Log.d(tag, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag, "Error!");
            }

        };

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("111");
                subscriber.onNext("222");
                subscriber.onNext("333");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(observer);

    }

}
