package com.cmcj.gmj.localapp.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by guomaojian on 16/9/28.
 */

public abstract class BaseDataBindingActivity<P extends BasePresenter> extends BaseActivity<P> {

    private ActivityProxy mActivityProxy;
    private ViewDataBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityProxy = createActivityProxy();
        mBinding = DataBindingUtil.inflate(this.getLayoutInflater(), mActivityProxy.getContentLayoutId(), null, false);
        setContentView(mBinding.getRoot());
        mPresenter = (P) mActivityProxy.createActivityPresenter();
        mActivityProxy.finishCreateDataBinding(mBinding);
    }

    public abstract ActivityProxy createActivityProxy();

    public interface ActivityProxy {

        int getContentLayoutId();

        BasePresenter createActivityPresenter();

        void finishCreateDataBinding(ViewDataBinding viewDataBinding);
    }
}
