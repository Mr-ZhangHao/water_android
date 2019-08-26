package com.exchange.water.application.web.util;

import android.text.TextUtils;

import java.util.SortedMap;
import java.util.TreeMap;

import io.kedou.wallet.common.http.HttpManager;

/**
 * Created by lion on 2017/5/25.
 */

public class WebUrlHttpManager {

    private static WebUrlHttpManager instance;

    public static WebUrlHttpManager getInstance() {
        if (instance == null) {
            instance = new WebUrlHttpManager();
        }
        return instance;
    }

    private WebUrlHttpManager() {
    }

    public String getRequestParamStr(String token, String secretKey) {
        return getRequestParamStr(token, secretKey, false);
    }

    public String getRequestParamStr(String token, String secretKey, boolean paramNeedSecretKey) {
        SortedMap<String, String> params = new TreeMap<>();
        if (!TextUtils.isEmpty(token)) {
            params.put("token", token);
            if (paramNeedSecretKey) {
                params.put("secretKey", secretKey);
            }
        }
        return HttpManager.getInstance().createUrlParams(token, secretKey, params);
    }

    public String getFullRequestUrl(String url, String token, String secretKey) {
        return getFullRequestUrl(url, token, secretKey, false);
    }

    public String getFullRequestUrl(String url, String token, String secretKey,
                                    boolean paramNeedSecretKey) {
        String params = getRequestParamStr(token, secretKey, paramNeedSecretKey);
        return String.format("%s?%s", url, params);
    }
}
