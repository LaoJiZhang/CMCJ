package com.cmcj.gmj.localapp.main.modle;

import com.cmcj.gmj.localapp.main.presenter.HomePresenter;

/**
 * Created by guomaojian on 16/11/16.
 */

public interface IHomeModel {

    void getDouBanTop250(HomePresenter presenter);

    class Factory {
        public static HomeModel create() {
            return new HomeModel();
        }
    }
}
