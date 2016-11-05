package com.cmcj.gmj.localapp.main.view;

import android.databinding.ViewDataBinding;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.fragment.BaseDatabindingFragment;
import com.cmcj.gmj.localapp.base.presenter.BaseDatabindingFragmentPresenter;
import com.cmcj.gmj.localapp.databinding.FragmentHomeBinding;
import com.cmcj.gmj.localapp.main.presenter.HomePresenter;

/**
 * Created by guomaojian on 16/11/5.
 */

public class HomeFragment extends BaseDatabindingFragment<HomePresenter> implements IHome {

    private FragmentHomeBinding mBinding;

    @Override
    protected FragmentProxy createFragmentProxy() {
        return new FragmentProxy() {
            @Override
            public BaseDatabindingFragmentPresenter createPresenter() {
                return new HomePresenter(HomeFragment.this);
            }

            @Override
            public int getRestId() {
                return R.layout.fragment_home;
            }

            @Override
            public void finishCreateView(ViewDataBinding binding) {
                mBinding = (FragmentHomeBinding) binding;
                mBinding.getRoot().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showErrorPage();
                    }
                }, 3000);
            }
        };
    }
}
