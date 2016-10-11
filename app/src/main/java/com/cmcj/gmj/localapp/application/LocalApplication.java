package com.cmcj.gmj.localapp.application;

import android.app.Application;

import com.cmcj.gmj.localapp.utils.LocalLogger;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by guomaojian on 16/10/10.
 */

public class LocalApplication extends Application {

    public static LocalApplication sApp;
    public static boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();
        LocalLogger.d(this.getClass().getSimpleName(), " ++++++++++++ LocalApplication onCreate");
        sApp = this;
        init();
    }

    private void init() {
        if (!DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
    }
}
