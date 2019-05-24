package com.business.electr.clothes.ui.activity.task;


import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.sankuai.waimai.router.annotation.RouterUri;

@RouterUri(path = {RouterCons.CREATE_SHARE_TASK})
public class ShareActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
    }
}
