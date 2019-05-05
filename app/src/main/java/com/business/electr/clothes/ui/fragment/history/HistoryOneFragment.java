package com.business.electr.clothes.ui.fragment.history;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.ui.fragment.BaseFragment;

/**
 * Created by zenghaiqiang on 2019/5/5.
 * 历史Fragment 第一种显示方式 图片
 */
public class HistoryOneFragment extends BaseFragment {


    public HistoryOneFragment() {
    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history_one;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

}
