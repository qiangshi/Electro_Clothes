package com.business.electr.clothes.ui.activity.mine;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.sankuai.waimai.router.annotation.RouterUri;

@RouterUri(path = {RouterCons.CREATE_TASK_TARGET})
public class TaskTargetActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_target;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle("");
    }
}
