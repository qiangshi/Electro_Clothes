package com.business.electr.clothes.ui.fragment.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.PresentationPresenter;
import com.business.electr.clothes.mvp.view.home.PresentationView;
import com.business.electr.clothes.ui.fragment.BaseFragment;

import butterknife.BindView;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/4/28
 */
public class PresentationFragment extends BaseFragment<PresentationPresenter> implements PresentationView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    ImageView btnBack;

    @Override
    protected void initEventAndData() {
        tvTitle.setText("报告");
        btnBack.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_presentation;
    }

    @Override
    protected PresentationPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }
}
