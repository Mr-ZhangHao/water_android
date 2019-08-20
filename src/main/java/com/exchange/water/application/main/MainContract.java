package com.exchange.water.application.main;

import android.app.Activity;

import com.exchange.water.application.base.Contract;


/**
 * Created by lion on 2017/3/14.
 */

public  interface MainContract {


    interface View extends Contract.BaseView<Presenter> {

    }


    interface Presenter extends Contract.BasePresenter {


        boolean checkHasSavePermission(Activity activity);

        boolean requestSavePermission(Activity activity);

    }


}
