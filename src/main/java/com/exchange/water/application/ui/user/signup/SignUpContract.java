package com.exchange.water.application.ui.user.signup;



import com.exchange.water.application.base.Contract;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface SignUpContract {
    interface View extends Contract.BaseView<Presenter> {

    }

    interface Presenter extends Contract.BasePresenter {

    }

    interface PhoneView extends Contract.BaseView<PhonePresenter> {

        void phoneCodeSuccess(String obj);

        void phoneCodeFail(Integer code, String toastMessage);

        void signUpByPhoneSuccess(String obj);

        void signUpByPhoneFail(Integer code, String toastMessage);

        void signUpByEmailSuccess(String obj);

        void signUpByEmailFail(Integer code, String toastMessage);


    }

    interface PhonePresenter extends Contract.BasePresenter {

        void phoneCode(String phone, String mAreacode,String  check_code);

        void signUpByPhone(String mAreacode,String mAccount, String password, String mCode,String tuijianma);
        void signUpByEmail(String mAccount, String password, String mCode,String tuijianma);


    }


}
