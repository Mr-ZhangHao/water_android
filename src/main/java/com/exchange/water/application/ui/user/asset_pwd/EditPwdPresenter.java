package com.exchange.water.application.ui.user.asset_pwd;


import com.exchange.water.application.data.DataSource;

/**
 * Created by Administrator on 2018/4/10.
 */

public class EditPwdPresenter implements AssetPwdContract.EditPresenter {
    private final DataSource                dataRepository;
    private final AssetPwdContract.EditView view;

    public EditPwdPresenter(DataSource dataRepository, AssetPwdContract.EditView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void editAccountPed(String token, String newPassword, String oldPassword,String code) {
        view.displayLoadingPopup();
        dataRepository.editAccountPed(token, newPassword, oldPassword,code, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.editAccountPedSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.editAccountPedFail(code, toastMessage);

            }
        });
    }

    @Override
    public void sendCode(String phone, String data) {
        view.displayLoadingPopup();
        dataRepository.sendEditLoginPwdCode(phone,data, new DataSource.DataCallback() {
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
}
