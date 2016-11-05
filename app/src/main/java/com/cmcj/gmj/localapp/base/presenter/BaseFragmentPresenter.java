package com.cmcj.gmj.localapp.base.presenter;

import com.cmcj.gmj.localapp.base.activity.BaseActivity;
import com.cmcj.gmj.localapp.base.view.IBaseFragmentView;

/**
 * Created by guomaojian on 16/11/5.
 */

public abstract class BaseFragmentPresenter<V extends IBaseFragmentView, A extends BaseActivity> {

    public abstract void finishCreatedPresenter();

    public abstract boolean isViewAttach();

    public abstract V getView();

    public abstract A getActivity();

    public abstract void onCreate();

    public abstract void onCreateView();

    public abstract void onViewCreated();

    public abstract void onStart();

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onStop();

    public abstract void OnDestory();
}
