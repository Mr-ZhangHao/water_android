package com.exchange.water.application.ui.user.asset_pwd;


import com.exchange.water.application.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class ResetAssetPwdPresenter implements AssetPwdContract.ResetPresenter {
    private final DataSource                 dataRepository;
    private final AssetPwdContract.ResetView view;

    public ResetAssetPwdPresenter(DataSource dataRepository, AssetPwdContract.ResetView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void resetAccountPwd(String token, String newPassword, String code) {
        view.displayLoadingPopup();
        dataRepository.resetAccountPwd(token, newPassword, code, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.resetAccountPwdSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.resetAccountPwdFail(code, toastMessage);

            }
        });
    }

    @Override
    public void resetAccountPwdCode(String token) {
        view.displayLoadingPopup();
        dataRepository.resetAccountPwdCode(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.resetAccountPwdCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.resetAccountPwdCodeFail(code, toastMessage);

            }
        });
    }
}
