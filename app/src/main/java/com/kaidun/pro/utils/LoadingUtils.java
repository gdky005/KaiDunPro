package com.kaidun.pro.utils;

import android.content.Context;

import com.kaidun.pro.views.LoadingDialog;

/**
 * Created by Doraemon on 2018/1/26.
 */

public class LoadingUtils {
    private static LoadingDialog materialDialog;

    public static void show(Context context) {
        materialDialog = new LoadingDialog(context);
        if (!materialDialog.isShowing()) {
            materialDialog.setCanceledOnTouchOutside(false);
            materialDialog.setCancelable(false);
            materialDialog.show();
        }
    }


    public static void dismiss() {
        if (materialDialog != null && materialDialog.isShowing()) {
            materialDialog.dismiss();
        }
        materialDialog = null;
    }
}
