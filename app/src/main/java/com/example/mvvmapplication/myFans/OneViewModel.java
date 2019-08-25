package com.example.mvvmapplication.myFans;

import androidx.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmapplication.base.BaseViewModel;

public class OneViewModel extends BaseViewModel<MyFansActivity> {

    public ObservableField<String> mHeadImgUrl = new ObservableField<>();

    public ObservableField<String> mNickName = new ObservableField<>();

    public ObservableField<String> mLastMessage = new ObservableField<>();

    public OneViewModel(MyFansActivity activity) {
        super(activity);
    }

    public OneViewModel setData(String headImgUrl, String nickName, String lastMessage) {
        mHeadImgUrl.set(headImgUrl);
        mNickName.set(nickName);
        mLastMessage.set(lastMessage);
        return this;
    }

    public void onOneClick(View view) {
        Toast.makeText(mActivity, mLastMessage.get(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void clear() {

    }
}
