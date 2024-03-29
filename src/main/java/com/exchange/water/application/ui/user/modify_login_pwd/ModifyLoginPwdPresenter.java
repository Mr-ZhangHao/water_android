package com.exchange.water.application.ui.user.modify_login_pwd;


import com.exchange.water.application.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class ModifyLoginPwdPresenter implements ModifyLoginPwdContract.Presenter {
    private final DataSource                  dataRepository;
    private final ModifyLoginPwdContract.View view;

    public ModifyLoginPwdPresenter(DataSource dataRepository, ModifyLoginPwdContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void sendEditLoginPwdCode(String phone, String data) {
        view.displayLoadingPopup();
        dataRepository.sendEditLoginPwdCode(phone,data, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.sendEditLoginPwdCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.sendEditLoginPwdCodeFail(code, toastMessage);

            }
        });
    }

    @Override
    public void editPwd(String token, String oldPassword, String newPassword, String code) {
        view.displayLoadingPopup();
        dataRepository.editPwd(token, oldPassword, newPassword, code, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.editPwdSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.editPwdFail(code, toastMessage);

            }
        });
    }
}
