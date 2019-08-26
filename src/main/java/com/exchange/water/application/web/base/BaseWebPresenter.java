package com.exchange.water.application.web.base;

import android.app.Activity;
import android.text.TextUtils;

import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.utils.WonderfulToastUtils;


/**
 * Created by lion on 2017/6/15.
 */

public class BaseWebPresenter<T extends BaseWebContract.View> implements BaseWebContract.Presenter {

    private Activity mContext;
    private T mBaseWebView;

//    private ShareWindow mShareWindow;

    public BaseWebPresenter(Activity context, T baseWebView) {
        this.mContext = context;
        this.mBaseWebView = baseWebView;
//        this.mShareWindow = new ShareWindow(context);
    }

    @Override
    public void showShare(String params) {
        if (TextUtils.isEmpty(params)) {
            return;
        }

        final String[] strs = params.split("\\|");
        if (strs.length == 4) {
            mBaseWebView.post(new Runnable() {
                @Override
                public void run() {
//                    mShareWindow.shareUrl(mBaseWebView.getLayoutBase(), strs[0], strs[1], strs[3], strs[2]);
                }
            });
            if (!MyApplication.getApp().isReleased()) {
                WonderfulToastUtils.showToast(strs[3]);
            }
        }
    }
}
