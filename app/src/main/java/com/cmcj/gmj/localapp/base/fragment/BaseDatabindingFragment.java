package com.cmcj.gmj.localapp.base.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.activity.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.presenter.BaseDatabindingFragmentPresenter;
import com.cmcj.gmj.localapp.databinding.FragmentLayoutBinding;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by guomaojian on 16/11/5.
 */

public abstract class BaseDatabindingFragment<P extends BaseDatabindingFragmentPresenter> extends BaseFragment<P> {

    private FragmentProxy mFragmentProxy;
    private FragmentLayoutBinding mFragmentLayoutBinding;
    private ViewDataBinding mContentBinding;
    private P mPresenter;
    private BaseDataBindingActivity.ActivityFlag mActivityFlag;

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
        mFragmentLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_layout, container, false);
        mFragmentLayoutBinding.setFlag(createActivityFlag());
        mFragmentLayoutBinding.publicErrNetwork.refreshRetryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(this);
            }
        });
        mContentBinding = DataBindingUtil.inflate(inflater, mFragmentProxy.getRestId(), null, false);
        mFragmentLayoutBinding.publicContent.addView(mContentBinding.getRoot());
        return mFragmentLayoutBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getPresenter().onViewCreated();
        mFragmentProxy.finishCreateView(mContentBinding);
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

    public BaseDataBindingActivity.ActivityFlag createActivityFlag() {
        mActivityFlag = new BaseDataBindingActivity.ActivityFlag(BaseDataBindingActivity.ActivityFlag.STATE_NORMAL);
        return mActivityFlag;
    }

    public void showLoadingPage() {
        mActivityFlag.flag = BaseDataBindingActivity.ActivityFlag.STATE_LOADING;
        mFragmentLayoutBinding.setFlag(mActivityFlag);
    }

    public void showNormalPage() {
        mActivityFlag.flag = BaseDataBindingActivity.ActivityFlag.STATE_NORMAL;
        mFragmentLayoutBinding.setFlag(mActivityFlag);
    }

    public void showErrorPage() {
        mActivityFlag.flag = BaseDataBindingActivity.ActivityFlag.STATE_ERROR;
        mFragmentLayoutBinding.setFlag(mActivityFlag);
    }

    public void showErrNetworkPage() {
        mActivityFlag.flag = BaseDataBindingActivity.ActivityFlag.STATE_ERR_NETWORK;
        mFragmentLayoutBinding.setFlag(mActivityFlag);
    }

    public void showEmptyPage() {
        mActivityFlag.flag = BaseDataBindingActivity.ActivityFlag.STATE_EMPTY;
        mFragmentLayoutBinding.setFlag(mActivityFlag);
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
