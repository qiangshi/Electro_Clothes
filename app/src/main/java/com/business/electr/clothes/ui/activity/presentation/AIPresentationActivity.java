package com.business.electr.clothes.ui.activity.presentation;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.ui.fragment.presentation.AIPresentationFragment;
import com.business.electr.clothes.utils.IndicatorLinUtil;
import com.google.android.material.tabs.TabLayout;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.util.ArrayList;
import java.util.Arrays;
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
    TabLayout tlAiPresentation;
    @BindView(R.id.vp_ai_presentation)
    ViewPager vpAiPresentation;


    private List<String> listTitle = new ArrayList<>();
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
        String[] strings = {"全部报告", "4月", "3月", "2月", "1月"};
        for (int i = 0; i< strings.length;i++){
            fragments.add(new AIPresentationFragment());
            tlAiPresentation.addTab(tlAiPresentation.newTab());
        }

        listTitle = Arrays.asList(strings);
        tlAiPresentation.setupWithViewPager(vpAiPresentation);
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
        vpAiPresentation.setAdapter(fragmentPagerAdapter);
        for (int i = 0; i< strings.length;i++){

            tlAiPresentation.getTabAt(i).setText(strings[i]);
        }
//        tlAiPresentation.setDeclaredField("mTabStrip");
//        IndicatorLinUtil.setIndicator(tlAiPresentation, 40, 40);
    }

}
