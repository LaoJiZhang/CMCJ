package com.cmcj.gmj.localapp.detail.presenter;

import com.pansijing.common.base.presenter.BaseActivityDatabindingPresenter;
import com.cmcj.gmj.localapp.detail.view.DetailActivity;
import com.cmcj.gmj.localapp.detail.view.IDetail;

/**
 * Created by guomaojian on 16/12/4.
 */

public class DetailPresenter extends BaseActivityDatabindingPresenter<IDetail, DetailActivity> {


    public DetailPresenter(IDetail view) {
        super(view);
    }

    @Override
    protected void finishCreatePresenter() {

    }
}
