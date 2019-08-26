package com.exchange.water.application.utils;


import android.text.TextUtils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class StrUtil {

    public static final char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    public static final char[] digits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9' };
    public static final char[] allDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z' };
    public static final Random random = new Random();
    public static final String emailCheck = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
    public static final String userNameCheck = "[a-z0-9A-Z]{2,16}";
    public static final String ethAddressCheck = "^0x[0-9a-fA-F]{40}$";
    public static final String mobileCheck = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
    public static final String nickNameCheck= "^[\\u4e00-\\u9fa5A-Za-z0-9-_]*$";
    public static final String idNum = "";
    public static final String pwdCheck = "^[a-zA-Z0-9\\@\\!\\#\\$\\%\\&\\*]{8,16}$";
    public static final String wallet_pwdCheck= "^[0-9\\@\\!\\#\\$\\%\\&\\*]{6,6}$";

    public static boolean check(String text, String regex) {
        if (TextUtils.isEmpty(text)&&TextUtils.isEmpty(regex)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            return matcher.matches();
        }
    }
/*

    */
/**
     * 检测是否是用户accessToken
     *//*

    public static boolean isUUID(String accessToken) {
        if (Strings.isNullOrEmpty(accessToken)) {
            return false;
        } else {
            try {
                // noinspection ResultOfMethodCallIgnored
                UUID.fromString(accessToken);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
*/

    public static void main(String[] args) {
        System.out.println(StrUtil.randomStr(4));
    }

    /**
     * 随机指定长度的数字
     *
     * @param length
     * @return
     */
    public static String randomNumber(int length) {
        StringBuffer sb = new StringBuffer();
        for (int loop = 0; loop < length; ++loop) {
            sb.append(digits[random.nextInt(digits.length)]);
        }
        return sb.toString();
    }

    /**
     * 随机指定长度的数字
     *
     * @param length
     * @return
     */
    public static String randomStr(int length) {
        StringBuffer sb = new StringBuffer();
        for (int loop = 0; loop < length; ++loop) {
            sb.append(allDigits[random.nextInt(allDigits.length)]);
        }
        return sb.toString();
    }

    /**
     * 随机指定长度的字符串
     *
     * @param length
     * @return
     */
    public static String randomString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int loop = 0; loop < length; ++loop) {
            sb.append(hexDigits[random.nextInt(hexDigits.length)]);
        }
        return sb.toString();
    }

    /**
     * 验证输入的名字是否为“中文”或者是否包含“·”
     */
    public static boolean isLegalName(String name){
        if (name.contains("·") || name.contains("•")){
            if (name.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]+$")){
                return true;
            }else {
                return false;
            }
        }else {
            if (name.matches("^[\\u4e00-\\u9fa5]+$")){
                return true;
            }else {
                return false;
            }
        }
    }

    /**
     * 验证输入的身份证号是否合法
     */
    public static boolean isLegalId(String id){
        if (id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")){
            return true;
        }else {
            return false;
        }
    }


    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        Matcher isNum = pattern.matcher(str);



        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}