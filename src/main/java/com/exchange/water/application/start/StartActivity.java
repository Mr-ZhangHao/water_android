package com.exchange.water.application.start;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.ViewDataBinding;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import com.exchange.water.application.R;
import com.exchange.water.application.base.BaseVDBActivity;
import com.exchange.water.application.databinding.ActivityBaseFragmentFrameBinding;
import com.exchange.water.application.main.MainActivity;
import com.exchange.water.application.start.guide.frame.GuideFragment;
import com.exchange.water.application.utils.SharedPreferenceInstance;



/**
 * Created by lion on 2017/6/3.
 */

public class StartActivity extends BaseVDBActivity<ActivityBaseFragmentFrameBinding> {


    private TextView mTvConten;

    private void gotoLogin() {
        updateUI(new Runnable() {
            @Override
            public void run() {
                if (SharedPreferenceInstance.getInstance().getIsFirstUse()) {
                loadRootFragment(R.id.layoutFragment, GuideFragment.newInstance());
                } else {
                   Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                    finish();
                }
            }
        }, 2500);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_base_fragment_frame;
    }

    @Override
    protected void onBind(final ActivityBaseFragmentFrameBinding dataBinding) {
       // mTvConten = (TextView)findViewById(R.id.tv_content);

      gotoLogin();

    }

    @Override
    public void onBackPressedSupport() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
