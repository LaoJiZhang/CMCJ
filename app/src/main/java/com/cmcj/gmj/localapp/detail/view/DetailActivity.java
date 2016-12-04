package com.cmcj.gmj.localapp.detail.view;

import android.databinding.ViewDataBinding;

import com.cmcj.gmj.localapp.R;
import com.cmcj.gmj.localapp.base.component.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.presenter.BasePresenter;
import com.cmcj.gmj.localapp.detail.presenter.DetailPresenter;

/**
 * Created by guomaojian on 16/12/4.
 */

public class DetailActivity extends BaseDataBindingActivity<DetailPresenter> implements IDetail {

    @Override
    public ActivityProxy createActivityProxy() {
        return new ActivityProxy() {
            @Override
            public int getContentLayoutId() {
                return R.layout.activity_detail;
            }

            @Override
            public BasePresenter createActivityPresenter() {
                return new DetailPresenter(DetailActivity.this);
            }

            @Override
            public void finishCreateDataBinding(ViewDataBinding viewDataBinding) {

            }
        };
    }

    @Override
    public ActivityTitleInfo createTitleInfo() {
        ActivityTitleInfo titleInfo = new ActivityTitleInfo("Second");
        return titleInfo;
    }
}