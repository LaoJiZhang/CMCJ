package com.pansijing.common.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.pansijing.common.BuildConfig;
import com.pansijing.common.database.DBManager;
import com.pansijing.common.network.RetrofitService;
import com.squareup.leakcanary.LeakCanary;

/**
 * 文件名: InitManager
 * 描    述: 初始化管理类
 * 创建人: pighead4u
 * 创建时间: 2016/12/24-下午4:03
 * <p>
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */

public final class InitManager {

    private InitManager() {
        throw new IllegalStateException("no new InitManager()");
    }

    public static void init(Application application) {

        asynchronousInit(application);
        
        synchronousInit(application);
    }

    private static void synchronousInit(Application application) {
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(application)) {
                // application process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in application process.
                return;
            }
            LeakCanary.install(application);

            Stetho.initializeWithDefaults(application);
        }

        AppUtils.init(application);
        // Retrofit 初始化
        RetrofitService.createRetrofit();
        DBManager.createInstance(application);
        Fresco.initialize(application);
    }

    private static void asynchronousInit(final Application application) {
        
    }
}
