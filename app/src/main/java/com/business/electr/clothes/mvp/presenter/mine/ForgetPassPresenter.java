package com.business.electr.clothes.mvp.presenter.mine;

import android.text.TextUtils;
import android.widget.Toast;

import com.business.electr.clothes.R;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.ForgetPassView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.utils.DataCheckUtils;

import okhttp3.RequestBody;

/**
 * @ClassName: ForgetPassPresenter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/4 17:02
 */
public class ForgetPassPresenter extends BasePresenter<ForgetPassView> {
    public ForgetPassPresenter(ForgetPassView view) {
        super(view);
    }

    /**
     * 手机号更改密码
     * @param phone
     * @Param code
     * @param newPassword
     */
    public void forgetPassword(String phone,String code, String newPassword,String againPassword) {
        if(TextUtils.isEmpty(phone)){
            mView.toastMessage(R.string.hint_input_phone_num);
            return;
        }
        if(TextUtils.isEmpty(newPassword)){
            mView.toastMessage(R.string.please_in_new_password);
            return;
        }else {
            if(!DataCheckUtils.checkPwd(newPassword)){
                mView.toastMessage(R.string.password_no_standard);
                return;
            }
        }
        if(TextUtils.isEmpty(againPassword)){
            mView.toastMessage(R.string.please_in_again_password);
            return;
        }
        if(!newPassword.equals(againPassword)){
            mView.toastMessage(R.string.password_no_agreement);
            return;
        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addParams("phone", phone)
                .addParams("newPassword", newPassword)
                .toRequestBody();
        addSubscription(
                apiStores.requestForgetPassword(requestBody),
                new BaseObserver<BaseApiResponse<String>>() {
                    @Override
                    public void onError(ResponseException e) {
                        Toast.makeText(context,e.getErrorMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BaseApiResponse<String> o) {
                        mView.toastMessage(R.string.modify_success);
                        mView.modifyPasswordSuccess();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
