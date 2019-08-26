package com.exchange.water.application.utils.captcha;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseWindow;
import com.exchange.water.application.databinding.WindowCaptchaBinding;
import com.exchange.water.application.entity.Captcha1;
import com.exchange.water.application.entity.YPCaptcha;
import com.luozm.captcha.Captcha;

/**
 * Created by Administrator on 2019/8/23.
 */

public class CaptchaWindow extends BaseWindow<WindowCaptchaBinding> implements CaptchaContract.CaptchaView {

    private OnCaptchaWindowListener onCaptchaWindowListener;

    private  Context                mContext;
    private CaptchaContract.CaptchaPresenter presenter;

    public void setCaptchaWindowListener(OnCaptchaWindowListener onCaptchaWindowListener) {
        this.onCaptchaWindowListener = onCaptchaWindowListener;
    }


    public CaptchaWindow(Context context) {
        super(context);
        this.mContext =context;
    }

    @Override
    protected void onBind(WindowCaptchaBinding dataBinding) {
        setCancelable(true);
        setOutTouchable(true);

        new CaptchaPresenter(Injection.provideTasksRepository(mContext), this);
        presenter.captcha();
        mDataBinding.captCha.reset(true);


        mDataBinding.captCha.setCaptchaListener(new Captcha.CaptchaListener() {
            @Override
            public String onAccess(long time) {
          //      Toast.makeText(mContext, "验证成功", Toast.LENGTH_SHORT).show();
                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.captCha.refresh();
                        dismiss();

                        if (onCaptchaWindowListener != null) {
                            onCaptchaWindowListener.onCaptchaSuccess(true);
                        }
                    }
                },1500);
                return "验证通过";
            }

            @Override
            public String onFailed(int count) {
         //       Toast.makeText(mContext, "验证失败,失败次数" + count, Toast.LENGTH_SHORT).show();

                updateUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataBinding.captCha.refresh();

                        if (onCaptchaWindowListener != null) {
                            onCaptchaWindowListener.onCaptchaFail();
                            onCaptchaWindowListener.onCaptchaSuccess(false);

                        }
                    }
                },1500);
                return "验证失败";
            }

            @Override
            public String onMaxFailed() {
           //     Toast.makeText(mContext, "验证超过次数，你的帐号被封锁", Toast.LENGTH_SHORT).show();
                dismiss();

                return "可以走了";
            }

        });
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.window_captcha;
    }

    public void  setBitmap (final String imgeaUrl){
        updateUI(new Runnable() {
            @Override
            public void run() {

                Glide.with(mContext).load(imgeaUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mDataBinding.captCha.setBitmap(resource);
                    }
                });

            }
        });
    }
    public void shows(View parent) {
        if (mWindow != null) {
            try {
                mWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
            } catch (WindowManager.BadTokenException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setPresenter(CaptchaContract.CaptchaPresenter presenter) {
        this.presenter =presenter;
    }

    @Override
    public void hideLoadingPopup() {
      //  hideLoadingPopup();
    }

    @Override
    public void displayLoadingPopup() {
      //  displayLoadingPopup();
    }

    @Override
    public void CaptchaSuccess(Captcha1 obj) {
        if (obj==null) return;
        setBitmap(obj.getSourceImgName());
    }

    @Override
    public void CaptchaFail() {
        updateUI(new Runnable() {
            @Override
            public void run() {
                mDataBinding.captCha.setBitmap(R.mipmap.img_guide_1);

          /*      Glide.with(mContext).load(mContext.getResources().getDrawable(R.mipmap.img_guide_1)).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    }
                });*/

            }
        });
    }

    @Override
    public void Captcha2Success(YPCaptcha obj) {

    }

    public interface OnCaptchaWindowListener {
        void onCaptchaSuccess(boolean Success);
        void onCaptchaFail();
    }
}
