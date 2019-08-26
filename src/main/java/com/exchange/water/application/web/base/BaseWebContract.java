package com.exchange.water.application.web.base;


import com.exchange.water.application.base.Contract;

/**
 * Created by lion on 2017/6/15.
 */

public interface BaseWebContract {

    interface Presenter extends Contract.BasePresenter {

        void showShare(String params);

    }

    interface View<T extends Presenter> extends Contract.BaseView<T> {
        android.view.View getLayoutBase();

        void post(Runnable runnable);
    }

}
