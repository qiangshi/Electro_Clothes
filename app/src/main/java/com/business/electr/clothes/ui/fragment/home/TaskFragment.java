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
import com.business.electr.clothes.view.TodayTaskCoverView;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 任务Fragment
 */
public class TaskFragment extends BaseFragment<TaskPresenter> implements TaskView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rel_task_back)
    RelativeLayout relTaskBack;//背景图
    @BindView(R.id.ttcv_sleep)
    TodayTaskCoverView ttcvSleep;
    @BindView(R.id.ttcv_elect)
    TodayTaskCoverView ttcvElect;
    @BindView(R.id.ttcv_number)
    TodayTaskCoverView ttcvNumber;

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
        ttcvSleep.setRatio(4.0f/8);
        imgCenterSleep.setVisibility(View.GONE);
        ttcvElect.setRatio(20000.0f/20000);
        imgCenterElect.setVisibility(View.GONE);
        relNumber.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.white));
        ttcvNumber.setVisibility(View.INVISIBLE);
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
