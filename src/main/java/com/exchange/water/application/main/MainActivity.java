package com.exchange.water.application.main;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.base.ActivityManage;
import com.exchange.water.application.base.BaseVDBActivity;
import com.exchange.water.application.entity.User;
import com.exchange.water.application.ui.home.HomeFragment;
import com.exchange.water.application.ui.home.contract.MainUIContract;
import com.exchange.water.application.ui.home.presenter.HomePresenter;
import com.exchange.water.application.ui.user.login.LoginFragment;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.SharedPreferenceInstance;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.github.mikephil.charting.data.ChartData;

import org.greenrobot.eventbus.Subscribe;

import java.util.Date;

import me.yokeyword.fragmentation.SupportFragment;

import static com.exchange.water.application.main.MainFragment.TYPE_FAST_LOGIN;
import static com.exchange.water.application.main.MainFragment.TYPE_NORMAL_LOGIN;

public class MainActivity extends BaseVDBActivity {

    private long mLastBackTime  = 0;
    private int  mLastBackTimes = 0;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_base_fragment_frame;
    }

    @Override
    protected void onBind(ViewDataBinding dataBinding) {
        SharedPreferenceInstance.getInstance().saveIsFirstUse(false);
        loadRootFragment(R.id.layoutFragment, MainFragment.newInstance(TYPE_NORMAL_LOGIN));
     /*   if (TextUtils.isEmpty(User.getInstance().getData().getToken())) {
            loadRootFragment(R.id.layoutFragment, LoginPWDFragment.newInstance());
            //       loadRootFragment(R.id.layoutFragment, MainFragment.newInstance(MainFragment.TYPE_NORMAL_LOGIN));
        } else {
            loadRootFragment(R.id.layoutFragment, MainFragment.newInstance(MainFragment.TYPE_FAST_LOGIN));
        }*/


    }

    @Subscribe
    public void onEvent(ExEventBus.MessageFragment event) {
        if (event != null) {
            SupportFragment fragment = event.getFragment();
            if (fragment != null) {
                if (fragment instanceof LoginFragment) {
                    LoginFragment loginFragment = findFragment(LoginFragment.class);
                    if (loginFragment == null) {
                        startWithPop(LoginFragment.newInstance());
                    } else {
                        popTo(LoginFragment.class, false);
                    }
                } else {
                    startForResult(fragment, event.getRequestCode());
                }
             //   startForResult(fragment, event.getRequestCode());
            }
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (getTopFragment() instanceof MainFragment||getTopFragment() instanceof LoginFragment) {

            exitApp();

        } else {
            super.onBackPressedSupport();
        }
    }

    private void exitApp() {
        mLastBackTimes++;
        long current = new Date().getTime();

        if (current - mLastBackTime > 1000) {
            mLastBackTimes = 1;
        }

        if (mLastBackTimes == 1) {
            WonderfulToastUtils.showToast(getString(R.string.exit_tips));

        } else {
            if (current - mLastBackTime <= 1000) {
                finish();
            } else {
                mLastBackTimes = 0;
            }
        }
        mLastBackTime = current;
    }
    @Override
    public void onEvent(ExEventBus.MessageEvent event) {
        super.onEvent(event);

        if(event.getType() == ExEventBus.MessageEvent.EVENT_TYPE_FORCE_UPDATE) {
            finish();
        }
    }
    public void finishActivity() {
    finish();
    }
}
