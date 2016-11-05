package com.cmcj.gmj.localapp.main.presenter;

import com.cmcj.gmj.localapp.base.presenter.BaseDatabindingFragmentPresenter;
import com.cmcj.gmj.localapp.base.view.IBaseFragmentView;
import com.cmcj.gmj.localapp.main.view.IHome;
import com.cmcj.gmj.localapp.main.view.MainActivity;
import com.cmcj.gmj.localapp.utils.LogUtils;

/**
 * Created by guomaojian on 16/11/5.
 */

public class HomePresenter extends BaseDatabindingFragmentPresenter<IHome, MainActivity> {

    public HomePresenter(IBaseFragmentView view) {
        super(view);
    }

    @Override
    public void finishCreatedPresenter() {
        LogUtils.i("finishCreatedPresenter");
    }
}
