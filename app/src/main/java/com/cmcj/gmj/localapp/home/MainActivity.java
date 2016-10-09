package com.cmcj.gmj.localapp.home;

import android.databinding.ViewDataBinding;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.BasePresenter;
import com.cmcj.gmj.localapp.databinding.ActivityMainBinding;

public class MainActivity extends BaseDataBindingActivity<HomePresenter> implements IHome {

    private ActivityMainBinding mBinding;

    @Override
    public ActivityProxy createActivityProxy() {
        return new ActivityProxy() {
            @Override
            public int getContentLayoutId() {
                return R.layout.activity_main;
            }

            @Override
            public BasePresenter createActivityPresenter() {
                return new HomePresenter(MainActivity.this);
            }

            @Override
            public void finishCreateDataBinding(ViewDataBinding viewDataBinding) {
                mBinding = (ActivityMainBinding) viewDataBinding;
                getPresenter().getStr();
            }
        };
    }
}
