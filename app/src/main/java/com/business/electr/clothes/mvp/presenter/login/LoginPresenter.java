package com.business.electr.clothes.mvp.presenter.login;

import android.text.TextUtils;
import android.widget.EditText;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.login.LoginView;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.utils.DataCheckUtils;
import com.business.electr.clothes.utils.MLog;

import io.reactivex.disposables.Disposable;

/**
 * Created by zenghaiqiang on 2019/1/16.
 */

public class LoginPresenter extends BasePresenter<LoginView> {


    public LoginPresenter(LoginView view) {
        super(view);
    }


    /**
     * 手机验证码登录
     *
     * @param mobilePhone
     * @param verificationCode
     */
    public void requestLogin(String mobilePhone, String verificationCode, boolean isChecked) {

        if (TextUtils.isEmpty(mobilePhone)) {
            //请输入手机号
            mView.toastMessage(R.string.hint_input_phone_num);
            return;
        } else if (!DataCheckUtils.checkPhone(mobilePhone)) {
            mView.toastMessage(R.string.msg_phone_num_error);
            return;
        }
        if (null == verificationCode || verificationCode.length() != 6) {
            mView.toastMessage(R.string.msg_verification_code_error);
            return;
        }
        if (!isChecked) {
            mView.toastMessage(R.string.read_user_agreement);
            return;
        }
        addSubscription(
                apiStores.requestLogin(mobilePhone, verificationCode,"roleType"),
                new BaseObserver<BaseApiResponse<UserBean>>() {
                    @Override
                    public void onNext(BaseApiResponse<UserBean> data) {
                        if (data.getData() == null) {
                            mView.toastMessage(R.string.please_get_code);
                        } else {
//                            mView.loginSuccess(data.getData());
                        }
                    }

                    @Override
                    public void onError(ResponseException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        MLog.d("SelfSelectPresenter onComplete:");
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

        //发送手机验证码
        addSubscription(
                apiStores.requestVerificationCode(mobilePhone),
                new BaseObserver<BaseApiResponse<String>>() {
                    @Override
                    public void onNext(BaseApiResponse<String> data) {
                        if (!TextUtils.isEmpty(data.getData().toString())) {
                            mView.toastMessage(R.string.get_success);
                        }
                        mView.sendSuccess();
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
