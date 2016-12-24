package com.pansijing.common.base.presenter;

import com.pansijing.common.base.component.BaseActivity;
import com.pansijing.common.base.view.IActivityView;

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
