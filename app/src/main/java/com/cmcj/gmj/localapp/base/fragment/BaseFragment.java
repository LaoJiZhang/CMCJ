package com.cmcj.gmj.localapp.base.fragment;

import android.support.v4.app.Fragment;

import com.cmcj.gmj.localapp.base.presenter.BaseFragmentPresenter;

/**
 * Created by guomaojian on 16/11/5.
 */

public abstract class BaseFragment<P extends BaseFragmentPresenter> extends Fragment {

    protected abstract P getPresenter();
}
