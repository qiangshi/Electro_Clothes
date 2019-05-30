package com.business.electr.clothes.mvp.view.mine;

import com.business.electr.clothes.bean.VersionBean;
import com.business.electr.clothes.mvp.view.IView;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/30 11:32
 */
public interface HelperView extends IView {

    void getVersionSuccess(VersionBean bean);
}
