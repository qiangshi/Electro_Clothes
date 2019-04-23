package com.business.electr.clothes.mvp.presenter.basePresenter;


import com.business.electr.clothes.mvp.view.IView;

/**
 * Created by pantianxiong on 2018/4/23.
 * 描述：
 */

public interface IPresenter<V extends IView> {
    void detachView();
}
