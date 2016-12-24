package com.pansijing.common.utils;

import android.app.Application;

/**
 * 文件名:
 * 描    述: [该类的简要描述]
 * 创建人: pighead4u
 * 创建时间: 2016/12/24-下午4:56
 * <p>
 * 修改人：
 * 修改时间:
 * 修改内容：[修改内容]
 */

public final class AppUtils {

    static Application sApp;

    private AppUtils() {
        throw new IllegalStateException("no new AppUtils()");
    }

    public static void init(Application application) {
        sApp = application;
    }

    public static Application getApplication() {
        return  sApp;
    }

}
