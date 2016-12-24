package com.pansijing.common.base;

import com.pansijing.common.base.component.BaseDataBindingActivity;
import com.pansijing.common.base.presenter.BasePresenter;

import rx.Subscriber;

/**
 * Created by guomaojian on 16/11/16.
 */

public abstract class LocalSubscriber<D extends Object, A extends BaseDataBindingActivity> extends Subscriber {

    private BasePresenter mPresenter;

    public LocalSubscriber(BasePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onStart() {
        if (mPresenter.isViewAttach()) {
            ((A) mPresenter.getActivity()).showLoadingPage();
        }
        super.onStart();
    }

    @Override
    public void onCompleted() {
        if (mPresenter.isViewAttach())
            ((A) mPresenter.getActivity()).showNormalPage();
    }

    @Override
    public void onError(Throwable e) {
        if (mPresenter.isViewAttach())
            ((A) mPresenter.getActivity()).showErrorPage();
    }

    @Override
    public void onNext(Object o) {
        if (mPresenter.isViewAttach()) {
            D data = (D) o;
            onSuccess(data);
        }
    }

    public abstract void onSuccess(D data);
}
