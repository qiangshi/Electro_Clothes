package com.business.electr.clothes.mvp.presenter.mine;

import android.text.TextUtils;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.MapModel;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.ModifyPasswordView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.utils.DataCheckUtils;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.utils.SharePreferenceUtil;

import okhttp3.RequestBody;

/**
 * @ClassName: ModifyPasswordPresenter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/4/25 22:12
 */
public class ModifyPasswordPresenter extends BasePresenter<ModifyPasswordView> {
    public ModifyPasswordPresenter(ModifyPasswordView view) {
        super(view);
    }

    /**
     * 注册用户信息
     * @param phone
     * @param password
     * @param code
     */
    public void registerUser(String phone,String password,String againPassword,String code){
        if (TextUtils.isEmpty(phone)) {
            //请输入手机号
            mView.toastMessage(R.string.hint_input_phone_num);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mView.toastMessage(R.string.please_in_password);
            return;
        }
        if(TextUtils.isEmpty(againPassword)){
            mView.toastMessage(R.string.please_in_again_password);
            return;
        }
        if(!password.equals(againPassword)){
            mView.toastMessage(R.string.password_no_agreement);
            return;
        }
        if (TextUtils.isEmpty(code)) {
            mView.toastMessage(R.string.please_in_code);
            return;
        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addParams("userName",phone)
                .addParams("password",password)
                .addParams("verify",code).toRequestBody();
        addSubscription(
                apiStores.requestRegister(requestBody),
                new BaseObserver<BaseApiResponse<UserBean>>() {
                    @Override
                    public void onNext(BaseApiResponse<UserBean> data) {
                        if (data.getData() == null) {
                            mView.toastMessage(R.string.please_get_code);
                        } else {
//                            saveLoginInfo(data.getData());
                            mView.registerSuccess(data.getData());
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
                .addParams("password",password)
                .addParams("roleType",0).toRequestBody();
        addSubscription(
                apiStores.requestLogin(requestBody),
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


    /**
     * 保存用户信息
     */
    private void saveLoginInfo(UserBean userBean) {
        DataCacheManager.saveToken(userBean.getToken());
        DataCacheManager.saveUserInfo(userBean);
        SharePreferenceUtil.putBoolean(Constant.IS_LOGIN, true);
        SharePreferenceUtil.putBoolean(Constant.IS_REGISTER,true);
    }

}
