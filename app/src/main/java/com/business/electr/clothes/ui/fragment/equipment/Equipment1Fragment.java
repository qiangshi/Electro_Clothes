package com.business.electr.clothes.ui.fragment.equipment;

import android.view.View;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.ui.activity.equipment.EquipmentActivity;
import com.business.electr.clothes.ui.fragment.BaseFragment;

import butterknife.OnClick;

public class Equipment1Fragment extends BaseFragment {


    public Equipment1Fragment() {
    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_equipment1;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @OnClick({R.id.img_elect, R.id.img_smart_socks})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_elect:
                ((EquipmentActivity)getActivity()).setCurrentPage(1);
                break;
            case R.id.img_smart_socks:
                break;
        }
    }
}
