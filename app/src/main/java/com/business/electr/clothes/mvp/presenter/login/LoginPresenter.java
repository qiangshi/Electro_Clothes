package com.business.electr.clothes.mvp.presenter.login;

import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.MapModel;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.login.LoginView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.utils.DataCheckUtils;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.utils.SharePreferenceUtil;
import com.business.electr.clothes.utils.ToastUtils;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by zenghaiqiang on 2019/1/16.
 */

public class LoginPresenter extends BasePresenter<LoginView> {


    public LoginPresenter(LoginView view) {
        super(view);
    }

    /**
     * 账号密码登录
     *
     * @param mobilePhone
     * @param password
     */
    public void requestLogin(String mobilePhone, String password, boolean isChecked) {

        if (TextUtils.isEmpty(mobilePhone)) {
            //请输入手机号
            mView.toastMessage(R.string.hint_input_phone_num);
            return;
        } else if (!DataCheckUtils.checkPhone(mobilePhone)) {
            mView.toastMessage(R.string.msg_phone_num_error);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mView.toastMessage(R.string.please_in_password);
            return;
        }
//        if (!isChecked) {
//            mView.toastMessage(R.string.read_user_agreement);
//            return;
//        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addParams("userName", mobilePhone)
                .addParams("password", password)
                .addParams("roleType", 0).toRequestBody();
        addSubscription(
                apiStores.requestLogin(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<UserBean>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<UserBean>> data) {
                        if (data.getData() == null) {
                            Toast.makeText(context, data.getMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            saveLoginInfo(data.getData().getMap());
                            mView.loginSuccess(data.getData().getMap());
                        }
                    }

                    @Override
                    public void onError(ResponseException e) {
                        Toast.makeText(context, e.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        MLog.d("HistoryPresenter onComplete:");
                    }
                }
        );
    }


    /**
     * 保存用户信息
     */
    private void saveLoginInfo(UserBean userBean) {
        MLog.e("====zhq====>111<" + userBean.getToken());
        DataCacheManager.saveToken(userBean.getToken());
        DataCacheManager.saveUserInfo(userBean);
        SharePreferenceUtil.putBoolean(Constant.IS_LOGIN, true);
    }

    /**
     * 验证码登录
     *
     * @param mobilePhone
     * @param verify
     */
    public void checkPhoneCode(String mobilePhone, String verify) {

        if (TextUtils.isEmpty(mobilePhone)) {
            //请输入手机号
            mView.toastMessage(R.string.hint_input_phone_num);
            return;
        } else if (!DataCheckUtils.checkPhone(mobilePhone)) {
            mView.toastMessage(R.string.msg_phone_num_error);
            return;
        }
        if (TextUtils.isEmpty(verify)) {
            mView.toastMessage(R.string.please_in_code);
            return;
        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addParams("phone", mobilePhone)
                .addParams("verify", verify).toRequestBody();
        addSubscription(
                apiStores.requestCheckCode(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<String>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<String>> data) {
                        mView.checkPhoneCodeSuccess(verify);
                    }

                    @Override
                    public void onError(ResponseException e) {
                        Toast.makeText(context, e.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        MLog.d("HistoryPresenter onComplete:");
                    }
                }
        );
    }


    /**
     * 验证码登录
     *
     * @param mobilePhone
     * @param verify
     */
    public void requestCodeLogin(String mobilePhone, String verify, boolean isChecked) {

        if (TextUtils.isEmpty(mobilePhone)) {
            //请输入手机号
            mView.toastMessage(R.string.hint_input_phone_num);
            return;
        } else if (!DataCheckUtils.checkPhone(mobilePhone)) {
            mView.toastMessage(R.string.msg_phone_num_error);
            return;
        }
        if (TextUtils.isEmpty(verify)) {
            mView.toastMessage(R.string.please_in_code);
            return;
        }
//        if (!isChecked) {
//            mView.toastMessage(R.string.read_user_agreement);
//            return;
//        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addParams("phone", mobilePhone)
                .addParams("verify", verify).toRequestBody();
        addSubscription(
                apiStores.requestCodeLogin(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<UserBean>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<UserBean>> data) {
                        if (data.getData() == null) {
                            mView.toastMessage(R.string.please_get_code);
                        } else {
                            saveLoginInfo(data.getData().getMap());
                            mView.loginSuccess(data.getData().getMap());
                        }
                    }

                    @Override
                    public void onError(ResponseException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        MLog.d("HistoryPresenter onComplete:");
                    }
                }
        );
    }


    //发送手机验证码的方法
    public void sendVerificationCode(String mobilePhone) {
        if (TextUtils.isEmpty(mobilePhone)) {
            //请输入手机号
            mView.toastMessage(R.string.hint_input_phone_num);
            return;
        } else if (!DataCheckUtils.checkPhone(mobilePhone)) {
            mView.toastMessage(R.string.msg_phone_num_error);
            return;
        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addParams("phone", mobilePhone).toRequestBody();
        //发送手机验证码
        addSubscription(
                apiStores.requestVerificationCode(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<String>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<String>> data) {
                        String isRe = data.getData().getMap();
                        JSONObject object = JSONObject.parseObject(isRe);
                        int isResiger = object.getInteger("isRegist");
                        boolean isRegist;
                        if (isResiger == 1) {
                            isRegist = false;
                        } else {
                            isRegist = true;
                        }
                        mView.toastMessage(R.string.get_success);
                        mView.sendSuccess(isRegist);
                    }

                    @Override
                    public void onError(ResponseException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }

}
