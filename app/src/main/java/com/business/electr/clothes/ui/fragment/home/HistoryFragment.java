package com.business.electr.clothes.ui.fragment.home;


import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.home.HistoryPresenter;
import com.business.electr.clothes.mvp.view.home.HistoryView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.ui.fragment.dialog.EmptyFragment;
import com.business.electr.clothes.ui.fragment.history.HistoryOneFragment;
import com.business.electr.clothes.ui.fragment.history.HistoryTwoFragment;
import com.business.electr.clothes.ui.fragment.history.SelectTimeFragment;
import com.business.electr.clothes.utils.DateUtils;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.view.ConflictViewPager;
import com.business.electr.clothes.view.NoScrollViewPager;
import com.business.electr.clothes.view.TimeTypeView;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 历史Fragment
 */
public class HistoryFragment extends BaseFragment<HistoryPresenter> implements HistoryView, TimeTypeView.OnClickItemListener {

    @BindView(R.id.ttv_time)
    TimeTypeView ttvTime;
    @BindView(R.id.img_history_share)
    ImageView imgHistoryShare;
    @BindView(R.id.img_last_page)
    ImageView imgLastPage;
    @BindView(R.id.img_next_page)
    ImageView imgNextPage;
    @BindView(R.id.ll_select_time)
    LinearLayout llSelectTime;
    @BindView(R.id.vp_type)
    ConflictViewPager vpType;
    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.tv_time_month)
    TextView tvTimeMonth;

    private List<BaseFragment> fragments;
    private int timeType;//当前时间类型  0 ： 日   1： 周   2： 月   3： 年
    private long currMonth;

    @Override
    protected void initEventAndData() {
        ttvTime.setListener(this);
        initViewPager();
        initData();
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        fragments = new ArrayList<>();
        HistoryOneFragment historyOneFragment = new HistoryOneFragment();
        HistoryTwoFragment historyTwoFragment = new HistoryTwoFragment();
        fragments.add(historyOneFragment);
        fragments.add(historyTwoFragment);
        currMonth = System.currentTimeMillis();
        SimpleDateFormat sf = new SimpleDateFormat(Constant.DATE_FORMAT_5);
        tvTimeMonth.setText(DateUtils.getTime(currMonth,sf));

        vpType.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        vpType.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    img1.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bottom_select));
                    img2.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bottom_no_select));
                }else {
                    img1.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bottom_no_select));
                    img2.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon_bottom_select));
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initData() {
        String dataTime = DateUtils.getTime(System.currentTimeMillis(),new SimpleDateFormat(Constant.DATE_FORMAT_0));
        if(dataTime.equals(getResources().getString(R.string.current_data_time))){
            fragments = null;
        }
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
    public void onDayClick() {//点击日
        timeType = 0;
    }

    @Override
    public void onWeekClick() {//点击周
        timeType = 1;

    }

    @Override
    public void onMonthClick() {//点击月
        timeType = 2;
    }

    @Override
    public void onYearClick() {//点击年
        timeType = 3;
    }

    @OnClick({R.id.tv_time_month,R.id.img_last_page, R.id.img_next_page, R.id.img_history_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_time_month://显示当月日期选择
                ((BaseActivity)getActivity()).showSelectFragment(currMonth,new SelectTimeFragment.SelectTimeListener() {
                    @Override
                    public void onClickSelect(String day) {
                        tvTimeMonth.setText(day.substring(5));
                        currMonth = DateUtils.getLongByString(day,Constant.DATE_FORMAT_0);
                    }
                });
                break;
            case R.id.img_history_share://分享
                break;
            case R.id.img_last_page://上一页
                monthChange(-1);
                break;
            case R.id.img_next_page://下一页
                monthChange(1);
                break;
        }
    }





    /**
     * 月份增减
     */
    public void monthChange(int change) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(currMonth));
        calendar.add(Calendar.MONTH, change);
        currMonth = calendar.getTime().getTime();
        SimpleDateFormat sf = new SimpleDateFormat(Constant.DATE_FORMAT_5);
        tvTimeMonth.setText(DateUtils.getTime(currMonth,sf));
    }
}
