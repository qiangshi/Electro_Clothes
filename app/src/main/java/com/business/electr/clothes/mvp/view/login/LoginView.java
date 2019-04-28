package com.business.electr.clothes.mvp.view.login;

import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.mvp.view.IView;

/**
 * Created by zenghaiqiang on 2019/1/16.
 */

public interface LoginView extends IView {

  /**
   * 发送验证码成功
   */
  void sendSuccess();


  /**
   * 登陆成功
   * @param userBean
   */
  void loginSuccess(UserBean userBean);


}
