package com.exchange.water.application.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.exchange.water.application.app.MyApplication;


/**
 * Created by lion on 2017/5/16.
 */

public class AccountSettings {

    private static final String NAME = "AccountSettings.xml";
    private static final String LAST_LOGIN_ACCOUNT = "last_login_account";

    private SharedPreferences preferences;

    private static AccountSettings instance;

    public static AccountSettings getInstance() {
        if (instance == null) {
            instance = new AccountSettings();
        }
        return instance;
    }

    private AccountSettings() {
        preferences = MyApplication.getApp().getSharedPreferences(
                NAME, Context.MODE_PRIVATE);
    }

    public String getLastLoginAccount() {
        return preferences.getString(LAST_LOGIN_ACCOUNT, "");
    }

    public void setLastLoginAccount(String loginAccount) {
        preferences.edit().putString(LAST_LOGIN_ACCOUNT, loginAccount).apply();
    }
}
