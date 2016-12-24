package com.pansijing.common.utils;

import android.widget.Toast;

/**
 * Created by guomaojian on 16/10/10.
 */

public class ToastUtils {

    public static void showToast(int strId) {
        showToast(AppUtils.getApplication().getResources().getString(strId));
    }

    public static void showToast(String message) {
        Toast.makeText(AppUtils.getApplication(), message, Toast.LENGTH_SHORT).show();
    }
}
