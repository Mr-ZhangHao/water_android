package com.exchange.water.application.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.exchange.water.application.R;
import com.exchange.water.application.utils.ExEventBus;
import com.gyf.barlibrary.ImmersionBar;


import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by lion on 2017/4/10.
 */

public abstract class   BaseVDBActivity<VDB extends ViewDataBinding> extends SupportActivity {

    private Handler mHandler = new Handler(Looper.getMainLooper());
    protected VDB mDataBinding;
    protected ImmersionBar         immersionBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (!ExEventBus.getDefault().getDefaultEventBus().isRegistered(this)) {
            ExEventBus.getDefault().getDefaultEventBus().register(this);
        }
        //这一行注意！看本文最后的说明！！！！
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (isImmersionBarEnabled()) initImmersionBar();

        mDataBinding = DataBindingUtil.setContentView(this, getLayoutRes());

        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);

        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(false);
        }
        onBind(mDataBinding);

    }

    @LayoutRes
    public abstract int getLayoutRes();

    protected abstract void onBind(VDB dataBinding);
    /**
     * 是否启用沉浸式
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void updateUI(Runnable runnable) {
        if (runnable != null) {
            mHandler.post(runnable);
        }
    }

    public void updateUI(Runnable runnable, long delay) {
        if (runnable != null) {
            mHandler.postDelayed(runnable, delay);
        }
    }

    public void setTextContent(TextView textView, Object data) {
        if (data instanceof String
                || data instanceof CharSequence) {
            textView.setText((CharSequence) data);
        } else if (data instanceof Number) {
            textView.setText(data.toString());
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
    /**
     * 子类重写实现扩展设置
     */
    protected void initImmersionBar() {
        immersionBar = ImmersionBar.with(this);
        immersionBar.keyboardEnable(false, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN).statusBarDarkFont(false, 0.2f).flymeOSStatusBarFontColor(R.color.colorPrimary).init();
    }

    private long DOUBLE_CLICK_TIME = 0L;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode != KeyEvent.KEYCODE_MENU && super.onKeyDown(keyCode, event);

    }
    @Subscribe
    public void onEvent(ExEventBus.MessageEvent event) {

    }

    @Override
    protected void onDestroy() {
        if (ExEventBus.getDefault().getDefaultEventBus().isRegistered(this)) {
            ExEventBus.getDefault().getDefaultEventBus().unregister(this);
        }
        super.onDestroy();
        if (immersionBar != null) immersionBar.destroy();
    }


}
