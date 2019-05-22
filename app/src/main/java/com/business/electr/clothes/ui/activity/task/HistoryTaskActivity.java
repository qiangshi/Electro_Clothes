package com.business.electr.clothes.ui.activity.task;

import android.view.View;
import android.widget.TextView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.ui.fragment.task.HistoryTaskFragment;
import com.sankuai.waimai.router.annotation.RouterUri;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_TASK_HISTORY})
public class HistoryTaskActivity extends BaseActivity {


    @BindView(R.id.vp_history_task)
    ViewPager viewPager;
    @BindView(R.id.tv_all_task)
    TextView tvAllTask;
    @BindView(R.id.v_all_task)
    View vAllTask;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.v_finish_task)
    View vFinishTask;
    @BindView(R.id.tv_no_finish)
    TextView tvNoFinish;
    @BindView(R.id.v_no_finish)
    View vNoFinish;
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_task;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.history_task));
        initData();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 3; i++) {
            fragments.add(HistoryTaskFragment.getInstance(i));
        }
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                initTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setAdapter(fragmentPagerAdapter);
    }




    @OnClick({R.id.rel_all, R.id.rel_finish_task, R.id.rel_no_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_all:
                initTab(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.rel_finish_task:
                initTab(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.rel_no_finish:
                initTab(2);
                viewPager.setCurrentItem(2);
                break;
        }
    }

    private void initTab(int pos){
        switch (pos){
            case 0:
                tvAllTask.setTextColor(getResources().getColor(R.color.color_353535));
                vAllTask.setVisibility(View.VISIBLE);
                tvFinish.setTextColor(getResources().getColor(R.color.color_8c919b));
                vFinishTask.setVisibility(View.GONE);
                tvNoFinish.setTextColor(getResources().getColor(R.color.color_8c919b));
                vNoFinish.setVisibility(View.GONE);
                break;
            case 1:
                tvAllTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                vAllTask.setVisibility(View.GONE);
                tvFinish.setTextColor(getResources().getColor(R.color.color_353535));
                vFinishTask.setVisibility(View.VISIBLE);
                tvNoFinish.setTextColor(getResources().getColor(R.color.color_8c919b));
                vNoFinish.setVisibility(View.GONE);
                break;
            case 2:
                tvAllTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                vAllTask.setVisibility(View.GONE);
                tvFinish.setTextColor(getResources().getColor(R.color.color_8c919b));
                vFinishTask.setVisibility(View.GONE);
                tvNoFinish.setTextColor(getResources().getColor(R.color.color_353535));
                vNoFinish.setVisibility(View.VISIBLE);
                break;
        }
    }
}
