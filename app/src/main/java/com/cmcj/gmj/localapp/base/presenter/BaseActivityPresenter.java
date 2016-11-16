package com.cmcj.gmj.localapp.base.presenter;

import com.cmcj.gmj.localapp.base.component.BaseActivity;
import com.cmcj.gmj.localapp.base.view.IActivityView;

import java.lang.ref.WeakReference;

/**
 * Created by guomaojian on 16/9/28.
 */

public class BaseActivityPresenter<V extends IActivityView, A extends BaseActivity> extends BasePresenter<V, A> {

    public BaseActivityPresenter(V view) {
        super(view);
    }

    @Override
    public A getActivity(WeakReference<V> wefView) {
        if (wefView != null && wefView.get() != null) {
            return (A) wefView.get();
        }
        return null;
    }
}
