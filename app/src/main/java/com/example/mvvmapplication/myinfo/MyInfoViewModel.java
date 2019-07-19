package com.example.mvvmapplication.myinfo;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.example.mvvmapplication.base.BaseViewModel;

public class MyInfoViewModel extends BaseViewModel<MyInfoActivity> {

    public ObservableField<String> imageUrl = new ObservableField<>();

    public ObservableField<String> name = new ObservableField<>();

    public ObservableInt age = new ObservableInt();


    public MyInfoViewModel(MyInfoActivity activity) {
        super(activity);
    }

    public void loadMyInfo(){
        /**https://avatar.csdn.net/6/F/7/1_u011002668.jpg?1523959103
         * https://www.lianaiyx.com/d/file/GalGame/2019-01-08/6caa758b0c9b7c90f158bc77c0af3ed6.jpg
         * **/
        imageUrl.set("https://www.lianaiyx.com/d/file/GalGame/2019-01-08/6caa758b0c9b7c90f158bc77c0af3ed6.jpg");
        name.set("猪大肠");
        age.set(18);
    }
    @Override
    public void clear() {

    }
}
