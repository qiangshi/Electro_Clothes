package com.business.electr.clothes.ui.activity.presentation;

import android.os.Bundle;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.adapter.Second60Adapter;
import com.sankuai.waimai.router.annotation.RouterUri;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

@RouterUri(path = {RouterCons.CREATE_SECOND_60})
public class Second60Activity extends BaseActivity {


    @BindView(R.id.rv_second_60)
    RecyclerView rvSecond60;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second60;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle("60s静态监测");
        rvSecond60.setLayoutManager(new LinearLayoutManager(this));
        Second60Adapter adapter = new Second60Adapter(this);
        rvSecond60.setAdapter(adapter);
    }


}
