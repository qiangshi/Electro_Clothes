package com.business.electr.clothes.ui.fragment.home;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.TaskPresenter;
import com.business.electr.clothes.mvp.view.home.TaskView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.utils.CommonUtils;
import com.business.electr.clothes.view.DayTaskView;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 任务Fragment
 */
public class TaskFragment extends BaseFragment<TaskPresenter> implements TaskView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_left_bg_sleep)
    ImageView imgLeftBgSleep;
    @BindView(R.id.tv_sleep)
    TextView tvSleep;
    @BindView(R.id.tv_sleep_time)
    TextView tvSleepTime;
    @BindView(R.id.img_back_sleep)
    RelativeLayout imgBackSleep;
    @BindView(R.id.rel_sleep)
    RelativeLayout relSleep;


    @BindView(R.id.day_task_view)
    DayTaskView dayTaskView;
    @BindView(R.id.img_center_elect)
    ImageView imgCenterElect;
    @BindView(R.id.rel_elect)
    RelativeLayout relElect;
    @BindView(R.id.img_left_bg_number)
    ImageView imgLeftBgNumber;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_number_time)
    TextView tvNumberTime;
    @BindView(R.id.img_back_number)
    RelativeLayout imgBackNumber;
    @BindView(R.id.img_center_number)
    ImageView imgCenterNumber;
    @BindView(R.id.rel_number)
    RelativeLayout relNumber;


    public TaskFragment() {
    }


    @Override
    protected void initEventAndData() {
        dayTaskView.setFinishWidth(CommonUtils.dp2px(getActivity(),120));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    protected TaskPresenter getPresenter() {
        return new TaskPresenter(this);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @OnClick({R.id.img_history, R.id.tv_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_history:
            case R.id.tv_history:
                new DefaultUriRequest(getActivity(), RouterCons.CREATE_TASK_HISTORY)
                        .start();
                break;
        }
    }
}
