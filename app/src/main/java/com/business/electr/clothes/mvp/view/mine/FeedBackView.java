package com.business.electr.clothes.mvp.view.mine;

import com.business.electr.clothes.mvp.view.IView;

/**
 * Created by zenghaiqiang on 2019/1/25.
 */

public interface FeedBackView extends IView{
    /**
     * 反馈成功
     */
    void feedbackSuccess();

    void selectPhoto(int position);

    void onUploadSuccess(String imgUrl,int position);
}
