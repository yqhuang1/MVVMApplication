package com.example.mvvmapplication.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * databinding 实现的recyclerview通用的Adapter
 *
 * @author fengwl
 * @version [版本]
 * @see [相关类]
 * @since [模块]
 */
public class CommonAdapter<T> extends RecyclerView.Adapter<CommonAdapter.CustomViewHolder> {

    List<T> mData = new ArrayList<>();

    private int mLayoutId = -1;
    private int mVariableId = -1;

    public CommonAdapter() {
    }

    /**
     * 通用Adapter的构造函数
     *
     * @param layoutId   item的布局文件ID
     * @param variableId 布局文件中data中的变量ID，eg. 变量名为viewModel,则这里传值为BR.viewModel
     */
    public CommonAdapter(int layoutId, int variableId) {
        mLayoutId = layoutId;
        mVariableId = variableId;
    }

    public void setData(List data) {
        if (data != null) {
            this.mData = data;
            notifyDataSetChanged();
        }
    }

    public void addData(List<T> data) {
        if (data != null) {
            int ps = mData.size();
            mData.addAll(data);
            notifyItemRangeInserted(ps, data.size());
        }
    }

    /**
     * 设置布局文件ID
     *
     * @param layoutId Item布局文件ID
     */
    public void setLayoutId(int layoutId) {
        mLayoutId = layoutId;
    }

    /**
     * 设置布局文件中Data部分变量ID，eg.  变量名为viewModel,则这里传值为BR.viewModel
     *
     * @param variableId Data部分变量ID
     */
    public void setVariableId(int variableId) {
        mVariableId = variableId;
    }

    /**
     * 加载布局 onCreateViewHolder()
     **/
    @Override
    public CommonAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutId != -1 && mVariableId != -1) {
            ViewDataBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), mLayoutId, parent, false);
            //传统的写法ViewHolder持有的是一个View，而这里是一个ViewDataBinding
            return new CustomViewHolder(binding);
        } else {
            throw new IllegalArgumentException("No layoutId & variableId !!!");
        }
    }

    /**
     * 绑定数据 onBindViewHolder()
     **/
    @Override
    public void onBindViewHolder(CommonAdapter.CustomViewHolder holder, int position) {
        T itemInfo = mData.get(position);

        /**mVariableId是什么呢？它其实是xml中申明的ViewModel的id。
         * 比如我们在xml中申明了一个ViewModel，name叫friendViewModel，
         * 就会自动在BR类中编译出一个id，叫  BR.friendViewModel
         **/
        holder.mBinding.setVariable(mVariableId, itemInfo);
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding mBinding;

        public CustomViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
