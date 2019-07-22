package com.example.mvvmapplication.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mvvmapplication.base.MultiTypeListItemViewModel;

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
public class MultiTypeAdapter<T extends MultiTypeListItemViewModel> extends RecyclerView.Adapter<MultiTypeAdapter.CustomViewHolder> {

    Context mContext;

    List<T> mData = new ArrayList<>();

    SparseIntArray mLayoutMapping;

    public MultiTypeAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<T> data, SparseIntArray layoutMapping) {
        if (data != null) {
            this.mData = data;
            this.mLayoutMapping = layoutMapping;
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
     * 设置布局文件和类型的对应关系
     *
     * @param layoutMapping 布局文件和类型的对应关系
     */
    public void setLayoutMapping(SparseIntArray layoutMapping) {
        this.mLayoutMapping = layoutMapping;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        /**以 mData 根据 position 获得相应的 MessageViewModel，并从中取得对应的 mType **/
        return mData.get(position).mType;
    }

    @Override
    public MultiTypeAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        if (mLayoutMapping != null) {
            /**根据 mType，从 mLayoutMapping 中取得相应的 layoutId**/
            int layoutId = mLayoutMapping.get(type);
            if (layoutId != 0) {
                ViewDataBinding binding = DataBindingUtil.inflate(
                        LayoutInflater.from(mContext), layoutId, parent, false);
                return new CustomViewHolder(binding);
            } else {
                throw new IllegalArgumentException("LayoutId is 0.");
            }
        } else {
            throw new IllegalArgumentException("LayoutMapping is null.");
        }
    }

    @Override
    public void onBindViewHolder(MultiTypeAdapter.CustomViewHolder holder, int position) {
        T itemInfo = mData.get(position);
        holder.mBinding.setVariable(itemInfo.variableId(), itemInfo);
        holder.mBinding.executePendingBindings();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding mBinding;

        public CustomViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }
}
