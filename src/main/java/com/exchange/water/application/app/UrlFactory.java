package com.exchange.water.application.app;


import com.exchange.water.application.config.UrlConfig;

/**
 * Created by Administrator on 2018/1/29.
 */

public class UrlFactory {

    public static final String host = UrlConfig.BASE_URL;

    //获取充币地址
    public static String getChongbi() {
        return host + "/uc/asset/wallet/reset-address";
    }

    //申请成为商家
    public static String getSellerApply() {
        return host + "/uc/approve/certified/business/apply";
    }

    //    提币验证码
    public static String getCode() {
        return host + "/uc/mobile/withdraw/code";
    }

    //    提币接口
    public static String getTIBi() {
        return host + "/uc/withdraw/apply/code";
    }

    //获取保证金币种列表
    public static String getDepositList() {
        return host + "/uc/approve/business-auth-deposit/list";
    }

    //帮助中心
    public static String getHelp() {
        return host + "/uc/ancillary/more/help";
    }

    //新手指南
    public static String getHelpXinShou() {
        return host + "/uc/ancillary/more/help/page";
    }


    //交易明细接口
    public static String getCha() {
        return host + "/uc/asset/transaction/all";
    }

    //提币明细
    public static String getChaTiBi() {
        return host + "/uc/withdraw/record";
    }

    //
    public static String getShangjia() {
        return host + "/uc/approve/certified/business/status";
    }

    // 获取汇率
    public static String getRateUrl() {
        return host + "/market/exchange-rate/usd-cny";
    }

    //手机注册验证码
    public static String getPhoneCodeUrl() {
        return host + "/m/mail";
    }  //手机注册验证码

    public static String getcaptchaImge() {
        return host + "/captcha/captchaImage";
    }

    public static String getcaptcha() {
        return host + "/captcha/checkCaptcha";
    }
    public static String getCheckCaptcha() {
        return host + "/captcha/ypCheckCaptcha";
    }

    //手机注册
    public static String getSignUpByPhone() {
        return host + "/m/member";
    }
    //邮箱注册
    public static String getSignUpByEmail() {
        return host + "/m/member";
    }
    //登录
    public static String getLoginUrl() {
        return host + "/m/app/login";
    }
    //k线数据
    public static String getKDataUrl() {
        return host + "/market/history";
    }
    //全部币种
    public static String getAllCurrency() {
        return host + "/market/symbol-thumb";
    }

    /**
     * 首页获取所有的币种
     */
    public static String getAllCurrencys() {
        return host + "/api/v1/allticker";
    }

    /**
     * 得到信息，来设置输入小数点位数的限制
     */
    public static String getSymbolInfo() {
        return host + "/market/symbol-info";
    }

    public static String getFindUrl() {
        return host + "/exchange/favor/find";
    }

    public static String getDeleteUrl() {
        return host + "/exchange/favor/delete";
    }

    public static String getAddUrl() {
        return host + "/exchange/favor/add";
    }

    public static String getExChangeUrl() {
        return host + "/exchange/order/add";
    }

    public static String getWalletUrl() {
        return host + "/uc/asset/wallet/";
    }

    public static String getAllUrl() {
        return host + "/otc/coin/all";
    }

    public static String getAdvertiseUrl() {
        return host + "/otc/advertise/page";
    }
    //全部国家
    public static String getCountryUrl() {
        return host + "/m/nationality";
    }
    //发布广告
    public static String getReleaseAdUrl() {
        return host + "/otc/advertise/create";
    }
    //上传头像
    public static String getUploadPicUrl() {
        return host + "/uc/upload/oss/base64";
    }
    //
    public static String getNameUrl() {
        return host + "/uc/approve/real/name";
    }
    /*
    * 设置或修改资金密码
    * */
    public static String getAccountPwdUrl() {
        return host + "/m/setSecPwd";
    }
    //广告列表
    public static String getAllAdsUrl() {
        return host + "/otc/advertise/all";
    }

    public static String getReleaseUrl() {
        return host + "/otc/advertise/on/shelves";
    }

    public static String getDeleteAdsUrl() {
        return host + "/otc/advertise/delete";
    }

    public static String getOffShelfUrl() {
        return host + "/otc/advertise/off/shelves";
    }

    public static String getAdDetailUrl() {
        return host + "/otc/advertise/detail";
    }

    public static String getUpdateAdUrl() {
        return host + "/otc/advertise/update";
    }

    public static String getC2CInfoUrl() {
        return host + "/otc/order/pre";
    }

    public static String getC2CBuyUrl() {
        return host + "/otc/order/buy";
    }

    public static String getC2CSellUrl() {
        return host + "/otc/order/sell";
    }

    public static String getMyOrderUrl() {
        return host + "/otc/order/self";
    }

    public static String getExtractinfoUrl() {
        return host + "/uc/withdraw/support/coin/info";
    }

    public static String getExtractUrl() {
        return host + "/uc/withdraw/apply";
    }

    public static String getAllTransactionUrl2() {
        return host + "/uc/asset/transaction/all";
    }

