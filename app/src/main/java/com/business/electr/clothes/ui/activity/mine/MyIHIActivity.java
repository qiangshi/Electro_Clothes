package com.business.electr.clothes.ui.activity.mine;

import android.view.View;
import android.widget.ImageView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.sankuai.waimai.router.annotation.RouterUri;

import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_MY_IHI})
public class MyIHIActivity extends BaseActivity {


    @BindView(R.id.img_125)
    ImageView img125;
    @BindView(R.id.img_1000)
    ImageView img1000;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_ihi;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle("我的iHi");

    }


    @OnClick({R.id.ll_125_sampling, R.id.ll_1000_sampling, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_125_sampling:
                img125.setVisibility(View.VISIBLE);
                img1000.setVisibility(View.GONE);
                break;
            case R.id.ll_1000_sampling:
                img1000.setVisibility(View.VISIBLE);
                img125.setVisibility(View.GONE);
                break;
            case R.id.tv_commit:
                break;
        }
    }
}
