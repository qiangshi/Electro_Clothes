package com.business.electr.clothes.ui.fragment.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.PresentationPresenter;
import com.business.electr.clothes.mvp.view.home.PresentationView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.utils.CommonUtils;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/4/28
 */
public class PresentationFragment extends BaseFragment<PresentationPresenter> implements PresentationView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_60_num)
    TextView tv60Num;
    @BindView(R.id.tv_ai_num)
    TextView tvAiNum;
    @BindView(R.id.tv_doctor_num)
    TextView tvDoctorNum;

    @Override
    protected void initEventAndData() {
        tvTitle.setText(getResources().getString(R.string.presentation));
        btnBack.setVisibility(View.GONE);
        tvTitle.setTextSize(18);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_presentation;
    }

    @Override
    protected PresentationPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @OnClick({R.id.rl_presentation_60, R.id.rl_presentation_ai, R.id.rl_presentation_doctor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_presentation_60:
                new DefaultUriRequest(getActivity(), RouterCons.CREATE_MEASURE_RESULT)
                        .start();
                break;
            case R.id.rl_presentation_ai:
                break;
            case R.id.rl_presentation_doctor:
                break;
        }
    }
}
