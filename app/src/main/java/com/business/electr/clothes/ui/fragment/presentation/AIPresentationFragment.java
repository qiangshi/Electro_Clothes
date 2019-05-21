package com.business.electr.clothes.ui.fragment.presentation;


import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.AiPresentationBean;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.ui.adapter.AiPresentationAdapter;
import com.business.electr.clothes.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by zenghaiqiang on 2019/5/19.
 * Ai报告Fragment
 */
public class AIPresentationFragment extends BaseFragment {


    @BindView(R.id.rv_ai_presentation)
    RecyclerView rvAiPresentation;

    List<AiPresentationBean> list = new ArrayList<>();

    public AIPresentationFragment() {
    }


    @Override
    protected void initEventAndData() {
        initList();
        rvAiPresentation.setLayoutManager(new LinearLayoutManager(getActivity()));
        AiPresentationAdapter adapter = new AiPresentationAdapter(getActivity(),list);
        rvAiPresentation.setAdapter(adapter);
    }

    private void initList() {
        list.add(new AiPresentationBean("05月07号报告","记录时常6小时"));
        list.add(new AiPresentationBean("05月05号报告","记录时常5小时"));
        list.add(new AiPresentationBean("05月04号报告","记录时常8小时"));
        list.add(new AiPresentationBean("05月02号报告","记录时常9小时"));
        list.add(new AiPresentationBean("05月01号报告","记录时常12小时"));
        list.add(new AiPresentationBean("04月28号报告","记录时常7小时"));
        list.add(new AiPresentationBean("04月25号报告","记录时常8小时"));
        list.add(new AiPresentationBean("04月24号报告","记录时常9小时"));
        list.add(new AiPresentationBean("04月23号报告","记录时6小时"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_aipresentation;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

}
