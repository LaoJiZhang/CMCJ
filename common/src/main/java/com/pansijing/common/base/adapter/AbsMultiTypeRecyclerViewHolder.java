package com.pansijing.common.base.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by guomaojian on 16/12/22.
 */

public class AbsMultiTypeRecyclerViewHolder<T> extends RecyclerView.ViewHolder {

    private ViewDataBinding mViewDataBinding;
    private DatabindingRecyclerDelegate<T> mProxy;

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

    public DatabindingRecyclerDelegate<T> getProxy() {
        return mProxy;
    }

    public AbsMultiTypeRecyclerViewHolder(ViewDataBinding viewDataBinding, DatabindingRecyclerDelegate<T> proxy) {
        super(viewDataBinding.getRoot());
        mViewDataBinding = viewDataBinding;
        mProxy = proxy;
    }
}
