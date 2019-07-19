package com.example.mvvmapplication;

import android.content.Intent;
import android.view.View;

import com.example.mvvmapplication.base.BaseViewModel;
import com.example.mvvmapplication.myinfo.MyInfoActivity;
import com.example.mvvmapplication.myshow.MyShowActivity;

public class MainViewModel extends BaseViewModel<MainActivity> {

    /**
     * ViewModel 暴露出去的绑定 属性、方法 必须是public
     * 否则会产生错误提示 / data binding error ****msg
     **/
    public MainViewModel(MainActivity activity) {
        super(activity);

    }

    public void onMyInfoBtnClick(View view) {
        mActivity.startActivity(new Intent(mActivity, MyInfoActivity.class));
    }

    public void onMyShowBtnClick(View view) {
        mActivity.startActivity(new Intent(mActivity, MyShowActivity.class));
    }

    @Override
    public void clear() {

    }
}
