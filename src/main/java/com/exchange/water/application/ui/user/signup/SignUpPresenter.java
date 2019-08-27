package com.exchange.water.application.ui.user.signup;



import com.exchange.water.application.data.DataSource;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public class SignUpPresenter implements SignUpContract.PhonePresenter {
    private final DataSource dataRepository;
    private final SignUpContract.PhoneView view;

    public SignUpPresenter(DataSource dataRepository, SignUpContract.PhoneView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void phoneCode(String phone, String country,String  check_code) {
        dataRepository.phoneCode(phone, country,check_code, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.phoneCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.phoneCodeFail(code, toastMessage);
            }
        });
    }

    @Override
    public void signUpByPhone(String mAreacode,String mAccount, String password, String mCode,String tuijianma) {
        view.displayLoadingPopup();
        dataRepository.signUpByPhone(mAreacode,mAccount,password, mCode, tuijianma,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.signUpByPhoneSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.signUpByPhoneFail(code, toastMessage);
            }
        });
    }

    @Override
    public void signUpByEmail(String mAccount, String password, String mCode, String tuijianma) {
        view.displayLoadingPopup();
        dataRepository.signUpByEmail(mAccount,password, mCode, tuijianma,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.signUpByPhoneSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.signUpByPhoneFail(code, toastMessage);
            }
        });
    }


}
