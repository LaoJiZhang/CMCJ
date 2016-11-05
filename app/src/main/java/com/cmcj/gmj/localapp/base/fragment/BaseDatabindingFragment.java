package com.cmcj.gmj.localapp.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmcj.gmj.localapp.base.presenter.BaseDatabindingFragmentPresenter;

/**
 * Created by guomaojian on 16/11/5.
 */

public abstract class BaseDatabindingFragment<P extends BaseDatabindingFragmentPresenter> extends BaseFragment<P> {

    private FragmentProxy mFragmentProxy;
    private ViewDataBinding mBinding;
    private P mPresenter;

    protected abstract FragmentProxy createFragmentProxy();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentProxy = createFragmentProxy();
        mPresenter = (P) mFragmentProxy.createPresenter();
        getPresenter().onCreate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getPresenter().onCreateView();
        mBinding = DataBindingUtil.inflate(inflater, mFragmentProxy.getRestId(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getPresenter().onViewCreated();
        mFragmentProxy.finishCreateView(mBinding);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().OnDestory();
    }

    @Override
    protected P getPresenter() {
        return mPresenter;
    }

    public interface FragmentProxy<P extends BaseDatabindingFragmentPresenter> {

        P createPresenter();

        int getRestId();

        void finishCreateView(ViewDataBinding binding);
    }
}
