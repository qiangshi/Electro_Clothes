package com.business.electr.clothes.ui.fragment.equipment;


import android.app.Fragment;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Equipment3Fragment extends BaseFragment {


    @BindView(R.id.tv_start)
    TextView tvStart;

    public Equipment3Fragment() {
    }

    @Override
    protected void initEventAndData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_equipment3;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @OnClick(R.id.tv_start)
    public void onViewClicked() {
        getActivity().finish();
    }
}
