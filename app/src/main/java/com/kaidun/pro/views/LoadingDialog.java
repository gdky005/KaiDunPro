package com.kaidun.pro.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.kaidun.pro.R;

/**
 * Created by Doraemon on 2018/1/26.
 * loading
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.LoadingDialogTheme);
        init();
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }

    private void init() {
        setContentView(R.layout.layout_loading);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
