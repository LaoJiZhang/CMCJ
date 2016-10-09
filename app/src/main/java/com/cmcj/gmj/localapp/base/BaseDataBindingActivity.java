package com.cmcj.gmj.localapp.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.databinding.ActivityLayoutBinding;

/**
 * Created by guomaojian on 16/9/28.
 */

public abstract class BaseDataBindingActivity<P extends BasePresenter> extends BaseActivity<P> {

    private ActivityProxy mActivityProxy;
    private ActivityTitleInfo mActivityTitleInfo;
    private ActivityLayoutBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTitleInfo = createTitleInfo();
        mActivityProxy = createActivityProxy();
        mPresenter = (P) mActivityProxy.createActivityPresenter();

        mBinding = DataBindingUtil.inflate(this.getLayoutInflater(), R.layout.activity_layout, null, false);
        mBinding.publicHeader.publicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBinding.publicHeader.publicSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivityTitleInfo != null)
                    mActivityTitleInfo.clickListener.onClick(v);
            }
        });
        mBinding.setInfo(mActivityTitleInfo);
        ViewDataBinding contentViewDataBinding = DataBindingUtil.inflate(this.getLayoutInflater(), mActivityProxy.getContentLayoutId(), null, false);
        mBinding.publicContent.addView(contentViewDataBinding.getRoot());
        setContentView(mBinding.getRoot());

        mActivityProxy.finishCreateDataBinding(contentViewDataBinding);
    }

    public abstract ActivityProxy createActivityProxy();

    public abstract ActivityTitleInfo createTitleInfo();

    public interface ActivityProxy {

        int getContentLayoutId();

        BasePresenter createActivityPresenter();

        void finishCreateDataBinding(ViewDataBinding viewDataBinding);
    }

    public static class ActivityTitleInfo {
        public String title;
        public View.OnClickListener clickListener;

        public ActivityTitleInfo(String title) {
            this.title = title;
        }

        public ActivityTitleInfo(String title, View.OnClickListener listener) {
            this.title = title;
            this.clickListener = listener;
        }
    }
}
