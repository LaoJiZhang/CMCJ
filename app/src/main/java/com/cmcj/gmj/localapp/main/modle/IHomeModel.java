package com.cmcj.gmj.localapp.main.modle;

import com.cmcj.gmj.localapp.base.IModel;
import com.cmcj.gmj.localapp.main.presenter.HomePresenter;

/**
 * Created by guomaojian on 16/11/16.
 */

public abstract class IHomeModel implements IModel {

    public abstract void getDouBanTop250(HomePresenter presenter);

    @Override
    public void onDestory() {
    }

    public static class Factory {
        public static HomeModel create() {
            return new HomeModel();
        }
    }
}
