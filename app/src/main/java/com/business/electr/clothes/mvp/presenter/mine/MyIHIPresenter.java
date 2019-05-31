package com.business.electr.clothes.mvp.presenter.mine;

import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.MapModel;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.MyIHIView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;

import okhttp3.RequestBody;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/30 11:02
 */
public class MyIHIPresenter extends BasePresenter<MyIHIView> {
    public MyIHIPresenter(MyIHIView view) {
        super(view);
    }

    /**
     * 获取采用率
     */
    public void getSampling(){

        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addCommonMap()
                .addParams("userId", DataCacheManager.getUserInfo().getUserId()).toRequestBody();
        addSubscription(
                apiStores.requestGetSampling(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<String>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<String>> data) {
                        JSONObject object = JSONObject.parseObject(data.getData().getMap());
                        int sampling = object.getInteger("sampling");
                        mView.getSuccess(sampling);
                    }

                    @Override
                    public void onError(ResponseException e) {
                        Toast.makeText(context, e.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }


    /**
     * 修改信息
     * @param sampling
     */
    public void updateSampling(int sampling){
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addCommonMap()
                .addParams("userId", DataCacheManager.getUserInfo().getUserId())
                .addParams("sampling",sampling).toRequestBody();
        addSubscription(
                apiStores.requestUpdateSampling(requestBody),
                new BaseObserver<BaseApiResponse<String>>() {
                    @Override
                    public void onNext(BaseApiResponse<String> data) {
                        mView.toastMessage(R.string.modify_success);
                    }

                    @Override
                    public void onError(ResponseException e) {
                        Toast.makeText(context, e.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }
}
