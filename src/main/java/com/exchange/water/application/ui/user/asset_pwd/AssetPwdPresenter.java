package com.exchange.water.application.ui.user.asset_pwd;


import com.exchange.water.application.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class AssetPwdPresenter implements AssetPwdContract.Presenter {
    private final DataSource            dataRepository;
    private final AssetPwdContract.View view;

    public AssetPwdPresenter(DataSource dataRepository, AssetPwdContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void accountPwd(String token, String jyPassword) {
        view.displayLoadingPopup();
        dataRepository.accountPwd(token, jyPassword, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.accountPwdSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.accountPwdFail(code, toastMessage);

            }
        });
    }
}
