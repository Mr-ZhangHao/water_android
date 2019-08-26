package com.exchange.water.application.utils.captcha;

import com.exchange.water.application.data.DataSource;
import com.exchange.water.application.entity.Captcha1;
import com.exchange.water.application.entity.YPCaptcha;

/**
 * Created by Administrator on 2019/8/23.
 */

public class CaptchaPresenter implements CaptchaContract.CaptchaPresenter {
    private final DataSource         dataRepository;
    private final CaptchaContract.CaptchaView view;

    public CaptchaPresenter(DataSource dataRepository, CaptchaContract.CaptchaView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void captcha() {
        view.displayLoadingPopup();
        dataRepository.captcha1( new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.CaptchaSuccess((Captcha1) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.CaptchaFail();
            }
        });
    }

    @Override
    public void captcha2(String point , String randomId) {
        view.displayLoadingPopup();
        dataRepository.captcha1( new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.Captcha2Success((YPCaptcha) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
             //   view.loginFail(code, toastMessage);
            }
        });
    }
}
