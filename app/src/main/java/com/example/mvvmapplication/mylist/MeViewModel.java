package com.example.mvvmapplication.mylist;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmapplication.base.BaseViewModel;

public class MeViewModel extends BaseViewModel<MyListActivity> {

    public ObservableInt mHeadImgResId = new ObservableInt();
    public ObservableField<String> mNickName = new ObservableField<>();
    public ObservableField<String> mLastMessage = new ObservableField<>();

    public MeViewModel(MyListActivity activity) {
        super(activity);
    }

    @Override
    public void clear() {

    }

    public MeViewModel setData(int headImgResId, String nickName, String lastMessage) {
        mHeadImgResId.set(headImgResId);
        mNickName.set(nickName);
        mLastMessage.set(lastMessage);
        return this;
    }


    public void onMeClick(View view) {
        Toast.makeText(mActivity, mNickName.get() + "  Say:  " + mLastMessage.get(), Toast.LENGTH_SHORT).show();
    }

}
