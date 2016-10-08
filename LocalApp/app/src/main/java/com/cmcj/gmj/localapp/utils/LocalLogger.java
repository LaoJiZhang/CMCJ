package com.cmcj.gmj.localapp.utils;

import android.util.Log;

public class LocalLogger {

    private static final String TAG = LocalLogger.class.getSimpleName();

    private LocalLogger() {
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (true) {
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
        if (true) {
            Log.i(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }

    public static void d(String tag, String msg) {
        if (true) {
            Log.d(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }

    public static void e(String tag, String msg) {
        if (true) {
            Log.e(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }


    public static void e(String tag, String msg, Throwable throwable) {
        if (true) {
            Log.e(tag, msg, throwable);
        }
//        LogToFile.getInstance().log(tag + " : " + msg+" \n throwable = "+(throwable == null ? "null":throwable.getMessage()));
    }

    public static void w(String tag, String msg) {
        if (true) {
            Log.w(tag, msg);
        }
//        LogToFile.getInstance().log(tag + " : " + msg);
    }
}

