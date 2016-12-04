package com.cmcj.gmj.localapp.detailweb.presenter;

import android.text.TextUtils;

import com.cmcj.gmj.localapp.base.presenter.BaseActivityPresenter;
import com.cmcj.gmj.localapp.detailweb.view.CommonWebActivity;
import com.cmcj.gmj.localapp.detailweb.view.ICommonWeb;

/**
 * Created by guomaojian on 16/12/4.
 */

public class CommonWebPresenter extends BaseActivityPresenter<ICommonWeb, CommonWebActivity> {


    public CommonWebPresenter(ICommonWeb view) {
        super(view);
    }

    public String getWebUrl() {
        String url = getIntent().getStringExtra(CommonWebActivity.WEB_URL);
        return TextUtils.isEmpty(url) ? "https://github.com/guomaojian1992/CMCJ" : url;
    }
}
