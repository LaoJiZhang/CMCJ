package com.cmcj.gmj.localapp.main.view;

import android.support.v7.widget.RecyclerView;

import com.pansijing.common.base.view.IFragmentView;
import com.cmcj.gmj.localapp.main.presenter.HomePresenter;

/**
 * Created by guomaojian on 16/11/5.
 */

public interface IHome extends IFragmentView<HomePresenter> {
    void getMovieSuccess();

    void showNetError();

    RecyclerView getRecyclerView();
}
