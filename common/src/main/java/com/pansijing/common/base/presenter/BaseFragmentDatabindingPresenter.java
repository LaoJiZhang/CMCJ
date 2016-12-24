package com.pansijing.common.base.presenter;

import com.pansijing.common.base.component.BaseDataBindingActivity;
import com.pansijing.common.base.view.IFragmentView;

/**
 * Created by guomaojian on 16/11/5.
 */

public abstract class BaseFragmentDatabindingPresenter<V extends IFragmentView, A extends BaseDataBindingActivity> extends BaseFragmentPresenter<V, A> {

    public BaseFragmentDatabindingPresenter(V view) {
        super(view);
        finishCreatedPresenter();
    }

    public abstract void finishCreatedPresenter();
}
