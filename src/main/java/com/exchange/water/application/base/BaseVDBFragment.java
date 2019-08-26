package com.exchange.water.application.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;


import com.exchange.water.application.R;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.StatusBarUtils;
import com.exchange.water.application.utils.WonderfulLogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.readystatesoftware.systembartint.SystemBarTintManager;


import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

public abstract class BaseVDBFragment<VDB extends ViewDataBinding> extends
        SwipeBackFragment implements View
        .OnClickListener{
    protected VDB mDataBinding;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    protected boolean isAlive = true;
    private static final int INVALID_VAL = -1;//顶部颜色默认值
    private SystemBarTintManager tintManager;//状态栏管理器
    protected abstract void onBind();
    protected ImmersionBar immersionBar;
    private PopupWindow loadingPopup;

    @LayoutRes
    protected abstract int getLayoutRes();
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        registerListener();
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
            onBind();
 //   if (isImmersionBarEnabled()) initImmersionBar();
     StatusBarUtils.setStatusBarColor(getActivity(),R.color.primaryText);
        return attachToSwipeBack(mDataBinding.getRoot());
    }



    protected void initImmersionBar() {
        immersionBar = ImmersionBar.with(this);
       immersionBar.keyboardEnable(false, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN).statusBarDarkFont(false,0.2f).flymeOSStatusBarFontColor(R.color.help_view).init();
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

    @Override
    public void onDestroy() {
        if (ExEventBus.getDefault().getDefaultEventBus().isRegistered(this)) {
            ExEventBus.getDefault().getDefaultEventBus().unregister(this);
        }
        super.onDestroy();
    }

    protected void registerListener() {
        if (!ExEventBus.getDefault().getDefaultEventBus().isRegistered(this)) {
            ExEventBus.getDefault().getDefaultEventBus().register(this);
        }

        if (getParentFragment() == null && getPreFragment() != null) {
            setSwipeBackEnable(true);
            getSwipeBackLayout().setEnableGesture(true);
        } else {
            setSwipeBackEnable(false);
            getSwipeBackLayout().setEnableGesture(false);
        }
    }



    @Subscribe
    public void onEvent(ExEventBus.MessageEvent event) {

    }
/*
    @Subscribe
    public void onEvent(ExEventBus.MessageFragment event) {

    }
*/

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);

    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }


    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isAlive = false;
        if (immersionBar != null) immersionBar.destroy();
        hideLoadingPopup();

    }

    protected View findViewById(int viewId) {
        return mDataBinding.getRoot().findViewById(viewId);
    }



    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        if (_mActivity != null) {
            //            LogUtils.d("setUserVisibleHint -> BaseVBDFragment");
            //            setStatusBar(R.drawable.bg_me_gradient_toolbar);
            _mActivity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            if (immersionBar != null) initImmersionBar();
        }
    }


    /**
     * 显示加载框
     */
    public void displayLoadingPopup() {
        if (getActivity() != null) ((BaseVDBActivity) getActivity()).displayLoadingPopup();

    }

    public void hideLoadingPopup() {
        if (getActivity() != null) ((BaseVDBActivity) getActivity()).hideLoadingPopup();
    }


    public BaseVDBActivity getmActivity() {
        return (BaseVDBActivity) super.getActivity();
    }
    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = _mActivity.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = _mActivity.getResources().getDimensionPixelSize(resourceId);
            WonderfulLogUtils.logi("getStatusBarHeight : " , result+"");
        }
        return result;
    }
    /**
     * 设置沉浸式状态栏
     *
     * @param res 背景资源
     */
    public void setStatusBar(int res) {
        if (tintManager == null)
            tintManager = new SystemBarTintManager(getmActivity());
        // 激活状态栏
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint 激活导航栏
        //        tintManager.setNavigationBarTintEnabled(true);
        //设置系统栏设置颜色
     // tintManager.setTintColor(R.color.primaryText);
        //给状态栏设置颜色
        if (res != INVALID_VAL) {
         tintManager.setStatusBarTintResource(res);
            //Apply the specified drawable or color resource to the system navigation bar.
            //给导航栏设置资源
        tintManager.setNavigationBarTintResource(res);
        }
    }

}
