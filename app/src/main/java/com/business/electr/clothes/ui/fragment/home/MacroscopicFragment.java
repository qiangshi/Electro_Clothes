package com.business.electr.clothes.ui.fragment.home;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.MacroscopicPresenter;
import com.business.electr.clothes.mvp.view.home.MacroscopicView;
import com.business.electr.clothes.ui.fragment.BaseFragment;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/3/1 10:22
 */
public class MacroscopicFragment extends BaseFragment<MacroscopicPresenter> implements MacroscopicView {


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_macroscopic;
    }

    @Override
    protected MacroscopicPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }
}
