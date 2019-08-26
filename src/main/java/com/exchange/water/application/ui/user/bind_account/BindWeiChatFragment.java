package com.exchange.water.application.ui.user.bind_account;


import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentWeichatAccountBinding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindWeiChatFragment extends BaseTitleFragment<FragmentWeichatAccountBinding> implements BindAccountContact.WeChatView,YunpianCaptchaUtils.OnCaptchaWindowListener {

    private BindAccountContact.WeChatPresenter presenter;

    public static BindWeiChatFragment newInstance() {
        Bundle args = new Bundle();
        BindWeiChatFragment fragment = new BindWeiChatFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onBind() {
        initTitle(R.string.bind_weichat_account);
        new BindWeiChatPresenter(Injection.provideTasksRepository(getContext()), this);
        mDataBinding.btnConfirm.setOnClickListener(this);
        mDataBinding.upload.setOnClickListener(this);
        mDataBinding.imgIsInvisible.setOnClickListener(this);
        YunpianCaptchaUtils.getInstance(getContext()).setCaptchaWindowListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_weichat_account;
    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {

            case R.id.img_is_invisible:
                if (mDataBinding.imgIsInvisible.isChecked()){
                    mDataBinding.edAssetPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    mDataBinding.edAssetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.upload:

                break;

            case R.id.btn_confirm:

                YunpianCaptchaUtils.getInstance(getContext()).start(getActivity());

                break;

        }
    }
    @Override
    public void setPresenter(BindAccountContact.WeChatPresenter presenter) {
        this.presenter =presenter;
    }

    @Override
    public void getBindWeChatSuccess(String obj) {

    }

    @Override
    public void getBindWeChatFail(Integer code, String toastMessage) {

    }

    @Override
    public void getUpdateWeChatSuccess(String obj) {

    }

    @Override
    public void getUpdateWeChatFail(Integer code, String toastMessage) {

    }

    @Override
    public void uploadBase64PicFail(Integer code, String toastMessage) {

    }

    @Override
    public void uploadBase64PicSuccess(String obj) {

    }

    @Override
    public void onCaptchaSuccess(YPCaptcha data) {

    }

    @Override
    public void onCaptchaFail(String msg) {

    }
}
