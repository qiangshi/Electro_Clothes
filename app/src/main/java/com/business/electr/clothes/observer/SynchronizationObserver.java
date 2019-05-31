package com.business.electr.clothes.observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zenghaiqiang on 2018/6/7.
 * 描述：观察者模式监听
 */
public class SynchronizationObserver {
    /******************************type*************************************/
    public static final int TYPE_LOGIN = 0;

    public static final int TYPE_UPDATE_USER_INFO = 1;//更新用户信息

    /******************************页面***************************************/
    /*baseActivity*/
    public static final int PAGE_BASEACTIVITY = 22;
    /*我的*/
    public static final int PAGE_FRAGMENT_TYPE_MINE = 0;
    /*心电衣界面*/
    public static final int PAGE_FRAGMENT_TYPE_ELECT = 1;


    /**
     * 保存监听的数组
     */
    private Map<Integer, OnSynchronizationListener> map = new ConcurrentHashMap<>();


    public static SynchronizationObserver getInstance() {
        return Holder.instance;
    }


    public void registerSynchronizationListener(OnSynchronizationListener listener, int sort) {
        map.put(sort, listener);
        return;
    }

    public void unRegisterSynchronizationListener(int sort) {
        if (map.containsKey(sort)) map.remove(sort);
    }


    public void onSynchronizationUpdate(int type, Object object, int... sort) {
        if (sort.length == 0) {
            for (OnSynchronizationListener listener : map.values()) listener.onSynchronizationUpdate(type, object);
        } else {
            for (int snc : sort) {
                if (map.containsKey(snc)) map.get(snc).onSynchronizationUpdate(type, object);
            }
        }
    }

    /**
     * 同步方法
     * sort 标签不传的时候所有界面都发送
     */
    public interface OnSynchronizationListener {
        /**
         * 同步更新
         * @param type  操作类型
         * @param object  变化的数据
         */
        void onSynchronizationUpdate(int type, Object object);
    }


    private static class Holder {
        private static final SynchronizationObserver instance = new SynchronizationObserver();
    }
}
