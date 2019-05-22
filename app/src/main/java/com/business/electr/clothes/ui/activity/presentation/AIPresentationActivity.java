package com.business.electr.clothes.ui.activity.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.ui.fragment.presentation.AIPresentationFragment;
import com.google.android.material.tabs.TabLayout;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


/**
 * Created by zenghaiqiang on 2019/5/19.
 * 描述：AI报告
 */
@RouterUri(path = {RouterCons.CREATE_AI_PRESENTATION})
public class AIPresentationActivity extends BaseActivity {


    @BindView(R.id.tl_ai_presentation)
    TabLayout tabLayout;
    @BindView(R.id.vp_ai_presentation)
    ViewPager vpAiPresentation;


    private String[] mTabTitles = {"全部报告", "4月", "3月", "2月", "1月"};
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ai_presentation;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.ai_presentation));
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i< mTabTitles.length; i++){
            fragments.add(new AIPresentationFragment());
            tabLayout.addTab(tabLayout.newTab());
        }

        tabLayout.setupWithViewPager(vpAiPresentation);
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
        vpAiPresentation.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setupWithViewPager(vpAiPresentation);
                setTabStyle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpAiPresentation.setAdapter(fragmentPagerAdapter);
        for (int i = 0; i< mTabTitles.length; i++){
            tabLayout.getTabAt(i).setText(mTabTitles[i]);
        }
        setTabStyle(0);
    }



    //这个一定要在setAdapter之后执行
    private void setTabStyle(int position) {
        for (int i = mTabTitles.length - 1; i >= 0; i--) {//根据Tab数量循环来设置
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View view = LayoutInflater.from(this).inflate(R.layout.tab_title_layout, null);
                TextView tvTitle = view.findViewById(R.id.tv_tab_title);
                View line = view.findViewById(R.id.v_bottom_line);
                tvTitle.setText(mTabTitles[i]);
                if (i == position) {//第一个默认为选择样式
                    tvTitle.setTextColor(getResources().getColor(R.color.color_353535));
                    line.setVisibility(View.VISIBLE);
                } else {
                    tvTitle.setTextColor(getResources().getColor(R.color.color_8c919b));
                    line.setVisibility(View.GONE);
                }
                tab.setCustomView(view);//最后添加view到Tab上面
            }
        }
    }

}
