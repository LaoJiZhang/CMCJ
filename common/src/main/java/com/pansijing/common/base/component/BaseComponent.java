package com.pansijing.common.base.component;

import com.pansijing.common.base.presenter.BasePresenter;

/**
 * Created by guomaojian on 16/11/16.
 */

public interface BaseComponent<P extends BasePresenter> {
    
    public P getPresenter();
}
