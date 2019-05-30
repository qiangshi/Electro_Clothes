package com.business.electr.clothes.mvp.view;

import com.business.electr.clothes.bean.TaskBean;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/30 11:20
 */
public interface TaskTargetView extends IView {

    /**
     * 获取任务信息成功
     * @param bean
     */
    void getTaskInfoSuccess(TaskBean bean);
}
