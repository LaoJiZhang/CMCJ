package com.cmcj.gmj.localapp.main;

import android.view.View;

import com.cmcj.gmj.localapp.base.presenter.BaseDatabindingPresenter;
import com.cmcj.gmj.localapp.base.view.IBaseView;
import com.cmcj.gmj.localapp.utils.LogUtils;
import com.cmcj.gmj.localapp.utils.ToastUtils;

/**
 * Created by guomaojian on 16/9/28.
 */

public class MainPresenter extends BaseDatabindingPresenter<IMain, MainActivity> {

    public MainPresenter(IBaseView view) {
        super(view);
    }

    @Override
    protected void finishCreatePresenter() {
        LogUtils.d(this.getClass().getSimpleName(), "finishCreatePresenter");
    }

    public void getStr() {
        LogUtils.d(this.getClass().getSimpleName(), "getStr");
    }

    public void onClickHomeBinding(View view) {
        getView().switchTab(MainActivity.MainTab.MAIN_HOME);
        ToastUtils.showToast("onClickHomeBinding");
    }

    public void onClickNetworkBinding(View view) {
        getView().switchTab(MainActivity.MainTab.MAIN_NEWWORK);
        ToastUtils.showToast("onClickNetworkBinding 123");
    }

    public void onClickDatabaseBinding(View view) {
        getView().switchTab(MainActivity.MainTab.MAIN_DATABASE);
        ToastUtils.showToast("onClickDatabaseBinding");
    }

    public void onClickNewBinding(View view) {
        getView().switchTab(MainActivity.MainTab.MAIN_NEW);
        ToastUtils.showToast("onClickNewBinding");
    }
}
