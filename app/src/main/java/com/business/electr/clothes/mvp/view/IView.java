package com.business.electr.clothes.mvp.view;

/**
 * Created by pantianxiong on 2018/4/23.
 * 描述：
 */

public interface IView {
    /**
     * 显示Loading
     */
    void showLoading();

    /**
     * 隐藏Loading
     */
    void hideLoading();

    /**
     * 吐司
     *
     * @param stringId
     */
    void toastMessage(int stringId);
}
