package com.example.mvvmapplication.base;

/**
 * 类说明
 *
 * @author renjialiang
 * @version [版本]
 * @see [相关类]
 * @since [模块]
 */
public abstract class MultiTypeListItemViewModel<T extends BaseActivity> extends BaseViewModel<T> {

    public int mType;

    public MultiTypeListItemViewModel(T activity) {
        super(activity);
    }

    public abstract int variableId();
}
