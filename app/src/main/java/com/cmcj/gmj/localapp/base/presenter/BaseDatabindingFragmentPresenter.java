package com.cmcj.gmj.localapp.base.presenter;

import android.support.v4.app.Fragment;

import com.cmcj.gmj.localapp.base.activity.BaseDataBindingActivity;
import com.cmcj.gmj.localapp.base.view.IBaseFragmentView;
import com.cmcj.gmj.localapp.utils.LogUtils;

import java.lang.ref.WeakReference;

/**
 * Created by guomaojian on 16/11/5.
 */

public abstract class BaseDatabindingFragmentPresenter<V extends IBaseFragmentView, A extends BaseDataBindingActivity> extends BaseFragmentPresenter {

    private WeakReference<IBaseFragmentView> mWefView;

    public BaseDatabindingFragmentPresenter(IBaseFragmentView view) {
        mWefView = new WeakReference<IBaseFragmentView>(view);
        finishCreatedPresenter();
    }

    @Override
    public boolean isViewAttach() {
        return mWefView.get() != null;
    }

    @Override
    public V getView() {
        if (isViewAttach())
            return (V) ((Fragment) mWefView.get()).getActivity();
        return null;
    }

    @Override
    public A getActivity() {
        if (isViewAttach())
            return (A) mWefView.get();
        return null;
    }

    @Override
    public void onCreate() {
        LogUtils.i(this.getClass().getSimpleName(), "onCreate");
    }

    @Override
    public void onCreateView() {
        LogUtils.i(this.getClass().getSimpleName(), "onCreateView");
    }

    @Override
    public void onViewCreated() {
        LogUtils.i(this.getClass().getSimpleName(), "onViewCreated");
    }

    @Override
    public void onStart() {
        LogUtils.i(this.getClass().getSimpleName(), "onStart");
    }

    @Override
    public void onResume() {
        LogUtils.i(this.getClass().getSimpleName(), "onResume");
    }

    @Override
    public void onPause() {
        LogUtils.i(this.getClass().getSimpleName(), "onPause");
    }

    @Override
    public void onStop() {
        LogUtils.i(this.getClass().getSimpleName(), "onStop");
    }

    @Override
    public void OnDestory() {
        LogUtils.i(this.getClass().getSimpleName(), "OnDestory");
    }
}
