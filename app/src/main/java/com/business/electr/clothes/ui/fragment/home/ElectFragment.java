package com.business.electr.clothes.ui.fragment.home;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.ElectPresenter;
import com.business.electr.clothes.view.ElectView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.view.DashBoard;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 我的心电记录
 */
public class ElectFragment extends BaseFragment<ElectPresenter>  {


    @BindView(R.id.dash_board)
    DashBoard dashBoard;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.img_bluetooth)
    ImageView imgBluetooth;
    @BindView(R.id.ev_elect)
    ElectView electView;

    public ElectFragment() {
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Random random = new Random();
            int curElect = random.nextInt(30);
            dashBoard.setCurElect(curElect + 60);
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Override
    protected void initEventAndData() {
        handler.sendEmptyMessageDelayed(0, 1000);
        electView.startDarw();
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

    @OnClick(R.id.img_bluetooth)
    public void onViewClicked() {
        new DefaultUriRequest(getActivity(), RouterCons.CREATE_EQUIPMENT)
                .start();
    }
}
