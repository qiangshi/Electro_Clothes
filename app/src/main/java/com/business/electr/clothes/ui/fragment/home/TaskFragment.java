package com.business.electr.clothes.ui.fragment.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.TaskPresenter;
import com.business.electr.clothes.mvp.view.home.TaskView;
import com.business.electr.clothes.ui.fragment.BaseFragment;

import butterknife.BindView;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 任务Fragment
 */
public class TaskFragment extends BaseFragment<TaskPresenter> implements TaskView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    ImageView btnBack;

    public TaskFragment() {}



    @Override
    protected void initEventAndData() {
        tvTitle.setText("任务");
        btnBack.setVisibility(View.GONE);
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
