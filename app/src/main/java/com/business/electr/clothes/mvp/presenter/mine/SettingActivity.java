package com.business.electr.clothes.mvp.presenter.mine;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.ui.activity.BaseActivity;

public class SettingActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.set));
    }
}
