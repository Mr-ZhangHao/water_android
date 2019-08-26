package com.exchange.water.application.ui.user.credit;


import android.os.Bundle;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentCreditInfoBinding;
import com.exchange.water.application.entity.YPCaptcha;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

public class CreditInfoFragment extends BaseTitleFragment<FragmentCreditInfoBinding> implements View.OnClickListener, CreditContract.InfoView ,YunpianCaptchaUtils.OnCaptchaWindowListener{

    public static CreditInfoFragment newInstance() {
        Bundle args = new Bundle();
        CreditInfoFragment fragment = new CreditInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void setPresenter(CreditContract.InfoPresenter presenter) {

    }

    @Override
    public void commitSuccess(String result) {

    }

    @Override
    public void commitError(Integer code, String toastMessage) {

    }

    @Override
    protected void onBind() {
        initTitle(R.string.bind_ali_account);
        mDataBinding.btnSubmit.setOnClickListener(this);
        mDataBinding.llUploadAfter.setOnClickListener(this);
        mDataBinding.llUploadBefore.setOnClickListener(this);
        mDataBinding.llUploadHand.setOnClickListener(this);
        mDataBinding.tvStatusHint.setOnClickListener(this);
        YunpianCaptchaUtils.getInstance(getContext()).setCaptchaWindowListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_credit_info;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {

            case R.id.btn_submit:
                YunpianCaptchaUtils.getInstance(getContext()).start(getActivity());

                break;
            case R.id.ll_upload_after:

                break;
            case R.id.ll_upload_before:

                break;
            case R.id.ll_upload_hand:

                break;
            case R.id.tv_status_hint:

                break;

        }
    }

    @Override
    public void onCaptchaSuccess(YPCaptcha data) {

    }

    @Override
    public void onCaptchaFail(String msg) {

    }
}