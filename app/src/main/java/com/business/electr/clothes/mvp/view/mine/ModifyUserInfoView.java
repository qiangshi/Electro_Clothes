package com.business.electr.clothes.mvp.view.mine;

import com.business.electr.clothes.bean.LoginBean;
import com.business.electr.clothes.mvp.view.IView;

/**
 * Created by zenghaiqiang on 2019/1/24.
 */

public interface ModifyUserInfoView extends IView {
    /**
     * 获取用户信息成功
     * @param bean
     */
    void getUserInfoSuccess(LoginBean.UserBean bean);

    void updateUserInfoSuccess();


    void selectPhoto();

    void onUploadSuccess(String imgUrl);
}
