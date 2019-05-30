package com.business.electr.clothes.mvp.presenter.mine;

import android.widget.Toast;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.MapModel;
import com.business.electr.clothes.bean.TaskBean;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.TaskTargetView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import okhttp3.RequestBody;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/30 11:19
 */
public class TaskTargetPresenter extends BasePresenter<TaskTargetView> {
    public TaskTargetPresenter(TaskTargetView view) {
        super(view);
    }

    /**
     * 获取任务信息
     */
    public void getTaskInfo(){
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addParams("userId", DataCacheManager.getUserInfo().getUserId()).toRequestBody();
        addSubscription(
                apiStores.requestGetTaskInfo(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<TaskBean>>>() {
                    @Override
                    public void onNext(BaseApiResponse<MapModel<TaskBean>> data) {
                        mView.getTaskInfoSuccess(data.getData().getMap());
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
     * 修改任务信息
     */
    public void updateTaskInfo(float sleepTime,int heartBeat, int stepNum){
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addParams("userId", DataCacheManager.getUserInfo().getUserId())
                .addParams("sleepTime",sleepTime)
                .addParams("heartBeat",heartBeat)
                .addParams("stepNum",stepNum).toRequestBody();
        addSubscription(
                apiStores.requestUpdateTaskInfo(requestBody),
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
