package com.business.electr.clothes.mvp.presenter.mine;

import android.text.TextUtils;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.MapModel;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.ModifyUserInfoView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.utils.DataCheckUtils;

import okhttp3.RequestBody;

/**
 * Created by zenghaiqiang on 2019/1/24.
 * 我的信息 P 层
 */

public class ModifyUserInfoPresenter extends BasePresenter<ModifyUserInfoView> {
    public ModifyUserInfoPresenter(ModifyUserInfoView view) {
        super(view);
    }


    /**
     * 获取用户信息
     */
    public void getUserInfo(){
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addCommonMap()
                .addParams("userId",DataCacheManager.getUserInfo().getUserId())
                .toRequestBody();
        addSubscription(apiStores.requestUserInfo(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<UserBean>>>() {
                    @Override
                    public void onError(ResponseException e) {

                    }

                    @Override
                    public void onNext(BaseApiResponse<MapModel<UserBean>> data) {
                        if(data.getData() != null){
                            mView.getUserInfoSuccess(data.getData().getMap());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 更新用户信息
     */
    public void updateUserInfo(int sex, String height, String weight, String birthDate){
        mView.showLoading();
        int heigh = 0,weigh = 0;
        if(!TextUtils.isEmpty(height)){
            if(DataCheckUtils.checkNumber(height)){
                heigh = Integer.valueOf(height);
            }else {
                mView.toastMessage(R.string.please_in_height);
                return;
            }
        }
        if(!TextUtils.isEmpty(weight)){
            if(DataCheckUtils.checkNumber(weight)){
                weigh = Integer.valueOf(weight);
            }else {
                mView.toastMessage(R.string.please_in_weight);
                return;
            }
        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addCommonMap()
                .addParams("userId",DataCacheManager.getUserInfo().getUserId())
                .addParams("sex",sex)
                .addParams("height",heigh)
                .addParams("weight",weigh)
                .addParams("birthDate",birthDate)
                .toRequestBody();
        addSubscription(apiStores.requestUpdateUserInfo(requestBody),
                new BaseObserver<BaseApiResponse<String>>() {
                    @Override
                    public void onError(ResponseException e) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(BaseApiResponse<String> data) {
                        mView.hideLoading();
                        mView.toastMessage(R.string.preservation_success);
                        mView.updateUserInfoSuccess();
                        getUserInfo();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }


}
