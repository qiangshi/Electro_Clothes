package com.business.electr.clothes.ui.activity.mine;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.TaskBean;
import com.business.electr.clothes.mvp.presenter.mine.TaskTargetPresenter;
import com.business.electr.clothes.mvp.view.TaskTargetView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.view.HorizontalProcessor;
import com.sankuai.waimai.router.annotation.RouterUri;
import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_TASK_TARGET})
public class TaskTargetActivity extends BaseActivity<TaskTargetPresenter> implements TaskTargetView {


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
    protected TaskTargetPresenter getPresenter() {
        return new TaskTargetPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        initTitle("");
        mPresenter.getTaskInfo();
    }


    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {//获取并存储设置的信息
        MLog.e("====zhq====>sleep<"+hpSleep.getSizeNumber());
        MLog.e("====zhq====>elect<"+hpElect.getSizeNumber());
        MLog.e("====zhq====>number<"+hpNumber.getSizeNumber());
        mPresenter.updateTaskInfo(hpSleep.getSizeNumber(),(int)hpElect.getSizeNumber(),(int)hpNumber.getSizeNumber());
    }

    @Override
    public void getTaskInfoSuccess(TaskBean bean) {
        hpSleep.setCurrent((float) bean.getSleepTime());
        hpElect.setCurrent(bean.getHeartNum());
        hpNumber.setCurrent(bean.getStepNum());
    }



}
