package com.pansijing.common.base.presenter;

import com.pansijing.common.base.component.BaseActivity;
import com.pansijing.common.base.view.IActivityView;

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
