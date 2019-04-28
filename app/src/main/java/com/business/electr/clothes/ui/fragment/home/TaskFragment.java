package com.business.electr.clothes.ui.fragment.home;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.TaskPresenter;
import com.business.electr.clothes.mvp.view.home.TaskView;
import com.business.electr.clothes.ui.fragment.BaseFragment;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 任务Fragment
 */
public class TaskFragment extends BaseFragment<TaskPresenter> implements TaskView {


    public TaskFragment() {}



    @Override
    protected void initEventAndData() {

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

}
