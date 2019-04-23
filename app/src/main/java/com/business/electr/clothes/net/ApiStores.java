package com.business.electr.clothes.net;

import com.business.electr.clothes.bean.LoginBean;

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

    @POST("basic/web/index.php?r=login/login")
    @FormUrlEncoded
    Observable<BaseApiResponse<LoginBean>> requestLogin(@Field("phone") @NonNull String phone,
                                                        @Field("code") @NonNull String code);

    @POST("basic/web/index.php?r=login/get-code")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestVerificationCode(
            @Field("phone") @NonNull String phone);


    /**
     * 获取用户信息
     */
    @POST("basic/web/index.php?r=user/get-user-info")
    @FormUrlEncoded
    Observable<BaseApiResponse<LoginBean.UserBean>> requestUserInfo(
            @Field("user_id") @NonNull String userId, @Field("token") @NonNull String token);

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
