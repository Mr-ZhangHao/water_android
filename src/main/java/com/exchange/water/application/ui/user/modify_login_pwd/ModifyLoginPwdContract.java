package com.exchange.water.application.ui.user.modify_login_pwd;


import com.exchange.water.application.base.Contract;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface ModifyLoginPwdContract {
    interface View extends Contract.BaseView<Presenter> {

        void sendEditLoginPwdCodeSuccess(String obj);

        void sendEditLoginPwdCodeFail(Integer code, String toastMessage);

        void editPwdSuccess(String obj);

        void editPwdFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {

        void sendEditLoginPwdCode(String token);

        void editPwd(String token, String oldPassword, String newPassword, String code);
    }
}
