package com.cmcj.gmj.localapp;

import android.app.Application;

import com.cmcj.gmj.localapp.utils.LocalLogger;

/**
 * Created by guomaojian on 16/10/10.
 */

public class LocalApplication extends Application {

    public static LocalApplication sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        LocalLogger.d("aaa");
    }
}
