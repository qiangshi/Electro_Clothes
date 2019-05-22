package com.business.electr.clothes.ui.fragment.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.observer.SynchronizationObserver;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.fragment.BaseFragment;
import com.sankuai.waimai.router.common.DefaultUriRequest;

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
    }


    SynchronizationObserver.OnSynchronizationListener syncListener = new SynchronizationObserver.OnSynchronizationListener() {
        @Override
        public void onSynchronizationUpdate(int type, Object object) {
            UserBean bean = (UserBean) object;
            if (bean != null) {
                tvName.setText(bean.getUserName());
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
                new DefaultUriRequest(getActivity(), RouterCons.MODIFY_USER_INFO)
                        .start();
                break;
            case R.id.ll_ihi:
                new DefaultUriRequest(getActivity(),RouterCons.CREATE_MY_IHI)
                        .start();
                break;
            case R.id.ll_task:
                break;
            case R.id.ll_ihi_exper:
                toastMessage(R.string.development_ing);
                break;
            case R.id.lin_ai:
                toastMessage(R.string.development_ing);
//                new DefaultUriRequest(getActivity(), RouterCons.CREATE_PERFECT_INFO)
//                        .start();
                break;
            case R.id.lin_my_doctor:
                toastMessage(R.string.development_ing);
//                startActivity(new Intent(getActivity(), TestActivity.class));
//                new DefaultUriRequest(getActivity(), RouterCons.CREATE_QUESTION_FEEDBACK)
//                        .start();
                break;
            case R.id.lin_help:
                new DefaultUriRequest(getActivity(), RouterCons.CREATE_HELPER)
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
