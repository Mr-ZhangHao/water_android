package com.exchange.water.application.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDex;
import android.view.View;
import android.widget.TextView;


import com.exchange.water.application.R;
import com.exchange.water.application.entity.User;
import com.exchange.water.application.ui.user.login.LoginFragment;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.SharedPreferenceInstance;
import com.exchange.water.application.utils.WonderfulCodeUtils;
import com.exchange.water.application.utils.WonderfulDateUtils;
import com.exchange.water.application.utils.WonderfulFileUtils;
import com.exchange.water.application.utils.WonderfulLogUtils;
import com.exchange.water.application.utils.WonderfulStringUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.exchange.water.application.utils.okhttp.StringCallback;
import com.exchange.water.application.utils.okhttp.WonderfulOkhttpUtils;
import com.qipeng.capatcha.QPCapatcha;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;

import me.yokeyword.fragmentation.Fragmentation;
import okhttp3.Request;

import static com.exchange.water.application.utils.okhttp.WonderfulOkhttpUtils.post;

/**
 * Created by pc on 2017/3/8.
 */
//http://git.xinhuokj.com/bitrade/bitrade-android
public class MyApplication extends Application {
    /**
     * 是否发布了
     */
    private boolean isReleased = false;

    /**
     * 当前是否有网络
     */
    private boolean isConnect = false;

    /**
     * 当前用户信息是否发生改变
     */
    private boolean isLoginStatusChange = false;

    public int typeBiaoshi=0;
    public int typeBiaoshi2=0;
    public int typeBiaoshi1=0;

    public static MyApplication app;

 private User currentUser = new User();
    /**
     * WonderfulToastView
     */
    private TextView tvToast;
    /**
     * 当前手机屏幕的宽高
     */
    private int mWidth;
    private int mHeight;

 public static List<Currency> list =new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initView();
        /*二维码识别*/
        ZXingLibrary.initDisplayOpinion(this);
        getDisplayMetric();
        getCurrentUserFromFile();
         checkInternet();
        x.Ext.init(this);
        WonderfulCodeUtils.saveErrorCode();
        Fragmentation.builder()
                .stackViewMode(Fragmentation.NONE)
                .debug(true)
                .install();

        //云片验证
        QPCapatcha.getInstance().init(this, "862653aa0b0c42f3a941c190b0441478");


     //   UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
    }


    private void initView() {
        tvToast = (TextView) View.inflate(app, R.layout.my_toast, null);
    }

    public boolean isLogin() {
        if (getCurrentUser() == null) return false;
        return !WonderfulStringUtils.isEmpty(getCurrentUser().getToken());
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    /**
     * 检查是否有网络
     */
    private void checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        NetworkInfo.State mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if ((wifiState != null && wifiState == NetworkInfo.State.CONNECTED) || (mobileState != null && mobileState == NetworkInfo.State.CONNECTED)) {
            isConnect = true;
        }/*else {
            WonderfulToastUtils.showToast(getString(R.string.no_network));
        }*/
    }

    /**
     * 获取屏幕的宽高
     */
    private void getDisplayMetric() {
        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取程序的Application对象
     */
    public static MyApplication getApp() {
        return app;
    }


/**
     * 重新登录
     */

    public void loginAgain() {
        setCurrentUser(null);
        WonderfulFileUtils.getLongSaveFile(this, "User", "user.info").delete();
        ExEventBus.getDefault().startFragment(LoginFragment.newInstance());
    }

    /**
     * 退出
     */

    public void loginOut() {
        WonderfulOkhttpUtils.get().url(UrlFactory.getLogoutUrl())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request,e);
                WonderfulLogUtils.logi("退出登录出错", "退出登录出错：" + e.getMessage());

            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("退出登录回执：", "退出登录回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("state") == 1) {
                        setCurrentUser(null);
                        SharedPreferenceInstance.getInstance().saveaToken("");
                        SharedPreferenceInstance.getInstance().saveTOKEN("");
                        WonderfulFileUtils.getLongSaveFile(getApp(), "User", "user.info").delete();
                        ExEventBus.getDefault().startFragment(LoginFragment.newInstance());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public synchronized void saveCurrentUser() {
        try {
            File file = WonderfulFileUtils.getLongSaveFile(this, "User", "user.info");
            if (currentUser == null) {
                if (file.exists()) {
                    file.delete();
                }
                return;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(currentUser);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCurrentUserFromFile() {
        try {
            File file = new File(WonderfulFileUtils.getLongSaveDir(this, "User"), "user.info");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            this.currentUser = (User) ois.readObject();
            WonderfulLogUtils.logi("读出来的User", currentUser.toString());
            if (this.currentUser == null) {
                this.currentUser = new User();
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean isReleased() {
        return isReleased;
    }

    public int getmWidth() {
        return mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public TextView getTvToast() {
        return tvToast;
    }

    public User getCurrentUser() {
        return currentUser == null ? currentUser = new User() : currentUser;
    }

    public synchronized void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        saveCurrentUser();
    }

    public boolean isLoginStatusChange() {
        return isLoginStatusChange;
    }

    public void setLoginStatusChange(boolean loginStatusChange) {
        isLoginStatusChange = loginStatusChange;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }




}
