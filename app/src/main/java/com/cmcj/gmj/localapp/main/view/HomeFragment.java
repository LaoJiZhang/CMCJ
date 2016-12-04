package com.cmcj.gmj.localapp.main.view;

import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.component.BaseDatabindingFragment;
import com.cmcj.gmj.localapp.base.presenter.BaseFragmentDatabindingPresenter;
import com.cmcj.gmj.localapp.databinding.FragmentHomeBinding;
import com.cmcj.gmj.localapp.main.presenter.HomePresenter;

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
    }

    @Override
    public void getMovieSuccess() {
        showNormalPage();
    }

    @Override
    public void showNetError() {
        showErrNetworkPage();
    }
}
