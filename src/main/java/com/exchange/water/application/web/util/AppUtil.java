package com.exchange.water.application.web.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.Window;

import com.exchange.water.application.R;
import com.exchange.water.application.app.MyApplication;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

public class AppUtil {
    /**
     * 获取本地软件版本号
     */
    public static int getVersionCode() {
        Context ctx = MyApplication.getApp();
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getVersionName() {
        String localVersion = "";
        Context ctx = MyApplication.getApp();
        try {
            PackageInfo packageInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    public static String getBigDecimal(double cash) {
        //        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00000000");
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.########");
        return decimalFormat.format(cash);
    }

    private static long lastClickTime;
    private final static long INTERVAL = 500;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < INTERVAL) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * @param timeInterval 时间间隔
     * @return
     */
    public static boolean isFastDoubleClick(Long timeInterval) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < timeInterval) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 获取本地手机的IMEI信息
     *
     * @return IMEI号码
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context mContext) {
        try {
            // 获取IMEI
            TelephonyManager phoneMgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            return phoneMgr.getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    // 获取电话的MAC
    public static String getLoaclPhoneMac(Context context) {

        if (context == null) {
            return null;
        }
        String macAddress = null;
        try {
            WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
            if (null != info) {
                macAddress = info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return macAddress;
    }

    public static String getSerialNumber() {
        return Build.SERIAL;
    }

    public static void openBrowser(Activity activity, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(Intent.createChooser(intent,
                    activity.getResources().getString(R.string.find_select_browser)));
        }
    }

    // 是否是小米手机
    public static boolean isXiaomi() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }

    // 设置小米状态栏
    public static void setXiaomiStatusBar(Window window, boolean isLightStatusBar) {
        Class<? extends Window> clazz = window.getClass();
        try {
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, isLightStatusBar ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
