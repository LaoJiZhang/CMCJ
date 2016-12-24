package com.pansijing.common.base.presenter;

import android.support.v4.app.Fragment;

import com.pansijing.common.base.component.BaseActivity;
import com.pansijing.common.base.view.IFragmentView;
import com.pansijing.common.utils.LogUtils;

import java.lang.ref.WeakReference;

/**
 * Created by guomaojian on 16/11/5.
 */

public class BaseFragmentPresenter<V extends IFragmentView, A extends BaseActivity> extends BasePresenter<V, A> {

    public BaseFragmentPresenter(V view) {
        super(view);
    }

    @Override
    public A getActivity(WeakReference<V> wefView) {
        if (wefView != null && wefView.get() != null) {
            return (A) ((Fragment) wefView.get()).getActivity();
        }
        return null;
    }

    public void onCreateView() {
        LogUtils.i(this.getClass().toString() + "onCreateView");
    }

    public void onViewCreated() {
        LogUtils.i(this.getClass().toString() + "onViewCreated");
    }

    public void onDestroyView() {
        LogUtils.i(this.getClass().toString() + "onDestroyView");
    }

    public void onDetach() {
        LogUtils.i(this.getClass().toString() + "onDetach");
    }

}
