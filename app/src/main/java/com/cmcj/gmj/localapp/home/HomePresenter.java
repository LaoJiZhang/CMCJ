package com.cmcj.gmj.localapp.home;

import com.cmcj.gmj.localapp.base.BaseDatabindingPresenter;
import com.cmcj.gmj.localapp.base.IBaseView;
import com.cmcj.gmj.localapp.utils.LocalLogger;

/**
 * Created by guomaojian on 16/9/28.
 */

public class HomePresenter extends BaseDatabindingPresenter<IHome, MainActivity> {

    public HomePresenter(IBaseView view) {
        super(view);
    }

    @Override
    protected void finishCreatePresenter() {
        LocalLogger.d(this.getClass().getSimpleName(), "finishCreatePresenter");
    }

    public void getStr() {
        LocalLogger.d(this.getClass().getSimpleName(), "getStr");
    }
}
