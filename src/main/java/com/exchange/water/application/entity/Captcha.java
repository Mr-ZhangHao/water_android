package com.exchange.water.application.entity;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Captcha {



    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(String authenticate) {
        this.authenticate = authenticate;
    }

    private String authenticate;


}
