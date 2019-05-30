package com.business.electr.clothes.mvp.presenter.mine;

import android.text.TextUtils;
import android.widget.Toast;
import com.business.electr.clothes.R;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.LinkOurView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.utils.MLog;
import okhttp3.RequestBody;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/30 10:50
 */
public class LinkOurPresenter extends BasePresenter<LinkOurView> {
    public LinkOurPresenter(LinkOurView view) {
        super(view);
    }

    /**
     * 联系我们
     * @param content
     * @param contactType 0 微信  1 邮箱
     */
    public void linkOur(String content,int contactType,String contact){
        if(TextUtils.isEmpty(content)){
            mView.toastMessage(R.string.please_input_feedback_info);
            return ;
        }
        if(TextUtils.isEmpty(contact)){
            mView.toastMessage(R.string.please_in_link_info);
            return;
        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addCommonMap()
                .addParams("userId", DataCacheManager.getUserInfo().getUserId())
                .addParams("content", content)
                .addParams("contactType", contactType)
                .addParams("contact",contact).toRequestBody();
        addSubscription(
                apiStores.requestLinkOur(requestBody),
                new BaseObserver<BaseApiResponse<String>>() {
                    @Override
                    public void onNext(BaseApiResponse<String> data) {
                        mView.toastMessage(R.string.thank_your_feedback);
                        mView.linkSuccess();
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
}
