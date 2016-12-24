package com.cmcj.gmj.localapp.main.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cmcj.gmj.localapp.R;
import com.pansijing.common.base.adapter.DatabindingRecyclerDelegate;
import com.pansijing.common.base.adapter.MultiTypeRecyclerViewAdapter;
import com.cmcj.gmj.localapp.databinding.FragHomeListItemLayoutBinding;
import com.cmcj.gmj.localapp.main.modle.MovieEntity;

/**
 * Created by guomaojian on 16/12/23.
 */

public class HomeNewAdapte extends MultiTypeRecyclerViewAdapter<MovieEntity> {

    public HomeNewAdapte(RecyclerView view) {
        super(view);
    }

    @Override
    public DatabindingRecyclerDelegate<MovieEntity> createProxy() {
        return new DatabindingRecyclerDelegate<MovieEntity>() {
            @Override
            public int getItemLayoutResId() {
                return R.layout.frag_home_list_item_layout;
            }

            @Override
            public void onBindItem(ViewDataBinding viewDataBinding, final MovieEntity item, int position) {
                FragHomeListItemLayoutBinding binding = (FragHomeListItemLayoutBinding) viewDataBinding;
                binding.setMovie(item);

                if (item.getImages() != null && !TextUtils.isEmpty(item.getImages().getLarge())) {
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

//                binding.homeItem.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (mRecyclerItemClickListener != null)
//                            mRecyclerItemClickListener.onItemClickCall(item);
//                    }
//                });
            }
        };
    }
}
