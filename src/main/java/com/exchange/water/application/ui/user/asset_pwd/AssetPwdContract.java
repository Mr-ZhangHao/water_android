package com.exchange.water.application.ui.user.asset_pwd;


import com.exchange.water.application.base.Contract;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface AssetPwdContract {
    interface View extends Contract.BaseView<Presenter> {

        void sendSuccess(String obj);

        void sendCodeFail(Integer code, String toastMessage);

        void accountPwdSuccess(String obj);

        void accountPwdFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {
        void accountPwd(String token, String jyPassword,String code);
        void sendCode(String phone, String data);
    }

    interface EditView extends Contract.BaseView<EditPresenter> {
        void sendSuccess(String obj);

        void sendCodeFail(Integer code, String toastMessage);

        void editAccountPedSuccess(String obj);

        void editAccountPedFail(Integer code, String toastMessage);
    }

    interface EditPresenter extends Contract.BasePresenter {
        void sendCode(String phone, String data);

        void editAccountPed(String token, String newPassword, String oldPassword,String code);
    }


    interface ResetView extends Contract.BaseView<ResetPresenter> {

        void resetAccountPwdSuccess(String obj);

        void resetAccountPwdFail(Integer code, String toastMessage);

        void resetAccountPwdCodeSuccess(String obj);

        void resetAccountPwdCodeFail(Integer code, String toastMessage);
    }

    interface ResetPresenter extends Contract.BasePresenter {

        void resetAccountPwd(String token, String newPassword, String code);

        void resetAccountPwdCode(String token);
    }

}
