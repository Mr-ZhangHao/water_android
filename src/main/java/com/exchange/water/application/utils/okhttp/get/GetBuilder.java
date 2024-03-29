package com.exchange.water.application.utils.okhttp.get;

import android.net.Uri;


import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.utils.EncryUtils;
import com.exchange.water.application.utils.LogUtils;
import com.exchange.water.application.utils.SharedPreferenceInstance;
import com.exchange.water.application.utils.okhttp.RequestBuilder;
import com.exchange.water.application.utils.okhttp.RequestCall;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * Created by Administrator on 2017/11/13.
 */

public class GetBuilder extends RequestBuilder {
    @Override
    public RequestCall build() {
     //   String token = EncryUtils.getInstance().decryptString(SharedPreferenceInstance.getInstance().getToken(), MyApplication.getApp().getPackageName());
   //     addHeader("access-auth-token", token);
        if (params != null)
        {
            url = appendParams(url, params);
        }
        String token;
        if ("".equals( SharedPreferenceInstance.getInstance().getaToken())||!MyApplication.getApp().isLogin()){
            token  = EncryUtils.getInstance().decryptString(SharedPreferenceInstance.getInstance().getToken(), MyApplication.getApp().getPackageName());
            SharedPreferenceInstance.getInstance().saveaToken(token);
        }else {
            token=MyApplication.getApp().getCurrentUser().getToken();

        }
        addHeader("token", token);
        LogUtils.e("token===",token);

        return new GetRequest(url, params, headers).build();
    }


    private String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty())
        {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();

    }

    @Override
    public GetBuilder url(String url) {
        this.url = url;
        return this;
    }


    @Override
    public GetBuilder addParams(String key, String value) {
        if (this.params == null) params = new HashMap<>();
        params.put(key, value);
        return this;
    }

    @Override
    public GetBuilder addHeader(String key, String value) {
        if (this.headers == null) headers = new HashMap<>();
        headers.put(key, value);
        return this;
    }
}
