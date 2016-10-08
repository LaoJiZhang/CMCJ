package com.cmcj.gmj.localapp.base;

import java.lang.ref.WeakReference;

/**
 * Created by guomaojian on 16/9/28.
 */

public class BasePresenter<V extends IBaseView, A extends BaseActivity> {

    public WeakReference<IBaseView> mWefView;

    public BasePresenter(IBaseView view) {
        mWefView = new WeakReference<>(view);
    }

    V getView() {
        return (V) mWefView.get();
    }

    boolean isAttachView() {
        return getView() != null;
    }

    A getActivity() {
        return (A) getActivity();
    }

    protected void onStart() {
    }

    protected void onResume() {
    }

    protected void onPause() {
    }

    protected void onStop() {
    }

    protected void onDestroy() {
    }
}
