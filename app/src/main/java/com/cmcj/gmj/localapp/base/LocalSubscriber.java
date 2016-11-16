package com.cmcj.gmj.localapp.base;

import com.cmcj.gmj.localapp.base.component.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.presenter.BasePresenter;

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
        if (mPresenter.isAttachView()) {
            ((A) mPresenter.getActivity()).showLoadingPage();
        }
        super.onStart();
    }

    @Override
    public void onCompleted() {
        if (mPresenter.isAttachView())
            ((A) mPresenter.getActivity()).showNormalPage();
    }

    @Override
    public void onError(Throwable e) {
        if (mPresenter.isAttachView())
            ((A) mPresenter.getActivity()).showErrorPage();
    }

    @Override
    public void onNext(Object o) {
        if (mPresenter.isAttachView()) {
            D data = (D) o;
            onSuccess(data);
        }
    }

    public abstract void onSuccess(D data);
}
