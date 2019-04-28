package com.business.electr.clothes.net;

import com.business.electr.clothes.bean.UserBean;
import androidx.annotation.NonNull;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
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
     * @return
     */
    @POST("login/regist.do")
    Observable<BaseApiResponse<UserBean>> requestRegister(@Body RequestBody requestBody);


    /**
     * 登录
     * @return
     */
    @POST("login/login.do")
    Observable<BaseApiResponse<UserBean>> requestLogin(@Body RequestBody requestBody);

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

    /**
     * 发送验证码
     * @param requestBody
     * @return
     */
    @POST("sendVerify.do")
    Observable<BaseApiResponse<String>> requestVerificationCode(@Body RequestBody requestBody);

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
    Observable<BaseApiResponse<Integer>> requestElectrReport(
            @Field("userId") @NonNull long userId,@Field("dayNo") @NonNull int dayNo);


    /**
     * 接收心率记录
     * @param userId
     * @param deviceId
     * @param value
     * @return
     */
    @POST("recvHrateRecord.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestHrateRecord(
            @Field("userId") @NonNull long userId, @Field("deviceId") @NonNull int deviceId,
            @Field("value") @NonNull String value);


    /**
     * 获取心率报告
     * @param userId   用户编号
     * @param deviceId  设备编号
     * @param value  值
     * @param gatherTime  采集时间
     * @param stepNum   步数
     * @param distance  路程
     * @param carlo  卡路里
     * @param rateMax 心率最低
     * @param rateMin 心率最高
     * @param rateAvg 心率平均
     * @return
     */
    @POST("recvHrateReport.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestHrateReport(
            @Field("userId") @NonNull long userId, @Field("deviceId") @NonNull int deviceId,
            @Field("value") @NonNull String value, @Field("gatherTime") @NonNull int gatherTime,
            @Field("stepNum") @NonNull int stepNum, @Field("distance") @NonNull int distance,
            @Field("carlo") @NonNull int carlo, @Field("rateMax") @NonNull int rateMax,
            @Field("rateMin") @NonNull int rateMin, @Field("rateAvg") @NonNull int rateAvg);

    /**
     * 获取心率记录页数
     * @param userId
     * @param dayNo
     * @param pageSize
     * @return
     */
    @POST("getHrateRecordPage.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Integer>> requestHrateRecordPage(
            @Field("userId") @NonNull long userId, @Field("dayNo") @NonNull int dayNo,
            @Field("pageSize") @NonNull int pageSize);


    /**
     * 获取心率记录
     * @param userId
     * @param dayNo
     * @param startIndex  开始索引
     * @param pageIndex    页数
     * @param pageSize    每页数量
     * @return
     */
    @POST("getHrateRecord.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Integer>> requestHrateRecord(
            @Field("userId") @NonNull long userId, @Field("dayNo") @NonNull int dayNo,
            @Field("startIndex") @NonNull int startIndex, @Field("pageIndex") @NonNull int pageIndex,
            @Field("pageSize") @NonNull int pageSize);

    /**
     * 获取心率报告
     * @param userId
     * @param dayNo
     * @return
     */
    @POST("getHrateReport.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Integer>> requestHrateReport(
            @Field("userId") @NonNull long userId, @Field("dayNo") @NonNull int dayNo);



    /*************************************************医生部分***********************************************/

    /**
     * 保存医生信息
     * @param userId
     * @param departmentId
     * @param dutyId
     * @param organization
     * @param skill
     * @param info
     * @return
     */
    @POST("saveDoctor.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Long>> requestSaveDoctor(
            @Field("userId") @NonNull long userId, @Field("departmentId") @NonNull int departmentId,
            @Field("dutyId") @NonNull int dutyId, @Field("organization") @NonNull String organization,
            @Field("skill") @NonNull String skill, @Field("info") @NonNull String info);


    /**
     * 获取医生信息
     * @param userId
     * @return
     */
    @POST("getDoctor.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requesDoctorInfo(
            @Field("userId") @NonNull long userId);


    /**
     * 获取最新心电报告页数
     * @param dayNum  天数
     * @param pageSize  每页记录数
     * @return
     */
    @POST("getNewElectrReportPage.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Integer>> requesNewElectrReportPage(
            @Field("dayNum") @NonNull int dayNum,@Field("pageSize") @NonNull int pageSize);


    /**
     * 获取最新心电报告
     * @param dayNum
     * @param pageSize
     * @param startIndex
     * @param pageIndex
     * @return
     */
    @POST("getNewElectrReport.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Integer>> requesNewElectrReport(
            @Field("dayNum") @NonNull int dayNum,@Field("pageSize") @NonNull int pageSize,
            @Field("startIndex") @NonNull int startIndex, @Field("pageIndex") @NonNull int pageIndex);


    /**
     * 提交心电诊断
     * @param userId
     * @param electrReportId
     * @param doctorId
     * @param advise
     * @return
     */
    @POST("saveElectrCure.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<Long>> requesSaveElectrCure(
            @Field("userId") @NonNull long userId,@Field("electrReportId") @NonNull long electrReportId,
            @Field("doctorId") @NonNull long doctorId, @Field("advise") @NonNull String advise);


    /**
     * 获取部门信息
     * @param userId
     * @return
     */
    @POST("getDepartments.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestDepartments(
            @Field("userId") @NonNull long userId);


    /**
     * 获取职务信息
     * @param userId
     * @return
     */
    @POST("getDutys.do")
    @FormUrlEncoded
    Observable<BaseApiResponse<String>> requestDutys(
            @Field("userId") @NonNull long userId);

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
