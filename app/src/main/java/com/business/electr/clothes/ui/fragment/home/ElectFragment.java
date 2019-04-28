package com.business.electr.clothes.ui.fragment.home;



import android.app.Fragment;
import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.ElectPresenter;
import com.business.electr.clothes.mvp.view.home.ElectView;
import com.business.electr.clothes.ui.fragment.BaseFragment;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 我的心电记录
 */
public class ElectFragment extends BaseFragment<ElectPresenter> implements ElectView {


    public ElectFragment() { }


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_elect;
    }

    @Override
    protected ElectPresenter getPresenter() {
        return new ElectPresenter(this);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

}
