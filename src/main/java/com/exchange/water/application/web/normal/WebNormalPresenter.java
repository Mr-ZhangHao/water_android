package com.exchange.water.application.web.normal;

import android.app.Activity;

import com.exchange.water.application.web.base.BaseWebPresenter;


/**
 * Created by lion on 2017/5/4.
 */

public class WebNormalPresenter extends BaseWebPresenter<WebNormalContract.View>
        implements WebNormalContract.Presenter {

    public WebNormalPresenter(Activity context, WebNormalContract.View baseWebView) {
        super(context, baseWebView);
    }
}
