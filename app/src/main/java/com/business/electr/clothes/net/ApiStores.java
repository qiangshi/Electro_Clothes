package com.business.electr.clothes.net;

import com.business.electr.clothes.bean.UserBean;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by zenghaiqiang on 2018/12/21. 描述：
 */
public interface ApiStores {

    /**
     * 注册
     * @param userName
     * @param password
     * @param verify
     * @return
     */
    @POST("login/regist.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<UserBean>> requestRegister(
            @Field("userName") @NonNull String userName, @Field("password") @NonNull String password,
            @Field("verify") @NonNull String verify);


    /**
     * 登录
     * @param userName
     * @param password
     * @param roleType
     * @return
     */
    @POST("login/login.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<UserBean>> requestLogin(
            @Field("userName") @NonNull String userName, @Field("password") @NonNull String password,
            @Field("roleType") String roleType);

    /**
     * 刷新token
     * @param token
     * @return
     */
    @POST("login/refresh.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestRefresh(
            @Field("token") @NonNull String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    @POST("login/logout.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestLogout(
            @Field("token") @NonNull String token);

    @POST("sendVerify.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestVerificationCode(
            @Field("phone") @NonNull String phone);

    /**********************************************业务接口**************************************/


    /**
     * 获取角色类型
     * @param userId
     * @return
     */
    @POST("getRoleType.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Integer>> requestRoleType(
            @Field("userId") @NonNull long userId);


    /**
     * 获取用户信息
     */
    @POST("getUserInfo.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<UserBean>> requestUserInfo(
            @Field("userId") @NonNull long userId);

    /**
     * 更新用户密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @POST("updatePassword.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestUpdatePassword(
            @Field("userId") @NonNull long userId,@Field("oldPassword") @NonNull String oldPassword,
            @Field("newPassword") @NonNull String newPassword);

    /**
     * 更新用户信息
     */
    @POST("updateUserInfo.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestUpdateUserInfo(
            @Field("userId") @NonNull long userId,@Field("sex") int sex,
            @Field("height") int height,@Field("weight") int weight,
            @Field("birthDate") String birthDate);

    /**
     * 获取设备信息
     * @param deviceNo
     * @return
     */
    @POST("getDevice.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestDevice(
            @Field("deviceNo") @NonNull String deviceNo);


    /**
     * 更新设备标题
     * @param deviceId  设备序号
     * @param deviceTitle  设备标题
     * @return
     */
    @POST("updateTitle.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestUpdateTitle(
            @Field("deviceId") @NonNull int deviceId,@Field("deviceTitle") @NonNull String deviceTitle);

    /**
     * 绑定设备
     * @param userId  用户编号
     * @param deviceNo  设备序号
     * @param rssi    信号
     * @param deviceType  设备类型
     * @param sideType   左右类型
     * @param deviceId   设备编号
     * @param code    二维码
     * @param deviceName  设备名称
     * @return
     */
    @POST("bindDevice.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestBindDevice(
            @Field("userId") @NonNull long userId,@Field("deviceNo") @NonNull String deviceNo,
            @Field("rssi") @NonNull int rssi,@Field("deviceType") @NonNull String deviceType,
            @Field("sideType") @NonNull int sideType,@Field("deviceId") @NonNull int deviceId,
            @Field("code") @NonNull int code,@Field("deviceName") @NonNull String deviceName
    );

    /**
     * 停止工作
     * @param userId
     * @param deviceId
     * @return
     */
    @POST("endWork.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestEndWork(
            @Field("userId") @NonNull long userId,@Field("deviceId") @NonNull int deviceId
    );

    /**
     * 解绑设备
     * @param userId
     * @param deviceId
     * @return
     */
    @POST("unbindDevice.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestUnBindDevice(
            @Field("userId") @NonNull long userId,@Field("deviceId") @NonNull int deviceId
    );


    /**
     * 获取服务器时间
     * @param userId
     * @return
     */
    @POST("getServerTime.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestServerTime(
            @Field("userId") @NonNull long userId
    );

    /**
     * 接收心电记录
     * @param userId
     * @param deviceId
     * @param value
     * @return
     */
    @POST("recvElectrRecord.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestElectrRecord(
            @Field("userId") @NonNull long userId,@Field("deviceId") @NonNull int deviceId,
            @Field("value") @NonNull String value);

    /**
     * 发起心电交互
     * @param userId
     * @param dayNo
     * @param innerNo
     * @param transNo
     * @return
     */
    @POST("sendElectrAsk.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestSendElectr(
            @Field("userId") @NonNull long userId,@Field("dayNo") @NonNull int dayNo,
            @Field("innerNo") @NonNull long innerNo,@Field("transNo") @NonNull String transNo);

    /**
     * 接收心电交互
     * @param userId
     * @param value
     * @return
     */
    @POST("recvElectrAsk.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestSendElectr(
            @Field("userId") @NonNull long userId,@Field("value") @NonNull String value);

    /**
     * 获取心电记录页数
     * @param userId
     * @param dayNo
     * @param pageSize
     * @return
     */
    @POST("getElectrRecordPage.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Integer>> requestElectrPage(
            @Field("userId") @NonNull long userId,@Field("dayNo") @NonNull int dayNo,
            @Field("pageSize") @NonNull int pageSize);


    /**
     * 获取心电记录
     * @param userId
     * @param dayNo
     * @param startIndex
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @POST("getElectrRecord.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Integer>> requestElectrRecord(
            @Field("userId") @NonNull long userId,@Field("dayNo") @NonNull int dayNo,
            @Field("startIndex") @NonNull int startIndex,@Field("pageIndex") @NonNull int pageIndex,
            @Field("pageSize") @NonNull int pageSize);


    /**
     * 获取心电报告
     * @param userId
     * @param dayNo
     * @return
     */
    @POST("getElectrReport.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Integer>> requestElectrRepord(
            @Field("userId") @NonNull long userId,@Field("dayNo") @NonNull int dayNo);


    /**
     * 用户反馈
     */
    @POST("basic/web/index.php?r=user/feedback")
    @Multipart
    Observable<BaseApiResponse<String>> requestFeedBack(
            @Part MultipartBody.Part userId, @Part MultipartBody.Part token,
            @Part MultipartBody.Part content, @Part MultipartBody.Part pic1,
            @Part MultipartBody.Part pic2, @Part MultipartBody.Part pic3,
            @Part MultipartBody.Part contact);



}
