package com.exchange.water.application.web.normal;


import com.exchange.water.application.web.base.BaseWebContract;

/**
 * Created by lion on 2017/5/4.
 */

public interface WebNormalContract {

    interface Presenter extends BaseWebContract.Presenter {
    }

    interface View<Presenter extends BaseWebContract.Presenter>
            extends BaseWebContract.View<Presenter> {
    }

}
