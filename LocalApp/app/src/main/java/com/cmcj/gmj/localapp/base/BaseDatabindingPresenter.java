package com.cmcj.gmj.localapp.base;

/**
 * Created by guomaojian on 16/10/8.
 */

public abstract class BaseDatabindingPresenter<V extends IBaseView, A extends BaseActivity> extends BasePresenter<V, A> {

    public BaseDatabindingPresenter(IBaseView view) {
        super(view);
        finishCreatePresenter();
    }

    protected abstract void finishCreatePresenter();
}
