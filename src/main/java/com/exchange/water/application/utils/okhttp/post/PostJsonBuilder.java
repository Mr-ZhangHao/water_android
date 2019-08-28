package com.exchange.water.application.utils.okhttp.post;



import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.utils.EncryUtils;
import com.exchange.water.application.utils.LogUtils;
import com.exchange.water.application.utils.SharedPreferenceInstance;
import com.exchange.water.application.utils.okhttp.RequestBuilder;
import com.exchange.water.application.utils.okhttp.RequestCall;

import java.util.HashMap;

import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/10/27.
 */

public class PostJsonBuilder extends RequestBuilder {

    private String body;
    private MediaType mime;

    @Override
    public PostJsonBuilder url(String url) {
        this.url = url;
        return this;
    }

    public PostJsonBuilder body(String body) {
        this.body = body;
        return this;
    }

    public PostJsonBuilder mime(MediaType mime) {
        this.mime = mime;
        return this;
    }

    @Override
    public RequestCall build() {
        addHeader("appOrPc_type", "app");
        String token;
        if ("".equals( SharedPreferenceInstance.getInstance().getaToken())||!MyApplication.getApp().isLogin()){
            token  = EncryUtils.getInstance().decryptString(SharedPreferenceInstance.getInstance().getToken(), MyApplication.getApp().getPackageName());
            SharedPreferenceInstance.getInstance().saveaToken(token);
        }else {
            token=MyApplication.getApp().getCurrentUser().getToken();

        }
        addHeader("token", token);
        LogUtils.e("token===",token);
        return new PostJsonRequest(url, params, headers, body, mime).build();

    }


    @Override
    public PostJsonBuilder addParams(String key, String val) {
        if (this.params == null) params = new HashMap<>();
        params.put(key, val);
        return this;
    }

    @Override
    public PostJsonBuilder addHeader(String key, String val) {
        if (this.headers == null) headers = new HashMap<>();
        headers.put(key, val);
        return this;
    }
}
