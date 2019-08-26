package com.exchange.water.application.web.fullscreen;

import android.app.Activity;

import com.exchange.water.application.web.base.BaseWebPresenter;


/**
 * Created by lion on 2017/5/4.
 */

public class WebFullscreenPresenter extends BaseWebPresenter<WebFullscreenContract.View> implements WebFullscreenContract.Presenter {

    public WebFullscreenPresenter(Activity context, WebFullscreenContract.View baseWebView) {
        super(context, baseWebView);
    }
}
