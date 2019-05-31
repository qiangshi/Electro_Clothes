package com.business.electr.clothes.ui.activity.mine;

import android.view.View;
import android.widget.ImageView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.presenter.mine.MyIHIPresenter;
import com.business.electr.clothes.mvp.view.mine.MyIHIView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.MLog;
import com.sankuai.waimai.router.annotation.RouterUri;

import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_MY_IHI})
public class MyIHIActivity extends BaseActivity<MyIHIPresenter> implements MyIHIView {


    @BindView(R.id.img_125)
    ImageView img125;
    @BindView(R.id.img_1000)
    ImageView img1000;
    private int sampling = 125;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_ihi;
    }

    @Override
    protected MyIHIPresenter getPresenter() {
        return new MyIHIPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.my_ihi));
        mPresenter.getSampling();
    }


    @OnClick({R.id.ll_125_sampling, R.id.ll_1000_sampling, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_125_sampling:
                sampling = 125;
                img125.setVisibility(View.VISIBLE);
                img1000.setVisibility(View.GONE);
                break;
            case R.id.ll_1000_sampling:
                sampling = 1000;
                img1000.setVisibility(View.VISIBLE);
                img125.setVisibility(View.GONE);
                break;
            case R.id.tv_commit:
                mPresenter.updateSampling(sampling);
                break;
        }
    }

    @Override
    public void getSuccess(int sampling) {
        this.sampling = sampling;
        if (sampling == 125) {
            img125.setVisibility(View.VISIBLE);
            img1000.setVisibility(View.GONE);
        } else {
            img1000.setVisibility(View.VISIBLE);
            img125.setVisibility(View.GONE);
        }
    }
}
