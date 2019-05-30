package com.business.electr.clothes.mvp.presenter.mine;

import android.widget.Toast;
import com.business.electr.clothes.bean.MapModel;
import com.business.electr.clothes.bean.VersionBean;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.HelperView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import okhttp3.RequestBody;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/30 11:32
 */
public class HelperPresenter extends BasePresenter<HelperView> {
    public HelperPresenter(HelperView view) {
        super(view);
    }


    public void getVersionInfo(){
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addCommonMap().toRequestBody();
        addSubscription(
                apiStores.requestGetVersion(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<VersionBean>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<VersionBean>> data) {
                        mView.getVersionSuccess(data.getData().getMap());
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
