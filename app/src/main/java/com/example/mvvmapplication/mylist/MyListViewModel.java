package com.example.mvvmapplication.mylist;

import android.databinding.ObservableField;

import com.example.mvvmapplication.R;
import com.example.mvvmapplication.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MyListViewModel extends BaseViewModel<MyListActivity> {

    public ObservableField<List<MeViewModel>> mListViewModel = new ObservableField<>();

    public MyListViewModel(MyListActivity activity) {
        super(activity);
    }

    public void loadMeList() {
        List<MeViewModel> friends = new ArrayList<>();
        friends.addAll(requestData());
        mListViewModel.set(friends);

        Observable.from(friends)
                .filter(new Func1<MeViewModel, Boolean>() {
                    @Override
                    public Boolean call(MeViewModel meViewModel) {
                        return meViewModel.mNickName.get().isEmpty();
                    }
                })
                .map(new Func1<MeViewModel, String>() {
                    @Override
                    public String call(MeViewModel meViewModel) {
                        return meViewModel.mLastMessage.get();
                    }
                })
                /**
                 * subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
                 * I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler
                 * **/
                .subscribeOn(Schedulers.io())
                /**
                 * observeOn(): 指定 Subscriber 所运行在的线程，或者叫做事件消费的线程。
                 * 指定的操作（UI更新，显示）将在 Android 主线程运行
                 * **/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });

    }

    private List<MeViewModel> requestData() {
        List<MeViewModel> data = new ArrayList<>();
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_01, "张三", "打南边来了个喇嘛"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_02, "李四", "手里提着五斤鳎蚂"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_03, "王五", "打北边来了一个哑巴"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_04, "赵四", "腰里别着一个喇叭"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_05, "刘能", "提搂鳎蚂的喇嘛要拿鳎蚂去换别着喇叭的哑巴的喇叭"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_06, "大脚", "别着喇叭的哑巴不愿意拿喇叭去换提搂鳎蚂的喇嘛的鳎蚂"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_07, "芙蓉", "提搂鳎蚂的喇嘛抡起鳎蚂就给了别着喇叭的哑巴一鳎蚂"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_08, "秀才", "别着喇叭的哑巴抽出喇叭就给了提搂鳎蚂的喇嘛一喇叭"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_09, "掌柜", "也不知是提搂鳎蚂的喇嘛打了别着喇叭的哑巴"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_10, "大嘴", "还是别着喇叭的哑巴打了提搂鳎蚂的喇嘛"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_11, "展堂", "喇嘛回家炖鳎蚂"));
        data.add(new MeViewModel(mActivity).setData(R.drawable.head_image_12, "小六", "哑巴回家滴滴答答吹喇叭"));
        return data;
    }

    @Override
    public void clear() {

    }

}
