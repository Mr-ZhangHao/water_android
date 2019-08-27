package com.exchange.water.application.ui.home.help;

import android.os.Bundle;
import android.view.View;

import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.databinding.FragmentHelpBinding;

/**
 * Created by Administrator on 2019/8/27.
 */

public class HelpFragment extends BaseTitleFragment<FragmentHelpBinding> implements View.OnClickListener{

    public static HelpFragment newInstance() {
        Bundle args = new Bundle();
        HelpFragment fragment = new HelpFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onBind() {
        initTitle(R.string.mine_Help_center);

        mDataBinding.llAboutUs.setOnClickListener(this);
        mDataBinding.llDisclaimer.setOnClickListener(this);
        mDataBinding.llPrivacyProtection.setOnClickListener(this);
        mDataBinding.llSubmitOrder.setOnClickListener(this);
        mDataBinding.llTermsService.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_help;
    }
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_about_us:
                break;
            case R.id.ll_disclaimer:

                break;
            case R.id.ll_submit_order:

                break;
            case R.id.ll_terms_service:

                break;
            case R.id.ll_privacy_protection:

                break;
        }
    }

}
