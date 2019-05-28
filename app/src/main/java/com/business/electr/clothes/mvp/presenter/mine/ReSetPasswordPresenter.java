package com.business.electr.clothes.mvp.presenter.mine;

import android.text.TextUtils;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.ReSetPasswordView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.utils.DataCheckUtils;

import okhttp3.RequestBody;
import retrofit2.http.Field;

/**
 * @ClassName: ReSetPasswordPresenter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/4 16:38
 */
public class ReSetPasswordPresenter extends BasePresenter<ReSetPasswordView> {
    public ReSetPasswordPresenter(ReSetPasswordView view) {
        super(view);
    }

    /**
     * 已知旧密码更改密码
     * @param oldPassword
     * @param newPassword
     */
    public void updateUserPassword(String oldPassword, String newPassword,String againPassword) {
        if(TextUtils.isEmpty(oldPassword)){
            mView.toastMessage(R.string.please_in_old_password);
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
                .addCommonMap()
                .addParams("userId", DataCacheManager.getUserInfo().getUserId())
                .addParams("oldPassword", oldPassword)
                .addParams("newPassword", newPassword)
                .toRequestBody();
        addSubscription(
                apiStores.requestUpdatePassword(requestBody),
                new BaseObserver<BaseApiResponse<String>>() {
                    @Override
                    public void onError(ResponseException e) {
                        mView.toastMessage(R.string.password_err);
                    }

                    @Override
                    public void onNext(BaseApiResponse<String> o) {
                        mView.toastMessage(R.string.modify_success);
                        mView.reSetSuccess();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
