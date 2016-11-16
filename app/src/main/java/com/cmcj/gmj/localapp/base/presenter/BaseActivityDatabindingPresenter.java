package com.cmcj.gmj.localapp.base.presenter;

import com.cmcj.gmj.localapp.base.component.BaseActivity;
import com.cmcj.gmj.localapp.base.view.IActivityView;

/**
 * Created by guomaojian on 16/10/8.
 */

public abstract class BaseActivityDatabindingPresenter<V extends IActivityView, A extends BaseActivity> extends BaseActivityPresenter<V, A> {

    public BaseActivityDatabindingPresenter(V view) {
        super(view);
        finishCreatePresenter();
    }

    protected abstract void finishCreatePresenter();
}
