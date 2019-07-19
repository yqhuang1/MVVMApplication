package com.example.mvvmapplication.myinfo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.mvvmapplication.R;
import com.example.mvvmapplication.base.BaseActivity;
import com.example.mvvmapplication.databinding.ActivityMyInfoBinding;


public class MyInfoActivity extends BaseActivity {

    private MyInfoViewModel myInfoViewModel;
    private ActivityMyInfoBinding myInfoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         myInfoBinding =  DataBindingUtil.setContentView(this, R.layout.activity_my_info);

        myInfoViewModel = new MyInfoViewModel(this);

        myInfoBinding.setMyInfoViewModel(myInfoViewModel);

        myInfoViewModel.loadMyInfo();
    }

}
