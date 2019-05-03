package com.business.electr.clothes.ui.fragment.home;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.ElectPresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.view.DashBoard;
import com.business.electr.clothes.view.ElectView;
import com.business.electr.clothes.view.QuantityView;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 我的心电记录
 */
public class ElectFragment extends BaseFragment<ElectPresenter> {


    @BindView(R.id.dash_board)
    DashBoard dashBoard;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.img_bluetooth)
    ImageView imgBluetooth;
    @BindView(R.id.ev_elect)
    ElectView electView;
    @BindView(R.id.quantity_view)
    QuantityView quantityView;
    @BindView(R.id.tv_mini)
    TextView tvMini;
    @BindView(R.id.tv_average)
    TextView tvAverage;
    @BindView(R.id.tv_high)
    TextView tvHigh;
    @BindView(R.id.tv_step_num)
    TextView tvStepNum;
    @BindView(R.id.tv_kilometre)
    TextView tvKilometre;
    @BindView(R.id.tv_calorie)
    TextView tvCalorie;
    @BindView(R.id.tv_house)
    TextView tvHouse;

    public ElectFragment() {
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Random random = new Random();
            int curElect = random.nextInt(60);
            dashBoard.setCurElect(curElect + 50);
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Override
    protected void initEventAndData() {
        handler.sendEmptyMessageDelayed(0, 1000);
        electView.startDarw();
        quantityView.setProcess(75);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_elect;
    }

    @Override
    protected ElectPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }


    @OnClick({R.id.img_bluetooth, R.id.bt_cesi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_bluetooth:
                new DefaultUriRequest(getActivity(), RouterCons.CREATE_EQUIPMENT)
                        .start();
                break;
            case R.id.bt_cesi:
                new DefaultUriRequest(getActivity(),RouterCons.CREATE_MEASUREMENT)
                        .start();
                break;
        }
    }
}
