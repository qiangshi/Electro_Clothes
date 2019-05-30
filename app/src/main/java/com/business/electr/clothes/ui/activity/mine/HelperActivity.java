package com.business.electr.clothes.ui.activity.mine;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.VersionBean;
import com.business.electr.clothes.mvp.presenter.mine.HelperPresenter;
import com.business.electr.clothes.mvp.view.mine.HelperView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.CommonUtils;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_HELPER})
public class HelperActivity extends BaseActivity<HelperPresenter> implements HelperView {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_helper;
    }

    @Override
    protected HelperPresenter getPresenter() {
        return new HelperPresenter(this);
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
                new DefaultUriRequest(this, RouterCons.CREATE_FAQ)
                        .start();
                break;
            case R.id.lin_update_version:
                mPresenter.getVersionInfo();
                break;
            case R.id.lin_link:
                new DefaultUriRequest(this,RouterCons.CREATE_LINK_OUR)
                        .start();
                break;
            case R.id.lin_about_our:
                new DefaultUriRequest(this,RouterCons.CREATE_ABOUT_OUR)
                        .start();
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

    @Override
    public void getVersionSuccess(VersionBean bean) {
        long versionCode =  CommonUtils.getVersionCode();
        if(versionCode < bean.getVersionNo()){
            Uri uri = Uri.parse(bean.getFilePath());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            toastMessage(R.string.used_update_news);
        }
    }
}
