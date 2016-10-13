package com.cmcj.gmj.localapp.base.presenter;

import com.cmcj.gmj.localapp.base.activity.BaseActivity;
import com.cmcj.gmj.localapp.base.network.RetrofitService;
import com.cmcj.gmj.localapp.base.view.IBaseView;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

/**
 * Created by guomaojian on 16/9/28.
 */

public class BasePresenter<V extends IBaseView, A extends BaseActivity> {

    private WeakReference<IBaseView> mWefView;

    public BasePresenter(IBaseView view) {
        mWefView = new WeakReference<>(view);
    }

    public V getView() {
        return (V) mWefView.get();
    }

    public boolean isAttachView() {
        return getView() != null;
    }

    public A getActivity() {
        if (mWefView.get() != null)
            return (A) mWefView.get();
        return null;
    }

    public void onCreate() {
        EventBus.getDefault().register(getActivity());
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(getActivity());
        RetrofitService.removeCurrentCall(getActivity());
    }
}
