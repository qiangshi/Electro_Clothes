package com.business.electr.clothes.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.mvp.view.IView;
import com.business.electr.clothes.ui.fragment.dialog.EmptyFragment;
import com.business.electr.clothes.ui.fragment.dialog.LoadingFragment;

import java.lang.reflect.Field;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

/**
 * 基础fragment
 */
public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView {
    /**
     * contentView
     */
    private View mRootView;
    /**
     * 代理类
     */
    protected P mPresenter;
    /**
     * loading fragment
     */
    public LoadingFragment fragment;
    protected EmptyFragment mEmptyFragment;
    private boolean isPrepared = false;
    private boolean isFragmentVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = getPresenter();
        }
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
            ButterKnife.bind(this, mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.isPrepared = true;
        initEventAndData();
    }

    protected abstract void initEventAndData();

    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    /**
     * fragment显示是否改变  true显示  false 隐藏状态
     *
     * @param isVisible
     */
    protected abstract void onFragmentVisibleChange(boolean isVisible);


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            this.isFragmentVisible = true;
        }
        if (this.mRootView != null) {
            if (!this.isPrepared && this.isFragmentVisible) {
                this.onFragmentVisibleChange(true);
            } else {
                if (this.isFragmentVisible) {
                    this.onFragmentVisibleChange(false);
                    this.isFragmentVisible = false;
                }
            }
        }
    }


    protected void tryLoadData() {
        if (isPrepared && isFragmentVisible) {

        }
    }

    /**
     * 显示 loading
     */
    public void showLoadFragment() {
        if (fragment != null) {
            fragment.dismissAllowingStateLoss();
            fragment = null;
        }
        fragment = new LoadingFragment();
        if (isAdded())
            getChildFragmentManager().beginTransaction().add(fragment, "").commitAllowingStateLoss();
    }

    /**
     * 隐藏loading
     */
    public void hideLoadFragment() {
        try {
            if (isAdded() && fragment != null) {
                fragment.dismissAllowingStateLoss();
                fragment = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 显示空页面
     * @param contentId 要依附在哪个的id上
     */
    public void showEmpty(int contentId) {
        if (mEmptyFragment == null) {
            mEmptyFragment = new EmptyFragment();
            getChildFragmentManager().beginTransaction().add(contentId, mEmptyFragment).commitAllowingStateLoss();
        } else getChildFragmentManager().beginTransaction().show(mEmptyFragment).commitAllowingStateLoss();
    }

    /**
     * 关闭空页面
     */
    public void hideEmpty() {
        if (mEmptyFragment != null && mEmptyFragment.isAdded()) getChildFragmentManager().beginTransaction().hide(mEmptyFragment).commitAllowingStateLoss();
    }


    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void showLoading() {
        showLoadFragment();
    }

    @Override
    public void hideLoading() {
        hideLoadFragment();
    }

    @Override
    public void toastMessage(int stringId) {
        Toast.makeText(getActivity(), stringId, Toast.LENGTH_SHORT).show();
    }
}
