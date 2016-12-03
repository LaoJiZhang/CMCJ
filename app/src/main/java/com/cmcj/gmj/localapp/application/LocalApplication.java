package com.cmcj.gmj.localapp.application;

import android.app.Application;

import com.cmcj.gmj.localapp.base.database.DBManager;
import com.cmcj.gmj.localapp.base.network.RetrofitService;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by guomaojian on 16/10/10.
 */

public class LocalApplication extends Application {

    public static LocalApplication sApp;


    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        init();
    }

    private void init() {
        if (LocalAppConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);

            Stetho.initializeWithDefaults(this);
        }
        // Retrofit 初始化
        RetrofitService.createRetrofit();
        DBManager.createInstance(this);
        Fresco.initialize(this);
    }
}
