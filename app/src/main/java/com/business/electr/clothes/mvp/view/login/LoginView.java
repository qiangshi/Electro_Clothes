package com.business.electr.clothes.mvp.view.login;

import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.mvp.view.IView;

/**
 * Created by zenghaiqiang on 2019/1/16.
 */

public interface LoginView extends IView {

    /**
     * 验证码倒计时
     */
    void changeBtnStatus();


    void loginSuccess(UserBean userBean);

}
