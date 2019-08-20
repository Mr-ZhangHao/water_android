package com.exchange.water.application.utils.okhttp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.exchange.water.application.R;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.base.ActivityManage;
import com.exchange.water.application.main.MainActivity;
import com.exchange.water.application.utils.WonderfulLogUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;

import java.io.IOException;
import java.net.UnknownHostException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/29.
 */

public abstract class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response) throws IOException {
        return response.body().string();
    }

    @Override
    public void onError(Request request, Exception e) {
        WonderfulLogUtils.logi("miao", e.toString().length()+"-666-");
        MyApplication.getApp().typeBiaoshi1=1+ MyApplication.getApp().typeBiaoshi1;

        if (MyApplication.getApp().typeBiaoshi1==2){
            MyApplication.getApp().typeBiaoshi1=0;
        }else {
            return;
        }

        if (checkInternet()){
            if (e instanceof UnknownHostException||MyApplication.getApp().typeBiaoshi2==404||e.toString().length()==197) {
                dialogT("请切换网络重试或系统升级维护中（升级进度详见PC官网）。");
            }
        }else {
            dialogT("手机网络无连接，请检查手机网络或者切换网络。");
            WonderfulToastUtils.showToast("请检查手机网络");
        }

    }
    private void dialogT(String title){
        if (MyApplication.getApp().typeBiaoshi==0){
            MyApplication.getApp().typeBiaoshi=1;
            AlertDialog dialog = new AlertDialog.Builder(ActivityManage.getInstance().getCurrentActivity(), R.style.custom_dialogs).setTitle("温馨提示").setNegativeButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MyApplication.getApp().typeBiaoshi=0;
                    dialog.cancel();
                    ActivityManage.finishAll();
                  System.exit(0);
                }
            }).create();
            dialog.setMessage(title);
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
        }
    }

    private boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        NetworkInfo.State mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if ((wifiState != null && wifiState == NetworkInfo.State.CONNECTED) || (mobileState != null && mobileState == NetworkInfo.State.CONNECTED)) {
           return true;
        }
        return false;
    }
}
