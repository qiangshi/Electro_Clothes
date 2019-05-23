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

    @BindView(R.id.rel_sleep)//睡眠
    RelativeLayout relSleep;
    @BindView(R.id.day_task_view_sleep)
    DayTaskView dayTaskViewSleep;
    @BindView(R.id.img_center_sleep)
    ImageView imgCenterSleep;

    @BindView(R.id.rel_elect)//心跳数
    RelativeLayout relElect;
    @BindView(R.id.day_task_view)
    DayTaskView dayTaskViewElect;
    @BindView(R.id.img_center_elect)
    ImageView imgCenterElect;

    @BindView(R.id.rel_number)//步数
    RelativeLayout relNumber;
    @BindView(R.id.day_task_view_number)
    DayTaskView dayTaskViewNumber;
    @BindView(R.id.img_center_number)
    ImageView imgCenterNumber;



    public TaskFragment() {
    }


    @Override
    protected void initEventAndData() {
//        dayTaskView.setFinishWidth(CommonUtils.dp2px(getActivity(),120));
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
