package com.business.electr.clothes.ui.activity.presentation;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.DoctorPresentationBean;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.adapter.DoctorPresentationAdapter;
import com.business.electr.clothes.view.ElectView;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/5/19.
 * 描述：医生报告
 */
@RouterUri(path = {RouterCons.CREATE_DOCTOR_PRESENTATION})
public class DoctorPresentationActivity extends BaseActivity {


    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.ev_detail)
    ElectView electDetail;
    @BindView(R.id.tv_record_time)
    TextView tvRecordTime;
    @BindView(R.id.tv_request_doctor)
    TextView tvRequestDoctor;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_kilometre)
    TextView tvKilometre;
    @BindView(R.id.tv_elect_calorie)
    TextView tvElectCalorie;
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;

    private List<DoctorPresentationBean> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_doctor_presentation;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.doctor_presentation));
        electDetail.setHavePoint(true);
        electDetail.startDarw();
        initList();
        scrollView.smoothScrollTo(0,20);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        DoctorPresentationAdapter adapter = new DoctorPresentationAdapter(this,list);
        rvComment.setAdapter(adapter);
    }

    private void initList() {
        list.add(new DoctorPresentationBean("","张珊","4-12","主治医生","想要改善与预防心率不齐，最简单的做法就 是调整日常生活型态：保持情绪稳定、调适 压力；控制体重；拒烟，避免摄取酒精性及 含咖啡因的饮料；均衡饮食，稳定摄取六大 类食物，不偏食；"));
        list.add(new DoctorPresentationBean("","李四","4-13","主治医生","想要改善与预防心率不齐，最简单的做法就 是调整日常生活型态：保持情绪稳定、调适 压力；控制体重；拒烟，避免摄取酒精性及 含咖啡因的饮料；均衡饮食，稳定摄取六大 类食物，不偏食；"));
        list.add(new DoctorPresentationBean("","王五","5-5","主治医生","想要改善与预防心率不齐，最简单的做法就 是调整日常生活型态：保持情绪稳定、调适 压力；控制体重；拒烟，避免摄取酒精性及 含咖啡因的饮料；均衡饮食，稳定摄取六大 类食物，不偏食；"));
        list.add(new DoctorPresentationBean("","黄器","5-19","主治医生","想要改善与预防心率不齐，最简单的做法就 是调整日常生活型态：保持情绪稳定、调适 压力；控制体重；拒烟，避免摄取酒精性及 含咖啡因的饮料；均衡饮食，稳定摄取六大 类食物，不偏食；"));
    }


    @OnClick(R.id.img_horizontal)
    public void onViewClicked() {
        new DefaultUriRequest(this, RouterCons.CREATE_HORIZONTAL_ELECT_DETAIL)
                .start();
    }

}
