package com.exchange.water.application.utils;


import android.support.v4.app.Fragment;

import com.exchange.water.application.R;
import com.exchange.water.application.app.GlobalConstant;
import com.exchange.water.application.app.MyApplication;
import com.exchange.water.application.base.BaseVDBActivity;
import com.qipeng.capatcha.utils.SpUtils;

import java.util.HashMap;


/**
 * Created by wonderful on 2017/5/23.
 */

public class WonderfulCodeUtils {

    private static String unknown_error;
    private static String no_network;
    private static String parse_error;
    private static String login_invalid;
    private static String no_data;


    static {
        unknown_error = MyApplication.getApp().getResources().getString(R.string.unknown_error);
        no_network = MyApplication.getApp().getResources().getString(R.string.no_network);
        parse_error = MyApplication.getApp().getResources().getString(R.string.parse_error);
        login_invalid = MyApplication.getApp().getResources().getString(R.string.login_invalid);
        no_data = MyApplication.getApp().getResources().getString(R.string.no_data);
    }

    private static HashMap<String, String> mStringHashMap;

    public static void saveErrorCode() {
        mStringHashMap = new HashMap<>();
        mStringHashMap.put("LANG_NO_LOGIN",
                "请先登录");
        mStringHashMap.put("LANG_ILLEGAL_CURRENCY",
                "不存在的币种");
        mStringHashMap.put("LANG_CANNOT_WITHDRAW",
                "暂停提现");
        mStringHashMap.put("LANG_ILLEGAL_ADDRESS",
                "不存在的地址");
        mStringHashMap.put("LANG_ILLEGAL_AMOUNT",
                "错误的数量");
        mStringHashMap.put("LANG_WITHDRAW_AMOUNT_MIN_THAN_MIN_TIP",
                "提现金额小于最低提现额");
        mStringHashMap.put("LANG_SMSCODE_ERR_TIP",
                "验证码错误");
        mStringHashMap.put("LANG_SEC_PWD_TIP",
                "资金密码错误");
        mStringHashMap.put("LANG_GOOGLE_CODE_ERROR",
                "谷歌验证码错误");
        mStringHashMap.put("LANG_AUTH_IDENTITY_FIRST",
                "请先完成实名认证");
        mStringHashMap.put("LANG_ILLEGAL_WITHDRAW_ID",
                "错误的提现ID");
        mStringHashMap.put("LANG_ILLEGAL_WITHDRAW_STATUS",
                "提现状态不为待处理，不能取消");
        mStringHashMap.put("LANG_SMSCODE_NULL_TIP",
                "验证码必填");
        mStringHashMap.put("LANG_WITHDRAW_ADDR_NULL_TIP",
                "提现地址必填");
        mStringHashMap.put("LANG_WITHDRAW_LABEL_NULL_TIP",
                "提现标签必填");
        mStringHashMap.put("LANG_MAIL_NULL_TIP",
                "请输入邮箱");
        mStringHashMap.put("LANG_PWD_NULL_TIP",
                "请输入密码");
        mStringHashMap.put("LANG_ALREADY_REG_FINDPWD",
                "该账号已注册");
        mStringHashMap.put("LANG_ACCOUNT_LOCKED",
                "该账号被锁定");
        mStringHashMap.put("LANG_PLEASE_LOGIN_AFTER_REGISTRATION",
                "该账号未完成注册");
        mStringHashMap.put("LANG_PWD_SAME_TIP",
                "安全密码和登录密码不能一致");
        mStringHashMap.put("LANG_ILLEGAL_FROM",
                "请求来源非法");
        mStringHashMap.put("LANG_NO_LOGIN",
                "请先登录");
        mStringHashMap.put("LANG_ILLEGAL_CURRENCY",
                "不存在的币种");
        mStringHashMap.put("LANG_CANNOT_WITHDRAW",
                "暂停提现");
        mStringHashMap.put("LANG_ILLEGAL_ADDRESS",
                "不存在的地址");
        mStringHashMap.put("LANG_ILLEGAL_AMOUNT",
                "错误的数量");
        mStringHashMap.put("LANG_WITHDRAW_AMOUNT_MIN_THAN_MIN_TIP",
                "提现金额小于最低提现额");
        mStringHashMap.put("LANG_SMSCODE_ERR_TIP",
                "验证码错误");
        mStringHashMap.put("LANG_SEC_PWD_TIP",
                "资金密码错误");
        mStringHashMap.put("LANG_GOOGLE_CODE_ERROR",
                "谷歌验证码错误");
        mStringHashMap.put("LANG_AUTH_IDENTITY_FIRST",
                "请先完成实名认证");
        mStringHashMap.put("LANG_ILLEGAL_WITHDRAW_ID",
                "错误的提现ID");
        mStringHashMap.put("LANG_ILLEGAL_WITHDRAW_STATUS",
                "提现状态不为待处理，不能取消");
        mStringHashMap.put("LANG_SMSCODE_NULL_TIP",
                "验证码必填");
        mStringHashMap.put("LANG_WITHDRAW_ADDR_NULL_TIP",
                "提现地址必填");
        mStringHashMap.put("LANG_WITHDRAW_LABEL_NULL_TIP",
                "提现标签必填");
        mStringHashMap.put("LANG_MAIL_NULL_TIP",
                "请输入邮箱");
        mStringHashMap.put("LANG_PWD_NULL_TIP",
                "请输入密码");
        mStringHashMap.put("LANG_ALREADY_REG_FINDPWD",
                "该账号已注册");
        mStringHashMap.put("LANG_ACCOUNT_LOCKED",
                "该账号被锁定");
        mStringHashMap.put("LANG_PLEASE_LOGIN_AFTER_REGISTRATION",
                "该账号未完成注册");
        mStringHashMap.put("LANG_PWD_SAME_TIP",
                "安全密码和登录密码不能一致");
        mStringHashMap.put("LANG_ILLEGAL_FROM",
                "请求来源非法");
        mStringHashMap.put("LANG_ACCOUNT_NOT_EXIST",
                "账号不存在");
        mStringHashMap.put("LANG_MAIL_FAIL",
                "发送邮件失败");
        mStringHashMap.put("LANG_INVALID_EMAIL_OR_PASSWORD",
                "账号或密码错误");
        mStringHashMap.put("LANG_ID_NUM_ALREADY_EXIST",
                "证件号已经存在");
        mStringHashMap.put("LANG_AUTH_IDENTITY_SUB_SUCCESS",
                "身份认证申请提交成功");
        mStringHashMap.put("LANG_ILLEGAL_SIGN",
                "错误的签名");
        mStringHashMap.put("LANG_ORDER_DOES_NOT_EXIST",
                "订单不存在");
        mStringHashMap.put("LANG_MARKET_ORDER_CANNOT_BE_CANCELLED",
                "市价单不允许取消");
        mStringHashMap.put("LANG_ORDER_IS_DONE_OR_CANCELED",
                "订单已成交或已经取消");
        mStringHashMap.put("LANG_ILLEGAL_SYMBOL",
                "错误的交易对");
        mStringHashMap.put("LANG_STOP_EX",
                "暂停交易");
        mStringHashMap.put("LANG_ILLEGAL_O_NO",
                "错误的订单号");
        mStringHashMap.put("LANG_ILLEGAL_VOLUME",
                "错误的数量,请保留两位小数");
        mStringHashMap.put("LANG_ILLEGAL_PRICE",
                "错误的价格");
        mStringHashMap.put("LANG_ILLEGAL_O_TYPE",
                "错误的订单类型");
        mStringHashMap.put("LANG_ILLEGAL_O_PRICE_TYPE",
                "错误的价格类型");
        mStringHashMap.put("LANG_ILLEGAL_SOURCE",
                "错误的订单来源");
        mStringHashMap.put("LANG_ILLEGAL_TRADE_PAIR",
                "错误的交易对");
        mStringHashMap.put("LANG_LITTLE_THAN_MIN_BUYVOLUME_TIP",
                "数量小于最小买入数量");
        mStringHashMap.put("LANG_LITTLE_THAN_MIN_BUYVOLUME_TIP2",
                "买入数量不能小于{0}");
        mStringHashMap.put("LANG_LITTLE_THAN_MIN_BUYAMOUNT_TIP",
                "总额小于最小买入总额");
        mStringHashMap.put("LANG_LITTLE_THAN_MIN_BUYAMOUNT_TIP2",
                "买入总额不能小于{0}");
        mStringHashMap.put("LANG_LITTLE_THAN_MIN_SELLVOLUME_TIP",
                "数量小于最小卖出数量");
        mStringHashMap.put("LANG_LITTLE_THAN_MIN_SELLVOLUME_TIP2",
                "卖出数量不能小于{0}");
        mStringHashMap.put("LANG_STOP_ONE_EX",
                "暂停交易");
        mStringHashMap.put("LANG_ILLEGAL_ORDER_ID",
                "错误的订单ID");
        mStringHashMap.put("LANG_POOL_NO_ADDRESS_EXISTS",
                "地址池地址已经用完");
        mStringHashMap.put("LANG_ADDRESS_ALREADY_EXISTS",
                "充值地址已经存在");
        mStringHashMap.put("LANG_REDIS_ERROR",
                "缓存服务器错误");
        mStringHashMap.put("LANG_ORDER_STATUS_ERR",
                "订单状态错误");
        mStringHashMap.put("LANG_OLD_GOOGLE_CODE_ERROR",
                "原谷歌验证码错误");
        mStringHashMap.put("NO_SUFFICIENT_FUNDS",
                "余额不足");
        mStringHashMap.put("LANG_OLD_PWD_ERR_TIP",
                "原密码错误");
        mStringHashMap.put("LANG_ERROR_A_TYPE",
                "错误的账户类型（只支持 bank alipay wxpay");
        mStringHashMap.put("LANG_A_TYPE_ONLY_ONE_RECORD",
                "一个账户类型（银行卡、微信、支付宝）只能绑定一个账户");
        mStringHashMap.put("LANG_AUTH_IDENTITY_FIRST",
                "请先完成实名认证");
        mStringHashMap.put("LANG_SEC_PWD_ERR_TIP",
                "资金密码错误");
        mStringHashMap.put("LANG_ILLEGAL_ADS_TYPE",
                "广告类型错误");
        mStringHashMap.put("LANG_ILLEGAL_ADS_ID",
                "广告ID不存在");
        mStringHashMap.put("LANG_ILLEGAL_AMOUNT",
                "数量不符合要求，比如小于广告要求的最低金额或大于广告要求的最高金额");
        mStringHashMap.put("LANG_ILLEGAL_ACCOUNT_TYPE",
                "账户类型不存在");
        mStringHashMap.put("LANG_WAITING_ORDER_MAX_THAN_2",
                "超过两个订单未付款，不能再添加新订单");
        mStringHashMap.put("LANG_ORDER_NOT_EXISTS",
                "订单不存在");
        mStringHashMap.put("LANG_ORDER_ALREADY_CONFIRMED",
                "订单不是待付款状态");
        mStringHashMap.put("LANG_SMSCODE_ERR_TIP",
                "验证码错误！");
        mStringHashMap.put("LANG_ILLEGAL_CHECK_CODE",
                "错误的图片验证码");
        mStringHashMap.put("LANG_ALREADY_REG_EMAIL",
                "该邮箱已被使用");
        mStringHashMap.put("LANG_HAVE_NULL_VALUE",
                "请按格式填写完整");
        mStringHashMap.put("LANG_ALREADY_REG_PHONE",
                "该手机号已被使用");
        mStringHashMap.put("LANG_REPETITION_CURRENCY",
                "此币种申请已提交过多次，无需再次提交此币种申请已提交过多次，无需再次提交");
        mStringHashMap.put("FULL_AMOUNT",
                "数量不符合要求，广告额度已用完");
        mStringHashMap.put("LOAN_EXCESS",
                "超出可借额度");
        mStringHashMap.put("TRIGGER_LIMIT",
                "发送验证码次数已达到当日上限");
        mStringHashMap.put("LANG_EMAILCODE_ERR_TIP",
                "邮箱验证码错误");
        mStringHashMap.put("LANG_REPEAT_ADDRESS",
                "提币地址重复");
        mStringHashMap.put("LANG_ILLEGAL_IPS",
                "非法操作");

    }

    public static void checkedErrorCode(Fragment fragment, Integer code, String toastMessage) {
        String toast = "";
        String Message = mStringHashMap.get(toastMessage);
        toast = Message;
        if (!WonderfulStringUtils.isEmpty(toastMessage)) {
            WonderfulToastUtils.showToast(toast);
            return;
        } else {
            toast = unknown_error;
            WonderfulToastUtils.showToast(toast);
            return;
        }
    }

}
