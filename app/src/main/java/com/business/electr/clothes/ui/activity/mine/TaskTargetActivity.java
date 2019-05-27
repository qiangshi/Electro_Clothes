package com.business.electr.clothes.ui.activity.mine;

import android.os.Bundle;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.view.HorizontalProcessor;
import com.sankuai.waimai.router.annotation.RouterUri;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_TASK_TARGET})
public class TaskTargetActivity extends BaseActivity {


    @BindView(R.id.hp_sleep)
    HorizontalProcessor hpSleep;
    @BindView(R.id.hp_elect)
    HorizontalProcessor hpElect;
    @BindView(R.id.hp_number)
    HorizontalProcessor hpNumber;

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


    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {//获取并存储设置的信息
        MLog.e("====zhq====>sleep<"+hpSleep.getSizeNumber());
        MLog.e("====zhq====>elect<"+hpElect.getSizeNumber());
        MLog.e("====zhq====>number<"+hpNumber.getSizeNumber());
    }
}
