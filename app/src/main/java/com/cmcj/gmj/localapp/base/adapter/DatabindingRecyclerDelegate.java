package com.cmcj.gmj.localapp.base.adapter;

import android.databinding.ViewDataBinding;

/**
 * Created by guomaojian on 16/12/22.
 */

public interface DatabindingRecyclerDelegate<T> {

    int getItemLayoutResId();

    void onBindItem(ViewDataBinding viewDataBinding, T data, int position);
}
