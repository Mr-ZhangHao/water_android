package com.exchange.water.application.web.fullscreen;


import com.exchange.water.application.web.base.BaseWebContract;

/**
 * Created by lion on 2017/5/4.
 */

public interface WebFullscreenContract {

    interface Presenter extends BaseWebContract.Presenter {
    }

    interface View extends BaseWebContract.View<Presenter> {
    }

}
