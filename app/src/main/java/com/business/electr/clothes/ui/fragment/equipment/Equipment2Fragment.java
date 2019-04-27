package com.business.electr.clothes.ui.fragment.equipment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.ui.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Equipment2Fragment extends BaseFragment {


    public Equipment2Fragment() {
        // Required empty public constructor
    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_equipment2;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

}
