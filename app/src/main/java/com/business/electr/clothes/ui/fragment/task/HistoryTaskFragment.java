package com.business.electr.clothes.ui.fragment.task;

import android.os.Bundle;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.HistoryTaskBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.ui.adapter.HistoryTaskAdapter;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by zenghaiqiang on 2019/5/19.
 * 历史任务Fragment
 */
public class HistoryTaskFragment extends BaseFragment {


    @BindView(R.id.rv_history_task)
    RecyclerView rvHistoryTask;
    private List<HistoryTaskBean> list = new ArrayList<>();
    private int type;//0：全部  1 ： 已完成  2 ：未完成

    public HistoryTaskFragment() {
    }

    public static HistoryTaskFragment getInstance(int type) {
        HistoryTaskFragment fragment = new HistoryTaskFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
        type = getArguments().getInt(Constant.TYPE);
        initData();
        rvHistoryTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        HistoryTaskAdapter adapter = new HistoryTaskAdapter(getActivity(),list);
        rvHistoryTask.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        switch (type){
            case 0:
                list.add(new HistoryTaskBean("05月11日",1,"8/8 小时", "20000/20000 跳", "8000/8000 步"));
                list.add(new HistoryTaskBean("05月12日",0,"5/8 小时", "10000/20000 跳", "5000/8000 步"));
                list.add(new HistoryTaskBean("05月13日",1,"8/8 小时", "20000/20000 跳", "8000/8000 步"));
                list.add(new HistoryTaskBean("05月14日",1,"8/8 小时", "20000/20000 跳", "8000/8000 步"));
                list.add(new HistoryTaskBean("05月15日",0,"0/8 小时", "0/20000 跳", "0/8000 步"));
                list.add(new HistoryTaskBean("05月16日",0,"2/8 小时", "2000/20000 跳", "1500/8000 步"));
                list.add(new HistoryTaskBean("05月17日",1,"8/8 小时", "20000/20000 跳", "8000/8000 步"));
                break;
            case 1:
                list.add(new HistoryTaskBean("05月11日",1,"8/8 小时", "20000/20000 跳", "8000/8000 步"));
                list.add(new HistoryTaskBean("05月13日",1,"8/8 小时", "20000/20000 跳", "8000/8000 步"));
                list.add(new HistoryTaskBean("05月14日",1,"8/8 小时", "20000/20000 跳", "8000/8000 步"));
                list.add(new HistoryTaskBean("05月17日",1,"8/8 小时", "20000/20000 跳", "8000/8000 步"));
                break;
            case 2:
                list.add(new HistoryTaskBean("05月12日",0,"5/8 小时", "10000/20000 跳", "5000/8000 步"));
                list.add(new HistoryTaskBean("05月15日",0,"0/8 小时", "0/20000 跳", "0/8000 步"));
                list.add(new HistoryTaskBean("05月16日",0,"2/8 小时", "2000/20000 跳", "1500/8000 步"));
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history_task;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

}
