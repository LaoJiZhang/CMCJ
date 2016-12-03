package com.cmcj.gmj.localapp.main.view;

import com.cmcj.gmj.localapp.base.view.IFragmentView;
import com.cmcj.gmj.localapp.main.presenter.HomePresenter;

/**
 * Created by guomaojian on 16/11/5.
 */

public interface IHome extends IFragmentView<HomePresenter> {
    void getMovieSuccess();
}
