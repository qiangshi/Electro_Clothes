package com.business.electr.clothes.ui.fragment.home;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.home.HistoryPresenter;
import com.business.electr.clothes.mvp.view.home.HistoryView;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.utils.MLog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 历史Fragment
 */
public class HistoryFragment extends BaseFragment<HistoryPresenter> implements HistoryView, OnRefreshListener {

    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    ImageView btnBack;


    @Override
    protected void initEventAndData() {
        tvTitle.setText("历史");
        btnBack.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected HistoryPresenter getPresenter() {
        return new HistoryPresenter(this);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        MLog.d("onFragmentVisibleChange");
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(2000);
    }


}
