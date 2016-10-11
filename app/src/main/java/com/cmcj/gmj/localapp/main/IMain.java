package com.cmcj.gmj.localapp.main;

import com.cmcj.gmj.localapp.base.view.IBaseView;

/**
 * Created by guomaojian on 16/9/28.
 */

public interface IMain extends IBaseView<MainPresenter> {

    void switchTab(int position);
}
