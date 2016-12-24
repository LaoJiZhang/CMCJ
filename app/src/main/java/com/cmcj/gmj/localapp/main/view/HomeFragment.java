package com.cmcj.gmj.localapp.main.view;

import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.adapter.DatabindingRecyclerDelegate;
import com.cmcj.gmj.localapp.base.adapter.OnRecyclerViewAttachItemClickListener;
import com.cmcj.gmj.localapp.base.component.BaseDatabindingFragment;
import com.cmcj.gmj.localapp.base.presenter.BaseFragmentDatabindingPresenter;
import com.cmcj.gmj.localapp.databinding.FragmentHomeBinding;
import com.cmcj.gmj.localapp.main.presenter.HomePresenter;
import com.cmcj.gmj.localapp.utils.LogUtils;
import com.cmcj.gmj.localapp.utils.ToastUtils;

/**
 * Created by guomaojian on 16/11/5.
 */

public class HomeFragment extends BaseDatabindingFragment<HomePresenter> implements IHome {

    private FragmentHomeBinding mBinding;
    private Handler mHandler = new Handler();

    @Override
    protected FragmentProxy createFragmentProxy() {
        return new FragmentProxy() {
            @Override
            public BaseFragmentDatabindingPresenter createPresenter() {
                return new HomePresenter(HomeFragment.this);
            }

            @Override
            public int getRestId() {
                return R.layout.fragment_home;
            }

            @Override
            public void finishCreateView(ViewDataBinding binding) {
                mBinding = (FragmentHomeBinding) binding;
                initView();
            }
        };
    }

    private void initView() {
        showLoadingPage();
        mBinding.homeRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.homeRecyclerview.setAdapter(mPresenter.getHomeAdapter());
        mBinding.homeRecyclerview.setItemAnimator(new DefaultItemAnimator());
//        mBinding.homeRecyclerview.addOnItemTouchListener(new OnRecyclerViewItemClickListener<AbsMultiTypeRecyclerViewHolder<MovieEntity>>(mBinding.homeRecyclerview) {
//
//            @Override
//            public void onItemClickEvent(RecyclerView recyclerView, AbsMultiTypeRecyclerViewHolder<MovieEntity> viewHolder) {
//                viewHolder.getProxy().getItemByPosition(viewHolder.getLayoutPosition());
//                LogUtils.i("CCC");
//            }
//
//            @Override
//            public void onItemLongClickEvent(RecyclerView recyclerView, AbsMultiTypeRecyclerViewHolder<MovieEntity> viewHolder) {
//                LogUtils.i("CCC");
//            }
//        });
        mBinding.homeRecyclerview.addOnChildAttachStateChangeListener(new OnRecyclerViewAttachItemClickListener() {
            @Override
            public void onItemClickEvent(Object data) {
                LogUtils.i("ccc", data.toString());
                ToastUtils.showToast("onItemClickEvent");
            }

            @Override
            public void onItemLongClickEvent(Object data) {
                ToastUtils.showToast("onItemLongClickEvent");
            }
        });
    }

    @Override
    public void getMovieSuccess() {
        showNormalPage();
        addHeaderView();
    }

    private void addHeaderView() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().getHomeAdapter().addHeaderView("我是header Title", new DatabindingRecyclerDelegate<String>() {
                    @Override
                    public int getItemLayoutResId() {
                        return R.layout.view_list_header;
                    }

                    @Override
                    public void onBindItem(ViewDataBinding viewDataBinding, String item, int position) {
                        LogUtils.i("ccc", "onBindItem : " + item.toString());
                    }
                });
                addFooterView();
            }
        }, 3000);
    }

    private void addFooterView() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                getPresenter().getHomeAdapter().removeHeaderView();
                getPresenter().getHomeAdapter().addFooterView("footerView", new DatabindingRecyclerDelegate() {
                    @Override
                    public int getItemLayoutResId() {
                        return R.layout.view_list_footer;
                    }

                    @Override
                    public void onBindItem(ViewDataBinding viewDataBinding, Object data, int position) {
                        LogUtils.i("ccc", "onBindItem : " + data.toString());
                    }
                });
            }
        }, 3000);
    }

    @Override
    public void showNetError() {
        showErrNetworkPage();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mBinding.homeRecyclerview;
    }
}
