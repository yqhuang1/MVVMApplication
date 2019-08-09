package com.example.mvvmapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvvmapplication.base.BaseActivity;
import com.example.mvvmapplication.databinding.ActivityMainBinding;
import com.example.mvvmapplication.utils.HttpUtils;
import com.example.mvvmapplication.utils.VideoUtils;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mainBinding;
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        new VideoUtils().RXjavaSHow();
//        new HttpUtils().getRetrofit(this);
//        new HttpUtils().requestList(this);
//        new HttpUtils().getVideoListCall(this, "3");
//        new HttpUtils().getVideoListObservable1(this, "3");
//        new HttpUtils().getVideoListObservable2(this, "3");

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        mainBinding.setMainViewModel(mainViewModel);
    }


}
