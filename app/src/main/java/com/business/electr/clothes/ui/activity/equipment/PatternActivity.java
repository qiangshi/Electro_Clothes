package com.business.electr.clothes.ui.activity.equipment;

import android.view.View;
import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.sankuai.waimai.router.common.DefaultUriRequest;
import butterknife.OnClick;

public class PatternActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pattern;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.color_3F3F40));
        StatusBarUtil.setStatusBarDarkTheme(this, false);
    }


    @OnClick({R.id.tv_collection, R.id.tv_custom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_collection:
                new DefaultUriRequest(this, RouterCons.CREATE_EQUIPMENT)
                        .start();
                break;
            case R.id.tv_custom:
                new DefaultUriRequest(this, RouterCons.CREATE_EQUIPMENT)
                        .start();
                break;
        }
    }
}
