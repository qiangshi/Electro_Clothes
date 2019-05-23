package com.business.electr.clothes.ui.activity.task;

import android.app.Activity;
import android.os.Bundle;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.ui.activity.BaseActivity;

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

    }
}
