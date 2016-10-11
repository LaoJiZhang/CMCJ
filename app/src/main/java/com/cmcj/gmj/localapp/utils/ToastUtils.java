package com.cmcj.gmj.localapp.utils;

import android.widget.Toast;

import com.cmcj.gmj.localapp.application.LocalApplication;

/**
 * Created by guomaojian on 16/10/10.
 */

public class ToastUtils {

    public static void showToast(int strId) {
        showToast(LocalApplication.sApp.getResources().getString(strId));
    }

    public static void showToast(String message) {
        Toast.makeText(LocalApplication.sApp, message, Toast.LENGTH_SHORT).show();
    }
}
