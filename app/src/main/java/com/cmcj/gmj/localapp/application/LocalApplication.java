package com.cmcj.gmj.localapp.application;

import android.app.Application;

import com.pansijing.common.utils.InitManager;

/**
 * Created by guomaojian on 16/10/10.
 */

public class LocalApplication extends Application {

    public static LocalApplication sApp;


    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

        InitManager.init(this);
    }

}
