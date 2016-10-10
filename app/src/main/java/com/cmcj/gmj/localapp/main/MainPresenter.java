package com.cmcj.gmj.localapp.main;

import android.view.View;

import com.cmcj.gmj.localapp.base.BaseDatabindingPresenter;
import com.cmcj.gmj.localapp.base.IBaseView;
import com.cmcj.gmj.localapp.utils.LocalLogger;
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
        LocalLogger.d(this.getClass().getSimpleName(), "finishCreatePresenter");
    }

    public void getStr() {
        LocalLogger.d(this.getClass().getSimpleName(), "getStr");
    }

    public void onClickNetworkBinding(View view) {
        ToastUtils.showToast("onClickNetworkBinding 123");
    }

    public void onClickHomeBinding(View view) {
        ToastUtils.showToast("onClickHomeBinding");
    }

    public void onClickDatabaseBinding(View view) {
        ToastUtils.showToast("onClickDatabaseBinding");
    }

    public void onClickNewBinding(View view) {
        ToastUtils.showToast("onClickNewBinding");
    }
}
