package com.cmcj.gmj.localapp.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cmcj.gmj.localapp.base.presenter.BasePresenter;

/**
 * Created by guomaojian on 16/9/28.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    public P mPresenter;

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getPresenter() != null)
            getPresenter().onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getPresenter() != null)
            getPresenter().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null)
            getPresenter().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getPresenter() != null)
            getPresenter().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null)
            getPresenter().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null)
            getPresenter().onDestroy();
    }
}
