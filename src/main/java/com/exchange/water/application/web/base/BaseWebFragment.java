package com.exchange.water.application.web.base;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.exchange.water.application.R;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.app.UrlFactory;
import com.exchange.water.application.base.BaseTitleFragment;
import com.exchange.water.application.entity.User;
import com.exchange.water.application.main.MainFragment;
import com.exchange.water.application.ui.user.login.LoginFragment;
import com.exchange.water.application.utils.ExEventBus;
import com.exchange.water.application.utils.WonderfulLogUtils;
import com.exchange.water.application.utils.WonderfulToastUtils;
import com.exchange.water.application.utils.widget.ProgressWebView;
import com.exchange.water.application.web.normal.WebNormalFragment;
import com.exchange.water.application.web.util.AppUtil;


/**
 * Created by lion on 2017/6/15.
 */

public abstract class BaseWebFragment<Presenter extends BaseWebContract.Presenter, VDB extends
        ViewDataBinding>
        extends BaseTitleFragment<VDB> implements BaseWebContract.View<Presenter> {

    private static final String JS_FUNCTION_NAME_LOGIN = "javascript:loginBack";
    private static final String JS_FUNCTION_NAME_BACK = "javascript:callbackWeb_Back()";
    private static final String JS_FUNCTION_NAME_MENU = "javascript:callbackWeb_Menu()";
    private static final String ANDROID_FUNCTION_NAME = "callbackAPP";
    //    private static final String ANDROID_FUNCTION_NAME = "AndroidFunction";

    protected String mUrl;

    protected class JSInterface<Presenter extends BaseWebContract.Presenter> {

        private BaseTitleFragment<VDB> mContext;
        private Presenter mPresenter;

        public JSInterface(BaseTitleFragment<VDB> context, Presenter presenter) {
            this.mContext = context;
            this.mPresenter = presenter;
        }

        /**
         * Web登录
         */
        @JavascriptInterface
        public void WebForLogin() {
            //            ExEventBus.getDefault().startActivityFromFragment(mContext,
            //                    ExEventBus.EVENT_TYPE_LOGIN, null, REQUEST_CODE_LOGIN);
            ExEventBus.getDefault().startFragmentForResult(
                    LoginFragment.newInstance(), ExEventBus.MessageFragment.REQUEST_CODE_NORMAL);
        }

        /**
         * Web返回
         */
        @JavascriptInterface
        public void WebForBack() {
            mContext.getActivity().finish();
        }

        @JavascriptInterface
        public void WebForShare(String params) {
            mPresenter.showShare(params);
        }

        @JavascriptInterface
        public void WebForMsg(String msg) {
            WonderfulToastUtils.showToast(msg);
        }

        @JavascriptInterface
        public void WebForTradePwd(String msg) {
            WonderfulToastUtils.showToast(msg);

            //            ExEventBus.getDefault().startActivity(mContext.getContext(),
            //                    ExEventBus.EVENT_TYPE_SECURITY_CENTER);
        }

        /**
         * Web返回
         */
        @JavascriptInterface
        public void Home() {
            pop();
        }

        /**
         * 打开新画面
         */
        @JavascriptInterface
        public void Blank(String message) {
            WonderfulLogUtils.loge("message : ", message);
            if (!message.startsWith("http")) {
                message = UrlFactory.host + message;

            }
            if (getParentFragment() instanceof MainFragment) {
                ExEventBus.getDefault().startFragment(WebNormalFragment.newInstance("", message));
            } else {
                start(WebNormalFragment.newInstance("", message));
            }
            //            message = "http://h5.fotoken.io/presell/pages/expect.html";
            //            ExEventBus.getDefault().startFragment(WebNormalFragment.newInstance("",
            // message));
        }

        /**
         * 打开浏览器
         */
        @JavascriptInterface
        public void Browser(String message) {
            AppUtil.openBrowser(_mActivity, message);
        }

        /**
         * 打开浏览器
         */
        @JavascriptInterface
        public void MM() {
         //   ExEventBus.getDefault().startFragment(FundFragment.newInstance());
        }

        /**
         * 打开浏览器
         */
        @JavascriptInterface
        public void Login() {
            ExEventBus.getDefault().startFragment(LoginFragment.newInstance());
        }

        /**
         * 打开浏览器
         */
        @JavascriptInterface
        public void ShowMenu() {

        }

        /**
         * 打开浏览器
         */
        @JavascriptInterface
        public void HideMenu() {
            updateUI(new Runnable() {
                @Override
                public void run() {
                    setMenuIconVisibel(false);
                }
            });
        }

        /**
         * 打开浏览器
         */
        @JavascriptInterface
        public void GoBack() {
            //            updateUI(new Runnable() {
            //                @Override
            //                public void run() {
            //                    WebView webview = getWebView();
            //                    if (webview != null) {
            //                        if (webview.canGoBack()) {
            //                            webview.goBack();
            //                        } else {
            //                            pop();
            //                        }
            //                    } else {
            //                        pop();
            //                    }
            //                }
            //            });
            pop();
        }
    }

    protected void initData() {
        if (!TextUtils.isEmpty(getUrl())) {
            mUrl = getUrl();
        } else {
            Bundle data = getArguments();
            if (data != null) {
                mUrl = data.getString("url");
            }
       /*     String token = User.getInstance().getData().getToken();
            String code = AppUtil.getSerialNumber();
            if (TextUtils.isEmpty(code)) {
                code = AppUtil.getLoaclPhoneMac(BaseApplication.getContext());
            }
            long mid = User.getInstance().getData().getUserInfo().getId();*/
 /*           mUrl = "/" + mUrl + "?authorization=" + token
                    + "&type=" + "Android" + "&code=" + code + "&mid=" + mid;*/
        }
        if (!mUrl.startsWith("http")) {
            mUrl = UrlFactory.host + mUrl;


        }
    }

    protected void initWebView(ProgressWebView webView, Presenter presenter) {
        if (!TextUtils.isEmpty(mUrl)) {
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setSupportMultipleWindows(true);
            settings.setDomStorageEnabled(true);
            // 设置可以支持缩放
            settings.setSupportZoom(true);
            //不显示webview缩放按钮
            settings.setDisplayZoomControls(false);
            //自适应屏幕
            settings.setUseWideViewPort(true);
            // WebView自适应屏幕大小
            settings.setLoadWithOverviewMode(true);
            // 设置网页编码
            settings.setDefaultTextEncodingName("UTF-8");
            webView.addJavascriptInterface(new JSInterface<Presenter>(BaseWebFragment.this,
                            presenter),
                    ANDROID_FUNCTION_NAME);
            WonderfulLogUtils.logd("mUrl:",mUrl);
            webView.loadUrl(mUrl);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    protected String getUrl() {
        return null;
    }

    @Override
    public boolean onBackPressedSupport() {
        if (getParentFragment() instanceof MainFragment) {
            return super.onBackPressedSupport();
        } else {
            WebView webview = getWebView();
            if (webview != null) {
                if (!webview.canGoBack()) {
                    return super.onBackPressedSupport();
                }
                if (TextUtils.isEmpty(webview.getTitle())
                        || webview.getTitle().startsWith("404")) {
                    return super.onBackPressedSupport();
                }
                String url = JS_FUNCTION_NAME_BACK;
                webview.loadUrl(url);
                return true;
            }
            return super.onBackPressedSupport();
        }
    }

    protected abstract WebView getWebView();

    @Override
    protected void onForwardClick() {
        WebView webview = getWebView();
        if (webview != null) {
            String url = JS_FUNCTION_NAME_MENU;
            webview.loadUrl(url);
        }
    }
}
