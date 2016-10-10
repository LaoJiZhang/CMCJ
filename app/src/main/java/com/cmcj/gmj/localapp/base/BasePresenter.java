package com.cmcj.gmj.localapp.base;

import com.cmcj.gmj.localapp.utils.LocalLogger;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

/**
 * Created by guomaojian on 16/9/28.
 */

public class BasePresenter<V extends IBaseView, A extends BaseActivity> {

    protected WeakReference<IBaseView> mWefView;

    protected BasePresenter(IBaseView view) {
        mWefView = new WeakReference<>(view);
    }

    protected V getView() {
        return (V) mWefView.get();
    }

    protected boolean isAttachView() {
        return getView() != null;
    }

    protected A getActivity() {
        LocalLogger.d(this.getClass().getSimpleName(), "BasePresenter getActivity");
        if (mWefView.get() != null)
            return (A) mWefView.get();
        return null;
    }

    protected void onCreate() {
        EventBus.getDefault().register(getActivity());
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
        EventBus.getDefault().unregister(getActivity());
    }
}
