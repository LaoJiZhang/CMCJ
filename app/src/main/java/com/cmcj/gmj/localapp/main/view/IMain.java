package com.cmcj.gmj.localapp.main.view;

import com.cmcj.gmj.localapp.base.view.IActivityView;
import com.cmcj.gmj.localapp.main.presenter.MainPresenter;

/**
 * Created by guomaojian on 16/9/28.
 */

public interface IMain extends IActivityView<MainPresenter> {

    void switchTab(int position);
}
