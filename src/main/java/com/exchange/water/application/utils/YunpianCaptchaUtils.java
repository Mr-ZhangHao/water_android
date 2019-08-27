package com.exchange.water.application.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.exchange.water.application.app.UrlFactory;
import com.exchange.water.application.data.DataSource;
import com.exchange.water.application.data.LocalDataSource;
import com.exchange.water.application.entity.Captcha;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.utils.okhttp.StringCallback;
import com.qipeng.capatcha.QPCapatcha;
import com.qipeng.capatcha.QPCaptchaConfig;
import com.qipeng.capatcha.QPCaptchaListener;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;

import static com.exchange.water.application.app.GlobalConstant.JSON_ERROR;
import static com.exchange.water.application.app.GlobalConstant.OKHTTP_ERROR;
import static com.exchange.water.application.utils.okhttp.WonderfulOkhttpUtils.post;

/**
 * Created by Administrator on 2019/8/23.
 */

public class YunpianCaptchaUtils {

    private static YunpianCaptchaUtils INSTANCE;

    public  OnCaptchaWindowListener onCaptchaWindowListener;
    public YunpianCaptchaUtils() {
    }


    public static YunpianCaptchaUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new YunpianCaptchaUtils();
        }

        return INSTANCE;
    }

    public void setCaptchaWindowListener(OnCaptchaWindowListener onCaptchaWindowListener) {
        this.onCaptchaWindowListener = onCaptchaWindowListener;
    }

    public  void start(final Activity activity) {
        QPCaptchaConfig config = new QPCaptchaConfig.Builder(activity)
                .setAlpha(0.7f)
                //       .setLangPackModel(langPackModel)
                .showLoadingView(true)
                //   .setLang(langEnCb.isChecked() ? QPCaptchaConfig.LANG_EN : QPCaptchaConfig.LANG_ZH)
                .setCallback(new QPCaptchaListener() {
                    @Override
                    public void onLoaded() {
                      //  Toast.makeText(activity, "onLoaded", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String msg) {
                  //      Toast.makeText(activity, "onSuccess = " + msg, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject object = new JSONObject(msg);
                            String token = object.optString("token");
                            String authenticate = object.optString("authenticate");
                             /*   Captcha captcha =new Captcha();
                                captcha.setToken(token);
                                captcha.setAuthenticate(authenticate);
                                onCaptchaWindowListener.onCaptchaSuccess(captcha);
*/
                            YPcaptcha(token,authenticate);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                     //   Toast.makeText(activity, "onFail msg = " + msg, Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(String msg) {
                 //       Toast.makeText(activity, "onError msg = " + msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                  //      Toast.makeText(activity, "onCancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        QPCapatcha.getInstance().verify(config);
    }

    public void YPcaptcha(String token, String authenticate) {
        post().url(UrlFactory.getCheckCaptcha()).addParams("token",token).addParams("authenticate",authenticate)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request,e);
                WonderfulLogUtils.logi("云片二次后台验证失败", "云片二次后台验证失败：" + e.getMessage());
                onCaptchaWindowListener.onCaptchaFail(null);

            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("云片二次后台验证成功：", "云片二次后台验证成功：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);

                    if (object.optInt("state") == 1) {

                        if (onCaptchaWindowListener != null) {
                            onCaptchaWindowListener.onCaptchaSuccess(object.optString("msg"));
                        }
                    } else {
                        if (onCaptchaWindowListener != null) {
                            onCaptchaWindowListener.onCaptchaFail(object.optString("msg"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  void  onDestroy(){
        if (INSTANCE != null) {
            INSTANCE=null;
        }
    }

    public interface OnCaptchaWindowListener {
        void onCaptchaSuccess(String msg);
        void onCaptchaFail(String msg);

    }

}
