package com.business.electr.clothes.bean;

import java.io.Serializable;

/**
 * Created by zenghaiqiang on 2018/12/21. 描述：
 */

public class UserBean implements Serializable {

    private long userId;//用户编号
    private int height;//身高
    private int weight;//体重
    private String birthDate;//出生日期
    private int roleType;//角色类型
    private int sex;//性别
    private int sourceType;//来源类型
    private int userStatus;//注册状态
    private int onlineStatus;//在线状态
    private int loginType;//登录类型
    private int numberStatus;//账号状态
    private int enableStatus;//启用状态
    private int loadStatus;//载入状态
    private int signStatus;//签到状态
    private String userName;
    private String password;
    private String token;
    private String userKey;//用户秘钥
    private String cardNo;//信用卡号
    private int creditValue;//积分
    private int totalRecharge;//充值总额
    private int accountRest;//账户余额
    private int retreatAmount;//可退余额
    private String openid;//微信用户标识
    private String openidZfb;//支付宝用户标识
    private String country;//国家
    private String province;//省份
    private String city;//城市
    private String nickName;//用户昵称
    private String phone;//手机号
    private String lastLoginIp;//上次登录IP
    private String email;//电子邮件
    private String headImgUrl;//头像
    private String info;//说明
    private String registerTime;//注册时间
    private String lastLoginTime;//上次登录时间
    private String inputTime;//输入时间


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getNumberStatus() {
        return numberStatus;
    }

    public void setNumberStatus(int numberStatus) {
        this.numberStatus = numberStatus;
    }

    public int getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(int enableStatus) {
        this.enableStatus = enableStatus;
    }

    public int getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(int loadStatus) {
        this.loadStatus = loadStatus;
    }

    public int getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(int signStatus) {
        this.signStatus = signStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(int creditValue) {
        this.creditValue = creditValue;
    }

    public int getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(int totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public int getAccountRest() {
        return accountRest;
    }

    public void setAccountRest(int accountRest) {
        this.accountRest = accountRest;
    }

    public int getRetreatAmount() {
        return retreatAmount;
    }

    public void setRetreatAmount(int retreatAmount) {
        this.retreatAmount = retreatAmount;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenidZfb() {
        return openidZfb;
    }

    public void setOpenidZfb(String openidZfb) {
        this.openidZfb = openidZfb;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }
}
