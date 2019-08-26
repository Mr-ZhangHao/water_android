package com.exchange.water.application.entity;


import android.location.Location;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/26.
 */

public class User   implements Serializable {
    private Integer id;//
    private String uid; //uid
    private Integer area_code;//国家代码，目前默认均为86表示中国

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getArea_code() {
        return area_code;
    }

    public void setArea_code(Integer area_code) {
        this.area_code = area_code;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getSms_code() {
        return sms_code;
    }

    public void setSms_code(String sms_code) {
        this.sms_code = sms_code;
    }

    public String getSms_code_time() {
        return sms_code_time;
    }

    public void setSms_code_time(String sms_code_time) {
        this.sms_code_time = sms_code_time;
    }

    public String getM_pwd() {
        return m_pwd;
    }

    public void setM_pwd(String m_pwd) {
        this.m_pwd = m_pwd;
    }

    public String getM_security_pwd() {
        return m_security_pwd;
    }

    public void setM_security_pwd(String m_security_pwd) {
        this.m_security_pwd = m_security_pwd;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_mod_pwd_time() {
        return last_mod_pwd_time;
    }

    public void setLast_mod_pwd_time(String last_mod_pwd_time) {
        this.last_mod_pwd_time = last_mod_pwd_time;
    }

    public Integer getAuth_grade() {
        return auth_grade;
    }

    public void setAuth_grade(Integer auth_grade) {
        this.auth_grade = auth_grade;
    }

    public Integer getM_status() {
        return m_status;
    }

    public void setM_status(Integer m_status) {
        this.m_status = m_status;
    }

    public Integer getApi_status() {
        return api_status;
    }

    public void setApi_status(Integer api_status) {
        this.api_status = api_status;
    }

    public String getIntroduce_m_id() {
        return introduce_m_id;
    }

    public void setIntroduce_m_id(String introduce_m_id) {
        this.introduce_m_id = introduce_m_id;
    }

    public String getM_nick_name() {
        return m_nick_name;
    }

    public void setM_nick_name(String m_nick_name) {
        this.m_nick_name = m_nick_name;
    }

    public String getGoogle_auth_key() {
        return google_auth_key;
    }

    public void setGoogle_auth_key(String google_auth_key) {
        this.google_auth_key = google_auth_key;
    }

    public Integer getHidden_assets() {
        return hidden_assets;
    }

    public void setHidden_assets(Integer hidden_assets) {
        this.hidden_assets = hidden_assets;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public Integer getLoan_protocol() {
        return loan_protocol;
    }

    public void setLoan_protocol(Integer loan_protocol) {
        this.loan_protocol = loan_protocol;
    }

    public String getM_email() {
        return m_email;
    }

    public void setM_email(String m_email) {
        this.m_email = m_email;
    }

    public String getEmail_code() {
        return email_code;
    }

    public void setEmail_code(String email_code) {
        this.email_code = email_code;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getM_name_hidden() {
        return m_name_hidden;
    }

    public void setM_name_hidden(String m_name_hidden) {
        this.m_name_hidden = m_name_hidden;
    }

    public Integer getGoogle_auth_code() {
        return google_auth_code;
    }

    public void setGoogle_auth_code(Integer google_auth_code) {
        this.google_auth_code = google_auth_code;
    }

    public Integer getOld_google_auth_code() {
        return old_google_auth_code;
    }

    public void setOld_google_auth_code(Integer old_google_auth_code) {
        this.old_google_auth_code = old_google_auth_code;
    }

    public Double getTrade_commission() {
        return trade_commission;
    }

    public void setTrade_commission(Double trade_commission) {
        this.trade_commission = trade_commission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String  m_name;//手机号/用户名，暂定手机号，考虑可能要支持邮箱，使用varchar类型
    private String  sms_code;//短信验证码，同时作为注册和找回密码的验证码
    private String  sms_code_time; //短信验证码发送时间
    private String  m_pwd;//密码,md5后，取前25位
    private String  m_security_pwd;//资金密码,md5后，取前25位
    private String  reg_time;//注册时间，获取手机验证码时就填充
    private String  last_login_ip; //最后登录IP
    private String  last_login_time;//最后登录时间，第一次填注册时间
    private String  last_mod_pwd_time; //最后修改密码时间，某些关键操作只能在修改密码后24小时后操作
    private Integer auth_grade;//认证等级，0表示未认证，1表示F1,2表示F2，提现人民币1级即可，提现比特币需2级认证，m_auth_identity审核通过即为1级，m_auth_video审核通过即为2级
    private Integer m_status;//状态：0：手机未验证，1：正常，2：冻结
    private Integer api_status;//API状态：1：正常，2：冻结
    private String introduce_m_id; //介绍人ID
    private String m_nick_name; //昵称
    private String google_auth_key; //谷歌验证码私钥
    private Integer hidden_assets; //是否隐藏小额资产 0表示隐藏,1不隐藏
    private String head_img; //头像图片
    private Integer loan_protocol;//杠杆协议,默认为0,0为没有查看,1为已查看
    private String m_email; //邮箱号
    private String email_code; //邮箱验证码
    private String validateCode; //校验码
    private String m_name_hidden; //隐藏了部分的用户名
    private Integer google_auth_code; //用户输入的谷歌验证码
    private Integer old_google_auth_code; //用户修改谷歌验证码时，输入旧的谷歌验证码
    private Double trade_commission; // 佣金比例，1为正常收取，0为不收取，0.5为打5折
    private String token; //token

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", username='" + m_nick_name + '\'' +
                ", location=" + last_login_ip +
                ", memberLevel=" + trade_commission +
                ", realName='" + introduce_m_id + '\'' +
                '}';
    }


}
