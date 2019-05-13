package com.business.electr.clothes.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.view.ElectView;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/1/5.
 * 描述：心电图详情页
 */
@RouterUri(path = {RouterCons.CREATE_ELECT_DETAIL})
public class ElectDetailActivity extends BaseActivity {

    @BindView(R.id.tv_house)
    TextView tvHouse;
    @BindView(R.id.tv_speed)
    TextView tvSpeed;
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
        initRightTitle(title, R.drawable.icon_share);
        electDetail.setHavePoint(true);
        electDetail.startDarw();
    }


    @OnClick({R.id.img_horizontal, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_horizontal://切换横屏
                new DefaultUriRequest(this,RouterCons.CREATE_HORIZONTAL_ELECT_DETAIL)
                        .start();
                break;
            case R.id.tv_commit:
                break;
        }
    }
}
