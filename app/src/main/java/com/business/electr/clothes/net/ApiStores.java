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

    @POST("basic/web/index.php?r=login/get-code")
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
    @POST("basic/web/index.php?r=user/get-user-info")
    @FormUrlEncoded
    Observable<BaseApiResponse<UserBean>> requestUserInfo(
            @Field("userId") @NonNull long userId);

    /**
     * 更新用户信息
     */
    @POST("basic/web/index.php?r=user/update-user")
    @Multipart
    Observable<BaseApiResponse<String>> requestUpdateUserInfo(
            @Part MultipartBody.Part userId, @Part MultipartBody.Part token,
            @Part MultipartBody.Part portrait, @Part MultipartBody.Part nickname,
            @Part MultipartBody.Part contact, @Part MultipartBody.Part gender,
            @Part MultipartBody.Part birthday);


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
