package com.example.mvvmapplication.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;

import com.example.mvvmapplication.adapter.CommonAdapter;
import com.example.mvvmapplication.adapter.MultiTypeAdapter;

import java.util.List;


/**
 * RecyclerView 在布局文件中使用的自定义属性值
 *
 * @author fengwl
 * @version [版本]
 * @see [相关类]
 * @since [模块]
 */
public class RecyclerBinding {

    /**
     * app:data="@{myListViewModel.myListViewModel}"
     *
     * @BindingAdapter("app:data")
     **/
    @BindingAdapter("data")
    public static void setRecyclerInfo(RecyclerView recyclerView, List datas) {
        CommonAdapter mAdapter = (CommonAdapter) recyclerView.getAdapter();
        mAdapter.setData(datas);
    }

    /**
     * @BindingAdapter({"app:data", "app:layoutMapping"})
     **/
    @BindingAdapter({"data", "layoutMapping"})
    public static void setRecyclerInfo(RecyclerView recyclerView, List data, SparseIntArray mapping) {
        MultiTypeAdapter mAdapter = (MultiTypeAdapter) recyclerView.getAdapter();
        mAdapter.setData(data, mapping);
    }
}
