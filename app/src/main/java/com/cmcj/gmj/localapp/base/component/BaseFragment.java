package com.cmcj.gmj.localapp.base.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmcj.gmj.localapp.base.presenter.BaseFragmentPresenter;

/**
 * Created by guomaojian on 16/11/5.
 */

public abstract class BaseFragment<P extends BaseFragmentPresenter> extends Fragment implements BaseComponent<P> {

    protected P mPresenter;

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getPresenter() != null)
            getPresenter().onCreateView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (getPresenter() != null) {
            getPresenter().onViewCreated();
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (getPresenter() != null)
            getPresenter().onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        if (getPresenter() != null)
            getPresenter().onDetach();
        super.onDetach();
    }
}