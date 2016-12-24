package com.cmcj.gmj.localapp.utils;

import android.util.Log;

import com.cmcj.gmj.localapp.BuildConfig;

public class LogUtils {

    private static final String TAG = LogUtils.class.getSimpleName();

    private LogUtils() {
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, msg);
        }
//        LogToFile.getInstance().log(TAG + " : " + msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }


    public static void e(String tag, String msg, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg, throwable);
        }
//        LogToFile.getInstance().log(tag + " : " + msg+" \n throwable = "+(throwable == null ? "null":throwable.getMessage()));
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }
}

