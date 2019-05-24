package com.business.electr.clothes.mvp.presenter.login;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.MapModel;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.login.LoginView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.utils.DataCheckUtils;
import com.business.electr.clothes.utils.MLog;

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
                .addParams("userName",mobilePhone)
                .addParams("phone",password)
                .addParams("roleType",0).toRequestBody();
        addSubscription(
                apiStores.requestCodeLogin(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<UserBean>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<UserBean>> data) {
                        if (data.getData() == null) {
                            mView.toastMessage(R.string.please_get_code);
                        } else {
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
                .addParams("phone",mobilePhone)
                .addParams("verify",verify).toRequestBody();
        addSubscription(
                apiStores.requestCodeLogin(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<UserBean>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<UserBean>> data) {
                        if (data.getData() == null) {
                            mView.toastMessage(R.string.please_get_code);
                        } else {
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
                .addParams("phone",mobilePhone).toRequestBody();
        //发送手机验证码
        addSubscription(
                apiStores.requestVerificationCode(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<String>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<String>> data) {
                        // TODO: 2019/4/28 根据发送验证码回调判断是否是新用户
                        MLog.e("====zhq====>111<"+data);
                        String isRe = data.getData().getMap();
                        JSONObject object = JSONObject.parseObject(isRe);
                        int isResiger = object.getInteger("isRegist");
                        MLog.e("====zhq====>111<"+isResiger);
                        boolean isRegist ;
                        if(isResiger == 1){
                            isRegist = false;
                        }else {
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
