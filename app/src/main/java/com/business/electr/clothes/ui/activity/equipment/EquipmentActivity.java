package com.business.electr.clothes.ui.activity.equipment;


import android.widget.ImageView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.equipment.Equipment1Fragment;
import com.business.electr.clothes.ui.fragment.equipment.Equipment2Fragment;
import com.business.electr.clothes.ui.fragment.equipment.Equipment3Fragment;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.sankuai.waimai.router.annotation.RouterUri;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 描述：设备连接管理
 */
@RouterUri(path = {RouterCons.CREATE_EQUIPMENT})
public class EquipmentActivity extends BaseActivity {


    @BindView(R.id.img_pot1)
    ImageView imgPot1;
    @BindView(R.id.img_pot2)
    ImageView imgPot2;
    @BindView(R.id.img_pot3)
    ImageView imgPot3;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private List<Fragment> fragments;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_equipment;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        StatusBarUtil.setStatusBarColor(this,0xff3F3F40);
        StatusBarUtil.setStatusBarDarkTheme(this, false);
        fragments = new ArrayList<>();
        Equipment1Fragment equipment1Fragment = new Equipment1Fragment();
        Equipment2Fragment equipment2Fragment = new Equipment2Fragment();
        Equipment3Fragment equipment3Fragment = new Equipment3Fragment();
        fragments.add(equipment1Fragment);
        fragments.add(equipment2Fragment);
        fragments.add(equipment3Fragment);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changePot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void changePot(int pos){
        switch (pos){
            case 0:
                imgPot1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circular_d8d8d8_pot));
                imgPot2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circular_51d8d8d8_pot));
                imgPot3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circular_51d8d8d8_pot));
                break;
            case 1:
                imgPot1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circular_51d8d8d8_pot));
                imgPot2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circular_d8d8d8_pot));
                imgPot3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circular_51d8d8d8_pot));
                break;
            case 2:
                imgPot1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circular_51d8d8d8_pot));
                imgPot2.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circular_51d8d8d8_pot));
                imgPot3.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.circular_d8d8d8_pot));
                break;
        }
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
