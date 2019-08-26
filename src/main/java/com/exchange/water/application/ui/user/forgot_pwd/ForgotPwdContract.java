package com.exchange.water.application.ui.user.forgot_pwd;



import com.exchange.water.application.base.Contract;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface ForgotPwdContract {
    interface View extends Contract.BaseView<Presenter> {

    }

    interface Presenter extends Contract.BasePresenter {

    }

    interface PhoneView extends Contract.BaseView<PhonePresenter> {


        void phoneForgotCodeSuccess(String obj);

        void phoneForgotCodeFail(Integer code, String toastMessage);

        void forgotPwdSuccess(String obj);

        void forgotPwdFail(Integer code, String toastMessage);

    }

    interface PhonePresenter extends Contract.BasePresenter {

        void phoneForgotCode(String phone, String mAreacode);

        void forgotPwd(String account, String code, String mAreacode, String password);

    }


}
