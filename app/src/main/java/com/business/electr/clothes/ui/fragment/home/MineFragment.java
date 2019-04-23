package com.business.electr.clothes.ui.fragment.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.LoginBean;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.observer.SynchronizationObserver;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.utils.GlidUtils;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/1/15.
 * 我的Fragment
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.img_user_head)
    ImageView imgUserHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    private LoginBean.UserBean userBean;

    @Override
    protected void initEventAndData() {
        SynchronizationObserver.getInstance().registerSynchronizationListener(syncListener, SynchronizationObserver.PAGE_FRAGMENT_TYPE_MINE);
        userBean = DataCacheManager.getUserInfo();
        if (userBean != null) {
            GlidUtils.setCircleGrid(getActivity(), userBean.getPortrait(), imgUserHead);
            tvUserName.setText(userBean.getNickname());
        }
    }


    SynchronizationObserver.OnSynchronizationListener syncListener = new SynchronizationObserver.OnSynchronizationListener() {
        @Override
        public void onSynchronizationUpdate(int type, Object object) {
            LoginBean.UserBean bean = (LoginBean.UserBean) object;
            if (bean != null) {
                tvUserName.setText(bean.getNickname());
                GlidUtils.setCircleGrid(getActivity(), bean.getPortrait(), imgUserHead);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @OnClick({R.id.rel_user_info, R.id.lin_time_bean, R.id.lin_my_company, R.id.lin_feed_back, R.id.lin_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_user_info://修改用户信息
                new DefaultUriRequest(getActivity(), RouterCons.MODIFY_USER_INFO)
                        .start();
                break;
            case R.id.lin_time_bean://时间豆界面

                break;
            case R.id.lin_my_company://企业管理
                break;
            case R.id.lin_feed_back://问题与反馈
                new DefaultUriRequest(getActivity(), RouterCons.CREATE_QUESTION_FEEDBACK)
                        .start();
                break;
            case R.id.lin_set://设置
                new DefaultUriRequest(getActivity(), RouterCons.CREATE_SETTING)
                        .start();
                break;
        }
    }

    @Override
    public void onDestroy() {
        SynchronizationObserver.getInstance().unRegisterSynchronizationListener(SynchronizationObserver.PAGE_FRAGMENT_TYPE_MINE);
        super.onDestroy();
    }
}
