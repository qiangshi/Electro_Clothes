package com.business.electr.clothes.ui.fragment.history;


import com.business.electr.clothes.R;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.mvp.view.OnItemClickListener;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.adapter.HistoryStateAdapter;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.utils.ToastUtils;
import com.sankuai.waimai.router.common.DefaultUriRequest;

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

    public HistoryOneFragment() {
    }


    @Override
    protected void initEventAndData() {
        rvTag.setLayoutManager(new LinearLayoutManager(getActivity()));
        HistoryStateAdapter adapter = new HistoryStateAdapter(getActivity());
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
        new DefaultUriRequest(getActivity(), RouterCons.CREATE_ELECT_DETAIL)
                .putExtra(Constant.TITLE_TIME,"昨天")
                .start();
    }

    @Override
    public void OnClickItemListener(Object obj, int position) {
        ToastUtils.showToast(getActivity(),"显示状态列表");
    }
}
