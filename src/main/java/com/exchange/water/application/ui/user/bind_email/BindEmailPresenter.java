package com.exchange.water.application.ui.user.bind_email;


import com.exchange.water.application.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class BindEmailPresenter implements BindEmailContract.Presenter {
    private final DataSource dataRepository;
    private final BindEmailContract.View view;

    public BindEmailPresenter(DataSource dataRepository, BindEmailContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void sendCode(String phone,String are, String data) {
        view.displayLoadingPopup();
        dataRepository.sendCode(data,phone, are, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.sendSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.sendCodeFail(code, toastMessage);

            }
        });
    }

    @Override
    public void sendEmailCode(String phone, String data) {
        view.displayLoadingPopup();
        dataRepository.sendEmailCode(data,phone, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.sendSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.sendCodeFail(code, toastMessage);

            }
        });
    }

    @Override
    public void bindEmail(String token, String phone, String code) {
        view.displayLoadingPopup();
        dataRepository.bindEmail(token, phone, code, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.bindEmailSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.bindEmailFail(code, toastMessage);

            }
        });
    }

    @Override
    public void bindPhone(String token, String phone, String code,String mArea) {
        view.displayLoadingPopup();
        dataRepository.bindPhone(token, phone, code,mArea, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.bindPhoneSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.bindPhoneFail(code, toastMessage);

            }
        });
    }


}
