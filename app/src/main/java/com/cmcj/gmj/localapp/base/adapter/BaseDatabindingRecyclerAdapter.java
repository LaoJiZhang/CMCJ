package com.cmcj.gmj.localapp.base.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc RecyclerAdapter封装类
 * Created by guomaojian on 16/12/4.
 */

public abstract class BaseDatabindingRecyclerAdapter<D> extends RecyclerView.Adapter<BaseDatabindingRecyclerAdapter.DatabindingViewHolder> {

    private Context mContext;
    private DatabindingRecyclerDelegate<D> mDatabindingRecyclerDelegate;
    private List<D> mBindingDatas = new ArrayList<>();

    public BaseDatabindingRecyclerAdapter(Context context) {
        mContext = context;
        mDatabindingRecyclerDelegate = createRecyclerAdapterProxy();
    }

    public void setBindingDatas(List<D> datas) {
        mBindingDatas.clear();
        mBindingDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addBindingDatas(List<D> datas) {
        mBindingDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public abstract DatabindingRecyclerDelegate createRecyclerAdapterProxy();

    @Override
    public DatabindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), mDatabindingRecyclerDelegate.getItemLayoutResId(), parent, false);
        return new DatabindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DatabindingViewHolder holder, int position) {
        mDatabindingRecyclerDelegate.onBindItem(holder.getVHBinding(), getItem(position), position);
    }

    public D getItem(int position) {
        return mBindingDatas.get(position);
    }

    @Override
    public int getItemCount() {
        return mBindingDatas.size();
    }

    public static class DatabindingViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mVHBinding;

        public DatabindingViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mVHBinding = binding;
        }

        public ViewDataBinding getVHBinding() {
            return mVHBinding;
        }
    }
}
