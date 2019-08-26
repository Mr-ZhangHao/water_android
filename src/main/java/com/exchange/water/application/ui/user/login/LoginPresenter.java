package com.exchange.water.application.ui.user.login;




import com.exchange.water.application.data.DataSource;
import com.exchange.water.application.entity.User;
import com.exchange.water.application.entity.YPCaptcha;

/**
 * Created by Administrator on 2017/9/25.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private final DataSource dataRepository;
    private final LoginContract.View view;

    public LoginPresenter(DataSource dataRepository, LoginContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }
    
    @Override
    public void login(String username, String password,String seccode) {
        view.displayLoadingPopup();
        dataRepository.login(username, password,seccode, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.loginSuccess((User) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.loginFail(code, toastMessage);
            }
        });
    }


}
