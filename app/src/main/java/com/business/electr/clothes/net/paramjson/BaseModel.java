package com.business.electr.clothes.net.paramjson;

import com.business.electr.clothes.net.ApiClient;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/4/25 18:16
 */
public class BaseModel {
    protected ApiClient service;

    public BaseModel() {
        service = ApiClient.getInstance();
    }
    /**
     * 链式添加参数
     */
    protected ParamsBuilder getBuilder(){
        return new ParamsBuilder();
    }
}
