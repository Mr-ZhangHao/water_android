package com.exchange.water.application.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.exchange.water.application.R;


/**
 * author: wuzongjie
 * time  : 2018/4/27 0027 15:05
 * desc  :
 */

public class LoadDialog extends Dialog{

    public LoadDialog(@NonNull Context context) {
        super(context, R.style.custom_dialogs);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);
    }
}
