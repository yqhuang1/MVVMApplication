package com.example.mvvmapplication.mychat;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.example.mvvmapplication.BR;
import com.example.mvvmapplication.base.MultiTypeListItemViewModel;

public class MessageViewModel extends MultiTypeListItemViewModel<MyChatActivity> {

    public ObservableInt mHeadImgResId = new ObservableInt();

    public ObservableField<String> mNickName = new ObservableField<>();

    public ObservableField<String> mMessage = new ObservableField<>();

    public MessageViewModel(MyChatActivity activity) {
        super(activity);
    }

    @Override
    public int variableId() {
        return BR.messageViewModel;
    }

    public MessageViewModel setData(int type, int headImgResId, String nickName, String lastMessage) {
        mType = type;
        /**设置属性绑定数据**/
        mHeadImgResId.set(headImgResId);
        mNickName.set(nickName);
        mMessage.set(lastMessage);
        return this;
    }

    @Override
    public void clear() {

    }
}
