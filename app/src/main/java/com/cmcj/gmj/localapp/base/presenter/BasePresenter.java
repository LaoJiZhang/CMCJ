package com.cmcj.gmj.localapp.base.presenter;

import com.cmcj.gmj.localapp.base.component.BaseActivity;
import com.cmcj.gmj.localapp.base.network.RetrofitService;
import com.cmcj.gmj.localapp.base.view.IAndroidView;
import com.cmcj.gmj.localapp.utils.LogUtils;

import java.lang.ref.WeakReference;

/**
 * Created by guomaojian on 16/9/28.
 */

public abstract class BasePresenter<V extends IAndroidView, A extends BaseActivity> {

    private WeakReference<V> mWefView;

    public BasePresenter(V view) {
        mWefView = (WeakReference<V>) new WeakReference<>(view);
    }

    public V getView() {
        return (V) mWefView.get();
    }

    public boolean isViewAttach() {
        return getView() != null && getActivity() != null && !getActivity().isFinishing();
    }

    public abstract A getActivity(WeakReference<V> wefView);

    public A getActivity() {
        return getActivity(mWefView);
    }

    public void onCreate() {
        LogUtils.i(this.getClass().toString() + "onCreate");
    }

    public void onStart() {
        LogUtils.i(this.getClass().toString() + "onStart");
    }

    public void onResume() {
        LogUtils.i(this.getClass().toString() + "onResume");
    }

    public void onPause() {
        LogUtils.i(this.getClass().toString() + "onPause");
    }

    public void onStop() {
        LogUtils.i(this.getClass().toString() + "onStop");
    }

    public void onDestroy() {
        LogUtils.i(this.getClass().toString() + "onDestroy");
        RetrofitService.removeCurrentCall(getActivity());
    }
}
