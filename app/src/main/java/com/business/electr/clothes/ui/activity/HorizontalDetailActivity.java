package com.business.electr.clothes.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.adapter.MultipleAdapter;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.view.ElectView;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/5/12.
 * 描述：横屏心电图详情页
 */
@RouterUri(path = {RouterCons.CREATE_HORIZONTAL_ELECT_DETAIL})
public class HorizontalDetailActivity extends BaseActivity {


    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.tv_house)
    TextView tvHouse;
    @BindView(R.id.rv_multiple)
    RecyclerView rvMultiple;
    @BindView(R.id.rv_time)
    RecyclerView rvTime;
    @BindView(R.id.tv_speed)
    TextView tvSpeed;
    @BindView(R.id.ev_detail)
    ElectView evDetail;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_5_mm)
    TextView tv5Mm;
    @BindView(R.id.tv_10)
    TextView tv10;
    @BindView(R.id.tv_10_mm)
    TextView tv10Mm;
    @BindView(R.id.tv_20)
    TextView tv20;
    @BindView(R.id.tv_20_mm)
    TextView tv20Mm;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_horizontal_detail;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initRecycler();
        evDetail.setHavePoint(true);
        evDetail.startDarw();
    }

    /**
     * 初始化recycler
     */
    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMultiple.setLayoutManager(layoutManager);
        MultipleAdapter multipleAdapter = new MultipleAdapter(this, Arrays.asList(getResources().getStringArray(R.array.multiple_list)));
        multipleAdapter.setSelectPos(2);
        multipleAdapter.setListener(new MultipleAdapter.OnMultipleClickListener() {
            @Override
            public void onItemClick(int pos) {
                switch (pos){
                    case 0:
                        evDetail.setMultiple(0.1f);
                        break;
                    case 1:
                        evDetail.setMultiple(0.25f);
                        break;
                    case 2:
                        evDetail.setMultiple(1f);
                        break;
                    case 3:
                        evDetail.setMultiple(5f);
                        break;
                    case 4:
                        evDetail.setMultiple(10f);
                        break;
                }
            }
        });
        rvMultiple.setAdapter(multipleAdapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTime.setLayoutManager(layout);
        MultipleAdapter timeAdapter = new MultipleAdapter(this, Arrays.asList(getResources().getStringArray(R.array.time_list)));
        timeAdapter.setListener(new MultipleAdapter.OnMultipleClickListener() {
            @Override
            public void onItemClick(int pos) {
                MLog.e("====zhq====>pos<"+pos);
                evDetail.setAllTime( 2*(pos+1));
            }
        });
        rvTime.setAdapter(timeAdapter);

    }



    @OnClick({R.id.img_back, R.id.ll_5, R.id.ll_10, R.id.ll_20})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_5:
                tv5.setTextColor(ContextCompat.getColor(this,R.color.color_353535));
                tv5Mm.setTextColor(ContextCompat.getColor(this,R.color.color_353535));
                tv10.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv10Mm.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv20.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv20Mm.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                break;
            case R.id.ll_10:
                tv5.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv5Mm.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv10.setTextColor(ContextCompat.getColor(this,R.color.color_353535));
                tv10Mm.setTextColor(ContextCompat.getColor(this,R.color.color_353535));
                tv20.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv20Mm.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                break;
            case R.id.ll_20:
                tv5.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv5Mm.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv10.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv10Mm.setTextColor(ContextCompat.getColor(this,R.color.color_91959F));
                tv20.setTextColor(ContextCompat.getColor(this,R.color.color_353535));
                tv20Mm.setTextColor(ContextCompat.getColor(this,R.color.color_353535));
                break;
        }
    }
}
