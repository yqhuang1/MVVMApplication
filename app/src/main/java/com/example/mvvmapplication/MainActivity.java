package com.example.mvvmapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvvmapplication.base.BaseActivity;
import com.example.mvvmapplication.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mainBinding;
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        mainBinding.setMainViewModel(mainViewModel);
    }


}
