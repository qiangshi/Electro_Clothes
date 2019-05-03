package com.business.electr.clothes.ui.activity.equipment;

import android.widget.TextView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.sankuai.waimai.router.annotation.RouterUri;

import butterknife.BindView;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 描述：精测结果界面
 */
@RouterUri(path = {RouterCons.CREATE_MEASURE_RESULT})
public class MeasureResultActivity extends BaseActivity {


    @BindView(R.id.tv_average)
    TextView tvAverage;
    @BindView(R.id.tv_mini)
    TextView tvMini;
    @BindView(R.id.tv_high)
    TextView tvHigh;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_measure_result;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initRightTitle("60s精测结果", R.drawable.icon_share);
    }

}
