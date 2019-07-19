package com.example.mvvmapplication.base;

/**
 * 类说明
 *
 * @author renjialiang
 * @version [版本]
 * @see [相关类]
 * @since [模块]
 */
public abstract class BaseViewModel<T extends BaseActivity> {

    protected T mActivity;

    public BaseViewModel(T activity) {
        mActivity = activity;
    }

    public abstract void clear();
}
