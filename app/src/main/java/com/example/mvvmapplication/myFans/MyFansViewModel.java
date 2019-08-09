package com.example.mvvmapplication.myFans;

import android.databinding.ObservableField;

import com.example.mvvmapplication.base.BaseViewModel;
import com.example.mvvmapplication.entity.BaseVideoEntity;
import com.example.mvvmapplication.http.OverTimeException;
import com.example.mvvmapplication.http.ResponseErrorException;
import com.example.mvvmapplication.model.HttpModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.functions.Action0;

public class MyFansViewModel extends BaseViewModel<MyFansActivity> {

    public ObservableField<List<OneViewModel>> mListOneViewModel = new ObservableField<>();

    private HttpModel httpModel = new HttpModel();

    public MyFansViewModel(MyFansActivity activity) {
        super(activity);
    }

    //调用 model,下载数据方法
    public void loadFans() {
        httpModel.requestFansList(new Action0() {
            @Override
            public void call() {
                // showLoadingView();
            }
        }, new Subscriber<List<BaseVideoEntity>>() {
            @Override
            public void onCompleted() {
                // finishLoadingView();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof OverTimeException) {
                    mActivity.showToast("");
                } else if (e instanceof ResponseErrorException) {
                    mActivity.showToast("");
                } else {
                    mActivity.showToast("服务器连接失败，请稍后再试~");
                }
                // finishLoadingView();
            }

            @Override
            public void onNext(List<BaseVideoEntity> baseVideoEntityList) {
                if (baseVideoEntityList == null) {
                    return;
                }

                List<OneViewModel> oneViewModelList = new ArrayList<>();
                for (BaseVideoEntity entity : baseVideoEntityList) {
                    OneViewModel oneViewModel = new OneViewModel(mActivity);
                    oneViewModel.setData(entity.getPic_600_x(), "项目名称：" + entity.getMatch_id(), entity.getName());
                    oneViewModelList.add(oneViewModel);
                }
                mListOneViewModel.set(oneViewModelList);
            }
        }, "3");
    }

    @Override
    public void clear() {
        httpModel.cancelRequest();
    }
}
