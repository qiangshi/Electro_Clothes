package com.business.electr.clothes.ui.activity.mine;

import android.os.Bundle;
import android.view.View;

import com.business.electr.clothes.R;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.activity.WebViewContainerActivity;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.ButterKnife;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_HELPER})
public class HelperActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_helper;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {

    }


    @OnClick({R.id.img_back, R.id.lin_faq, R.id.lin_update_version, R.id.lin_link, R.id.lin_about_our, R.id.tv_service, R.id.tv_private})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.lin_faq:
                toastMessage(R.string.development_ing);
//                new DefaultUriRequest(this, RouterCons.CREATE_QUESTION_FEEDBACK)
//                        .start();
                break;
            case R.id.lin_update_version:
                toastMessage(R.string.used_update_news);
                break;
            case R.id.lin_link:
                new DefaultUriRequest(this,RouterCons.CREATE_LINK_OUR)
                        .start();
                break;
            case R.id.lin_about_our:
                toastMessage(R.string.development_ing);
                break;
            case R.id.tv_service:
//                new DefaultUriRequest(this,RouterCons.WEB_VIEW_INFO)
//                        .putExtra(Constant.URL,"file:///android_asset/apps/web/html/service.html")
//                        .putExtra(WebViewContainerActivity.TITLE,getResources().getString(R.string.user_argument))
//                        .start();
            break;
            case R.id.tv_private:
//                new DefaultUriRequest(this,RouterCons.WEB_VIEW_INFO)
//                        .putExtra(Constant.URL,"file:///android_asset/apps/web/html/privacy.html")
//                        .putExtra(WebViewContainerActivity.TITLE,getResources().getString(R.string.private_policy))
//                        .start();
                break;
        }
    }
}