    public static String getSafeSettingUrl() {
        return host + "/uc/approve/security/setting";
    }

    public static String getAvatarUrl() {
        return host + "/uc/approve/change/avatar";
    }

    /*绑定手机
    * */
    public static String getBindPhoneUrl() {
        return host + "/m/bindingPhone";
    }

    public static String getSendCodeUrl() {
        return host + "/m/sendPhoneBinding";
    }

    public static String getBindEmailUrl() {
        return host + "/m/bindingmail";
    }

    public static String getSendEmailCodeUrl() {
        return host + "/m/sendEmailBinding";
    }

    public static String getEditLoginPwdUrl() {
        return host + "/m/mail";
    }

    public static String getEditPwdUrl() {
        return host + "/m/resetPwd";
    }

    public static String getPlateUrl() {
        return host + "/market/exchange-plate";
    }

    /**
     * 查询当前委托
     */
    public static String getEntrustUrl() {
        return host + "/exchange/order/personal/current";
    }

    /**
     * 获取历史委托记录
     */
    public static String getHistoryEntrus() {
        return host + "/exchange/order/personal/history";
    }

    public static String getCancleEntrustUrl() {
        return host + "/exchange/order/cancel/";
    }
    //忘记密码 验证码
    public static String getPhoneForgotPwdCodeUrl() {
        return host + "/m/mail/";
    }

    public static String getEmailForgotPwdCodeUrl() {
        return host + "/uc/reset/email/code";
    }
    //忘记密码 重置
    public static String getForgotPwdUrl() {
        return host + "/m/forgotV2";
    }

    public static String getCaptchaUrl() {
        return host + "/uc/start/captcha";
    }

    public static String getSendChangePhoneCodeUrl() {
        return host + "/uc/mobile/change/code";
    }

    public static String getChangePhoneUrl() {
        return host + "/uc/approve/change/phone";
    }


    public static String getLogoutUrl() {
        return host + "/m/member/logout";
    }


    /*
    * 首页notice消息
    * */
    public static String getMessageUrl() {
        return host + "/notice";
    }

    public static String getMessageDetailUrl() {
        return host + "/uc/announcement/";
    }

    public static String getMessageHelpUrl() {
        return host + "/uc/ancillary/more/help/detail";
    }

    public static String getRemarkUrl() {
        return host + "/uc/feedback";
    }

    public static String getAppInfoUrl() {
        return host + "/uc/ancillary/website/info";
    }

    //首页BannersUrl
    public static String getBannersUrl() {
        return host + "/n/news/banner";
    }

    public static String getOrderDetailUrl() {
        return host + "/otc/order/detail";
    }

    public static String getCancleUrl() {
        return host + "/otc/order/cancel";
    }

    public static String getpayDoneUrl() {
        return host + "/otc/order/pay";
    }

    public static String getReleaseOrderUrl() {
        return host + "/otc/order/release";
    }

    public static String getAppealUrl() {
        return host + "/otc/order/appeal";
    }

    public static String getEditAccountPwdUrl() {
        return host + "/m/setSecPwd";
    }

    public static String getResetAccountPwdUrl() {
        return host + "/uc/approve/reset/transaction/password";
    }

    public static String getResetAccountPwdCodeUrl() {
        return host + "/uc/mobile/transaction/code";
    }

    public static String getHistoryMessageUrl() {
        return host + "/chat/getHistoryMessage";
    }

    public static String getEntrustHistory() {
        return host + "/exchange/order/history";
    }

    public static String getCreditInfo() {
        return host + "/uc/approve/real/detail";
    }

    public static String getNewVision() {
        return host + "/uc/ancillary/system/app/version/0";
    }

    public static String getSymbolUrl() {
        return host + "/market/symbol";
    }

    public static String getAccountSettingUrl() {
        return host + "/uc/approve/account/setting";
    }

    public static String getBindBankUrl() {
        return host + "/uc/approve/bind/bank";
    }

    public static String getUpdateBankUrl() {
        return host + "/uc/approve/update/bank";
    }

    public static String getBindAliUrl() {
        return host + "/uc/approve/bind/ali";
    }

    public static String getUpdateAliUrl() {
        return host + "/uc/approve/update/ali";
    }

    public static String getBindWechatUrl() {
        return host + "/uc/approve/bind/wechat";
    }

    public static String getUpdateWechatUrl() {
        return host + "/uc/approve/update/wechat";
    }

    public static String getCheckMatchUrl() {
        return host + "/uc/asset/wallet/match-check";
    }

    public static String getStartMatchUrl() {
        return host + "/uc/asset/wallet/match";
    }

    public static String getPromotionUrl() {
        return host + "/uc/promotion/record";
    }

    public static String getPromotionRewardUrl() {
        return host + "/uc/promotion/reward/record";
    }

    public static String getDepth() {
        return host + "/market/exchange-plate-full";
    } // 获取深度图数据

    public static String getVolume() {
        return host + "/market/latest-trade";
    } // 获取成交数据


}
