package com.business.electr.clothes.net.paramjson;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.business.electr.clothes.manager.DataCacheManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Description: 动态添加post参数
 * @Author: 曾海强
 * @CreateDate: 2019/4/25 18:12
 */
public class ParamsBuilder {

    static {
        fastJson = new JSONObject();
    }

    private static JSONObject fastJson;
    private Map<String, Object> map;

    public ParamsBuilder() {
        this.map = new HashMap<>();
    }

    /**
     * 添加参数
     */
    public ParamsBuilder addParams(String key, Object value) {
        if (TextUtils.isEmpty(key)) {
            throw new NullPointerException("key 不能为空");
        }
        map.put(key, value);
        return this;
    }

    /**
     * 添加全局参数
     */
    public ParamsBuilder addCommonMap() {
        //添加统一参数
        addParams("token", DataCacheManager.getToken());
        return this;
    }

    /**
     * 返回map集合
     */
    public Map bulid() {
        return map;
    }

    /**
     * 返回json字符串
     */
    public String toJson() {
        return fastJson.toJSONString(map);
    }

    /**
     * 返回RequestBody
     */
    public RequestBody toRequestBody() {
        return RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), toJson());
    }
}

