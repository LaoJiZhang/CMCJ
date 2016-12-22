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
 * Created by guomaojian on 16/12/22.
 */

public abstract class MultiTypeRecyclerViewAdapter<D> extends RecyclerView.Adapter<AbsMultiTypeRecyclerViewHolder> {

    private static final int VIEWTYPE_HEADER = 1 << 0;
    private static final int VIEWTYPE_BODY = 1 << 1;
    private static final int VIEWTYPE_FOOTER = 1 << 2;

    private Context mContext;
    private AbsMultiTypeRecyclerViewHolder mHeaderViewHolder;
    private List mDatas = new ArrayList<>();

    public MultiTypeRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    public void setBindingDatas(List datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void addHeaderView(Object object, DatabindingRecyclerDelegate proxy) {
        ViewDataBinding headerBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), proxy.getItemLayoutResId(), null, false);
        mHeaderViewHolder = new AbsMultiTypeRecyclerViewHolder(headerBinding, proxy);
        mDatas.add(0, object);
        notifyDataSetChanged();
    }

    public int getHeaderCount() {
        return mHeaderViewHolder == null ? 0 : 1;
    }

    public abstract DatabindingRecyclerDelegate<D> createProxy();

    public ViewDataBinding createBodyViewDataBinding(int layoutId) {
        return DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutId, null, false);
    }

    public AbsMultiTypeRecyclerViewHolder<D> createViewHolder() {
        DatabindingRecyclerDelegate proxy = createProxy();
        return new AbsMultiTypeRecyclerViewHolder<>(createBodyViewDataBinding(proxy.getItemLayoutResId()), proxy);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getHeaderCount())
            return VIEWTYPE_HEADER;
        else
            return VIEWTYPE_BODY;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public AbsMultiTypeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEWTYPE_HEADER:
                return mHeaderViewHolder;
            case VIEWTYPE_BODY:
                return createViewHolder();
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(AbsMultiTypeRecyclerViewHolder holder, int position) {
        holder.getProxy().onBindItem(holder.getViewDataBinding(), getItem(position), position);
    }

    public Object getItem(int position) {
        if (position < mDatas.size()) {
            return mDatas.get(position);
        }
        return null;
    }
}
