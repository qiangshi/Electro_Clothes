package com.business.electr.clothes.ui.fragment.history;


import android.text.TextUtils;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.HistoryBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.mvp.view.OnItemClickListener;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.adapter.HistoryStateAdapter;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.ui.fragment.dialog.CustomStateFragment;
import com.business.electr.clothes.ui.fragment.dialog.HistoryStateFragment;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.utils.ToastUtils;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/5/5.
 * 历史Fragment 第一种显示方式 图片
 */
public class HistoryOneFragment extends BaseFragment implements OnItemClickListener{


    @BindView(R.id.rv_tag)
    RecyclerView rvTag;
    private int curPos = -1;
    private HistoryStateAdapter adapter;
    private List<HistoryBean> list = new ArrayList<>();
    List<String> strings ;

    public HistoryOneFragment() {
    }


    @Override
    protected void initEventAndData() {
        strings = Arrays.asList(getResources().getStringArray(R.array.select_state_list));
        list.add(new HistoryBean("06:15-07:15","1小时","睡觉",true));
        list.add(new HistoryBean("18:45-20:15","1.5小时","",false));
        list.add(new HistoryBean("18:45-20:15","1.5小时","",false));
        rvTag.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HistoryStateAdapter(getActivity(),list);
        rvTag.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history_one;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @OnClick(R.id.tv_select)
    public void onViewClicked() {
        new DefaultUriRequest(Objects.requireNonNull(getActivity()), RouterCons.CREATE_ELECT_DETAIL)
                .putExtra(Constant.TITLE_TIME,"昨天")
                .start();
    }

    private HistoryStateFragment historyStateFragment;
    @Override
    public void OnClickItemListener(Object obj, int position) {
        historyStateFragment = new HistoryStateFragment();
        showFragment(getChildFragmentManager(), curPos, new HistoryStateFragment.TypeChangeListener() {
            @Override
            public void onTypeChange(int pos) {
                curPos = pos;
                changeList(position,"");
                if(pos == 6){
                    CustomStateFragment.showFragment(getChildFragmentManager(), new CustomStateFragment.CustomTextListener() {
                        @Override
                        public void onCustomText(String customText) {
                            historyStateFragment.setCustomText(customText);
                            changeList(position,customText);
                        }
                    });
                }
            }
        });
    }

    private void changeList(int position,String customText) {//刷新数据
        list.get(position).setSelect(true);
        if(TextUtils.isEmpty(customText)){
            list.get(position).setState(strings.get(curPos));
        }else {
            list.get(position).setState(customText);
        }
        adapter.setData(list);
    }


    private void showFragment(FragmentManager manager, int position, HistoryStateFragment.TypeChangeListener typeChangeListener) {
        historyStateFragment = new HistoryStateFragment();
        historyStateFragment.setCurPos(position);
        historyStateFragment.setListener(typeChangeListener);
        historyStateFragment.show(manager, "historyStateFragment");
    }


//    private void hideFragment() {
//        try {
//            if (historyStateFragment.isAdded()) historyStateFragment.dismissAllowingStateLoss();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
