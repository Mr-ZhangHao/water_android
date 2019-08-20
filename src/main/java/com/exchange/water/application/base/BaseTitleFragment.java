package com.exchange.water.application.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exchange.water.application.R;
import com.exchange.water.application.utils.ExEventBus;
import com.gyf.barlibrary.ImmersionBar;


import org.greenrobot.eventbus.Subscribe;


/**
 * Created by lion on 2017/4/10.
 */

public abstract class BaseTitleFragment<VDB extends ViewDataBinding> extends BaseVDBFragment {

    private   boolean mHasLoginMenu = true;
    protected View mViewSpace;
    protected LinearLayout mBackLayout;
    protected TextView     mBackText;
    protected ImageView    mBackImage;

    protected LinearLayout mTitleLayout;
    protected TextView     mTitleText;
    protected ImageView    mTitleImage;

    protected LinearLayout mForwardLayout;
    protected TextView     mForwardText;
    protected ImageView    mForwardImage;

    private RelativeLayout mToolbarLayout;


    private   ViewGroup mView;
    protected VDB       mDataBinding;

    protected abstract void onBind();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        try {

            registerListener();
            if (mView != null) {
                ViewGroup viewGroup = (ViewGroup) mView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(mView);
                }
            } else {
                mView = (ViewGroup) inflater.inflate(R.layout.fragment_base, null);
                mDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), mView, false);
                FrameLayout rootLayout = mView.findViewById(R.id.rootLayout);
                rootLayout.addView(mDataBinding.getRoot());
                initTitle();
            }
            onBind();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attachToSwipeBack(mView);
    }

    private void initTitle() {
        mToolbarLayout = mView.findViewById(R.id.toolbar);

        mViewSpace = mView.findViewById(R.id.viewSpace);

        mBackImage = mView.findViewById(R.id.imageBack);
        mBackText = mView.findViewById(R.id.textBack);
        mBackLayout = mView.findViewById(R.id.layoutBack);
        mBackLayout.setOnClickListener(this);

        mTitleText = mView.findViewById(R.id.textTitle);
        mTitleImage = mView.findViewById(R.id.imageTitle);
        mTitleLayout = mView.findViewById(R.id.layoutTitle);
        mTitleLayout.setOnClickListener(this);

        mForwardText = mView.findViewById(R.id.textForward);
        mForwardImage = mView.findViewById(R.id.imageForward);
        mForwardLayout = mView.findViewById(R.id.layoutForward);
        mForwardLayout.setOnClickListener(this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        params.height = getStatusBarHeight()/2;
        mViewSpace.setLayoutParams(params);


    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.setTitleBar(getActivity(), mToolbarLayout);

    }
    protected void setTitleVisible(boolean isVisible) {
        if (mToolbarLayout != null) {
            if (isVisible) {
                mToolbarLayout.setVisibility(View.VISIBLE);
            } else {
                mToolbarLayout.setVisibility(View.GONE);
            }
        }

    }

    protected void setTitle(@StringRes int resId) {
        setTitle(getString(resId));
    }

    protected void setTitle(String title) {
        if (mTitleText != null && !TextUtils.isEmpty(title)) {
            mTitleText.setText(title);
        }
    }

    protected void initTitle(@StringRes int resId) {
        initTitle(getString(resId));
    }

    protected void initTitle(String title) {
        initTitle(title, true, false, false);
    }

    protected void initTitle(@StringRes int resId, boolean hasBack) {
        initTitle(getString(resId), hasBack, false, false);
    }

    protected void initTitle(String resId, boolean hasBack) {
        initTitle(resId, hasBack, false, false);
    }


    protected void initTitle(@StringRes int resId, boolean hasBack, boolean hasMenu, boolean
            canClick) {
        initTitle(getString(resId), hasBack, hasMenu, canClick);
    }

    protected void initTitle(String title, boolean hasBack, boolean hasMenu, boolean canClick) {
        if (hasBack) {
            mBackLayout.setVisibility(View.VISIBLE);
        } else {
            mBackLayout.setVisibility(View.GONE);
        }
        if (hasMenu) {

            mForwardLayout.setVisibility(View.VISIBLE);
        } else {
            mForwardLayout.setVisibility(View.GONE);
        }
        if (canClick) {
            mTitleImage.setVisibility(View.VISIBLE);
        } else {
            mTitleImage.setVisibility(View.GONE);
        }
        _mActivity.setTitle("");
        if (mTitleText != null && !TextUtils.isEmpty(title)) {
            mTitleText.setText(title);
        }
    }

    protected void setTitleIcon(int imageId) {
        if (mTitleImage != null && imageId > 0) {
            mTitleImage.setImageResource(imageId);
        }
    }

    protected void setTitleIconVisible(boolean visible) {
        if (mTitleImage != null) {
            if (visible) {
                mTitleImage.setVisibility(View.VISIBLE);
            } else {
                mTitleImage.setVisibility(View.GONE);
            }
        }
    }

    protected View getTitleLayout() {
        return mToolbarLayout;
    }

    protected void setMenu(int textId, int imageId) {
        if (mForwardText != null) {
            if (textId > 0) {
                mForwardText.setText(getString(textId));
                mForwardText.setVisibility(View.VISIBLE);
                mForwardLayout.setVisibility(View.VISIBLE);
            } else {
                mForwardText.setVisibility(View.GONE);
            }
        }
        if (mForwardImage != null) {
            if (imageId > 0) {
                mForwardImage.setImageResource(imageId);
                mForwardImage.setVisibility(View.VISIBLE);
                mForwardLayout.setVisibility(View.VISIBLE);
            } else {
                mForwardImage.setVisibility(View.GONE);
            }
        }
    }

    protected void setMenuIcon(int imageId) {
        if (mForwardImage != null) {
            mForwardImage.setImageResource(imageId);
            mForwardImage.setVisibility(View.VISIBLE);
            mForwardLayout.setVisibility(View.VISIBLE);
        }
        if (mForwardText != null) {
            mForwardText.setVisibility(View.GONE);
        }
    }

    protected void setMenuIconVisibel(boolean visibel) {
        if (mForwardImage != null) {
            if (visibel) {
                mForwardImage.setVisibility(View.VISIBLE);
                if (mForwardLayout != null) {
                    mForwardLayout.setVisibility(View.VISIBLE);
                }
            } else {
                mForwardImage.setVisibility(View.GONE);
            }
        }
    }

    protected void setMenuText(int textId,float size) {
        if (mForwardText != null) {
            mForwardText.setText(getString(textId));
            mForwardText.setTextSize(size);
            mForwardText.setVisibility(View.VISIBLE);
            mForwardLayout.setVisibility(View.VISIBLE);
        }
        if (mForwardImage != null) {
            mForwardImage.setVisibility(View.GONE);
        }
    }
    protected void setMenuSize(int textId) {
        if (mForwardText != null) {
            mForwardText.setTextSize(textId);
            mForwardText.setVisibility(View.VISIBLE);

        }

    }

    protected void setBackIcon(int imageId) {
        if (mBackImage != null) {
            mBackImage.setImageResource(imageId);
            mBackImage.setVisibility(View.VISIBLE);
        }
    }

    protected void setBackText(int textId) {
        if (mBackText != null) {
            mBackText.setText(getString(textId));
            mBackText.setVisibility(View.VISIBLE);
        }
    }

    public boolean isHasLoginOptionMenu() {
        return mHasLoginMenu;
    }


    @Override
    public void onResume() {
        super.onResume();
        //   changeLoginMenuState(User.getInstance().isLogin());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setHasLoginOptionMenu(boolean hasLoginOptionMenu) {
        this.mHasLoginMenu = hasLoginOptionMenu;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.layoutTitle:
                onTitleClick();
                break;
            case R.id.layoutBack:
                onBackClick();
                break;
            case R.id.layoutForward:
                onForwardClick();
                break;
        }
    }

    protected void onTitleClick() {

    }

    protected void onBackClick() {
        pop();
    }

    protected void onForwardClick() {

    }


    @Override
    public void onSupportVisible() {
        if (_mActivity != null) {
            //            LogUtils.d("setUserVisibleHint -> BaseTitleFragment");
            //            setStatusBar(R.drawable.bg_common_gradient);
            _mActivity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    @Subscribe
    public void onEvent(ExEventBus.MessageEvent event) {
        super.onEvent(event);
    }

    protected void calculatorStatusBar(ViewGroup layoutBase) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        params.topMargin = (0 - getStatusBarHeight());
        layoutBase.setLayoutParams(params);
    }
}
