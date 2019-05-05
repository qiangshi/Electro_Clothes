package com.business.electr.clothes.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.business.electr.clothes.R;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.view.ElectView;
import com.sankuai.waimai.router.annotation.RouterUri;

import butterknife.BindView;

/**
 * Created by zenghaiqiang on 2019/1/5.
 * 描述：心电图详情页
 */
@RouterUri(path = {RouterCons.CREATE_ELECT_DETAIL})
public class ElectDetailActivity extends BaseActivity {

    private String title;
    @BindView(R.id.ev_detail)
    ElectView electDetail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_elect_detail;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        title = getIntent().getStringExtra(Constant.TITLE_TIME);
        initRightTitle(title,R.drawable.icon_share);
        electDetail.startDarw();
    }
}
