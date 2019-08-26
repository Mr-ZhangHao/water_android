package com.exchange.water.application.ui.user.credit;


import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.app.Injection;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentCreditBinding;
import com.exchange.water.application.entity.Credit;
import com.exchange.water.application.ui.user.bind_account.BindAccountContact;
import com.exchange.water.application.ui.user.bind_account.BindAliFragment;
import com.exchange.water.application.ui.user.bind_account.BindAliPresenter;
import com.exchange.water.application.ui.user.bind_account.BindBankFragment;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.YunpianCaptchaUtils;

public class CreditFragment extends BaseTitleFragment<FragmentCreditBinding> implements View.OnClickListener {


    public static CreditFragment newInstance() {
        Bundle args = new Bundle();
        CreditFragment fragment = new CreditFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void onBind() {
        initTitle(R.string.bind_ali_account);
        mDataBinding.llCountry.setOnClickListener(this);
        mDataBinding.llType.setOnClickListener(this);
        mDataBinding.btnNext.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_credit;
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {

            case R.id.btn_next:
                ExEventBus.getDefault().startFragment(CreditInfoFragment.newInstance());

                break;
            case R.id.ll_country:

                break;
            case R.id.ll_type:

                break;

        }
    }
}
