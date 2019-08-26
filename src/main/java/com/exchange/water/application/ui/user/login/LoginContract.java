package com.exchange.water.application.ui.user.login;




import com.exchange.water.application.base.Contract;
import com.exchange.water.application.entity.User;
import com.exchange.water.application.entity.YPCaptcha;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface LoginContract {
    interface View extends Contract.BaseView<Presenter> {

        void loginFail(Integer code, String toastMessage);

        void loginSuccess(User obj);
    }
        
    interface Presenter extends Contract.BasePresenter {
        //void login(String token,String username, String password, String challenge, String validate, String seccode);
        void login(String username, String password,String seccode);

    }
}
