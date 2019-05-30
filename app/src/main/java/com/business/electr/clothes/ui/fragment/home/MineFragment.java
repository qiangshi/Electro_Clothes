package com.business.electr.clothes.ui.fragment.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.observer.SynchronizationObserver;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.business.electr.clothes.utils.GlidUtils;
import com.business.electr.clothes.utils.MLog;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 我的Fragment
 */
public class MineFragment extends BaseFragment {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.img_user_head)
    ImageView imgUserHead;

    @Override
    protected void initEventAndData() {
        SynchronizationObserver.getInstance().registerSynchronizationListener(syncListener, SynchronizationObserver.PAGE_FRAGMENT_TYPE_MINE);
        tvName.setText(DataCacheManager.getUserInfo().getUserName());
        GlidUtils.setCircleGrid(getActivity(), DataCacheManager.getUserInfo().getHeadImgUrl(),imgUserHead);
    }


    private SynchronizationObserver.OnSynchronizationListener syncListener = new SynchronizationObserver.OnSynchronizationListener() {
        @Override
        public void onSynchronizationUpdate(int type, Object object) {
            UserBean bean = (UserBean) object;
            if (bean != null) {
                tvName.setText(bean.getUserName());
                GlidUtils.setCircleGrid(getActivity(),bean.getHeadImgUrl(),imgUserHead);
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


    @OnClick({R.id.ll_user_info, R.id.ll_ihi, R.id.ll_task, R.id.ll_ihi_exper, R.id.lin_ai, R.id.lin_my_doctor, R.id.lin_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_user_info:
                new DefaultUriRequest(Objects.requireNonNull(getActivity()), RouterCons.MODIFY_USER_INFO)
                        .start();
                break;
            case R.id.ll_ihi:
                new DefaultUriRequest(Objects.requireNonNull(getActivity()),RouterCons.CREATE_MY_IHI)
                        .start();
                break;
            case R.id.ll_task:
                new DefaultUriRequest(Objects.requireNonNull(getActivity()),RouterCons.CREATE_TASK_TARGET)
                        .start();
                break;
            case R.id.ll_ihi_exper:
                toastMessage(R.string.development_ing);
                break;
            case R.id.lin_ai:
                toastMessage(R.string.development_ing);
                break;
            case R.id.lin_my_doctor:
                toastMessage(R.string.development_ing);
//                new DefaultUriRequest(Objects.requireNonNull(getActivity()), RouterCons.CREATE_SHARE_TASK)
//                        .start();
                break;
            case R.id.lin_help:
                new DefaultUriRequest(Objects.requireNonNull(getActivity()), RouterCons.CREATE_HELPER)
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
