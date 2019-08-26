package com.exchange.water.application.utils.captcha;

import com.exchange.water.application.base.Contract;
import com.exchange.water.application.entity.Captcha1;
import com.exchange.water.application.entity.YPCaptcha;

/**
 * Created by Administrator on 2019/8/23.
 */

public interface CaptchaContract {

    interface CaptchaView extends  Contract.BaseView<CaptchaPresenter> {
        void CaptchaSuccess(Captcha1 obj);
        void CaptchaFail();
        void Captcha2Success(YPCaptcha obj);
    }

    interface CaptchaPresenter extends Contract.BasePresenter  {
        void captcha();
        void captcha2(String point , String randomId);

    }
}
