package com.business.electr.clothes.ui.fragment.home;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.PresentationPresenter;
import com.business.electr.clothes.mvp.view.home.PresentationView;
import com.business.electr.clothes.ui.fragment.BaseFragment;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/4/28
 */
public class PresentationFragment extends BaseFragment<PresentationPresenter> implements PresentationView {


    @Override
    protected void initEventAndData() {

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
