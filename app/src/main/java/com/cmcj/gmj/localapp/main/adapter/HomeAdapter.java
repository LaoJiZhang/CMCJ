package com.cmcj.gmj.localapp.main.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.common.RecyclerItemClickListener;
import com.cmcj.gmj.localapp.databinding.FragHomeListItemLayoutBinding;
import com.cmcj.gmj.localapp.main.modle.MovieEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guomaojian on 16/12/3.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private Context mContext;
    private List<MovieEntity> mDatas = new ArrayList<>();
    private RecyclerItemClickListener<MovieEntity> mRecyclerItemClickListener;

    public HomeAdapter(Context context, RecyclerItemClickListener<MovieEntity> listener) {
        mContext = context;
        mRecyclerItemClickListener = listener;
    }

    public void setDatas(List<MovieEntity> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    private MovieEntity getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragHomeListItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.frag_home_list_item_layout, parent, false);
        return new HomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        FragHomeListItemLayoutBinding binding = (FragHomeListItemLayoutBinding) holder.getViewDataBinding();
        final MovieEntity item = getItem(position);
        binding.setMovie(item);

        if (item.getImages() != null && !TextUtils.isEmpty(item.getImages().getLarge())) {
//            Uri uri = Uri.parse(item.getImages().getLarge());
            binding.homeItemPicIv.setImageURI(item.getImages().getLarge());
        }

        if (item.getGenres() != null) {
            binding.homeItemType1Tv.setVisibility(View.GONE);
            binding.homeItemType2Tv.setVisibility(View.GONE);
            binding.homeItemType3Tv.setVisibility(View.GONE);
            for (int i = 0, len = item.getGenres().size(); i < len; i++) {
                TextView textView = i == 0 ? binding.homeItemType1Tv : (i == 1 ? binding.homeItemType2Tv : binding.homeItemType3Tv);
                if (!TextUtils.isEmpty(item.getGenres().get(i))) {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(item.getGenres().get(i));
                }
            }
        }

        binding.homeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecyclerItemClickListener != null)
                    mRecyclerItemClickListener.onItemClickCall(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mBinding;

        public HomeViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public ViewDataBinding getViewDataBinding() {
            return mBinding;
        }
    }
}
