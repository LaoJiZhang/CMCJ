package com.cmcj.gmj.localapp.base.presenter;

import com.cmcj.gmj.localapp.base.component.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.view.IFragmentView;

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
