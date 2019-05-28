package com.business.electr.clothes.bean;

import java.io.Serializable;

/**
 * Created by zenghaiqiang on 2018/12/21. 描述：
 */

public class UserBean implements Serializable {


    /**
     * country :
     * userStatus : 1
     * openidStatus : 0
     * city :
     * loginType : 0
     * onlineStatus : 1
     * inputTime :
     * roleType : 0
     * cardNo :
     * totalRecharge : 0
     * lastLoginIp :
     * score : 0
     * password : 123456
     * province :
     * email :
     * height : 0
     * info :
     * openidZfb :
     * registerTime :
     * nickName :
     * openid :
     * sex : 0
     * weight : 0
     * userName : 15001396144
     * birthDate :
     * userId : 1000000000019713
     * userKey : ACB247E359379CF698A0146C34235589
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEwMDAwMDAwMDAwMTk3MTMiLCJleHAiOjE1NjE2MjU4MzcsImp0aSI6IjNiMTA5YjAxLWUwYjctNGNjYi1iNTk2LTVkYTgyNDdiMThmNyJ9.KaAlMXr28tTbUfcUAmnHx22jLbmeX51RGp9q7ag3yPc
     * lastLoginTime :
     * headImgUrl :
     * phone : 15001396144
     * sourceType : 0
     * retreatAmount : 0
     * numberStatus : 0
     * enableStatus : 0
     * accountRest : 0
     */

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
    private int openidStatus;
    private int score;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getOpenidStatus() {
        return openidStatus;
    }

    public void setOpenidStatus(int openidStatus) {
        this.openidStatus = openidStatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(int totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOpenidZfb() {
        return openidZfb;
    }

    public void setOpenidZfb(String openidZfb) {
        this.openidZfb = openidZfb;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public int getRetreatAmount() {
        return retreatAmount;
    }

    public void setRetreatAmount(int retreatAmount) {
        this.retreatAmount = retreatAmount;
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

    public int getAccountRest() {
        return accountRest;
    }

    public void setAccountRest(int accountRest) {
        this.accountRest = accountRest;
    }
}
