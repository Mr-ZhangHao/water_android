package com.exchange.water.application.ui.user.bind_email;


import com.exchange.water.application.base.Contract;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface BindEmailContract {
    interface View extends Contract.BaseView<Presenter> {

        void bindEmailSuccess(String obj);

        void bindEmailFail(Integer code, String toastMessage);

        void sendSuccess(String obj);

        void sendCodeFail(Integer code, String toastMessage);
        void sendEmailSuccess(String obj);

        void sendEmailCodeFail(Integer code, String toastMessage);
        void bindPhoneSuccess(String obj);

        void bindPhoneFail(Integer code, String toastMessage);

    }

    interface Presenter extends Contract.BasePresenter {
        void sendCode(String phone,String are, String data);
        void sendEmailCode(String phone, String data);
        void bindEmail(String token, String phone, String code);
        void bindPhone(String token, String phone, String code,String mArea);


    }
}
