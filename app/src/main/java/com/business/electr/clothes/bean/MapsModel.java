package com.business.electr.clothes.bean;

import java.util.List;

/**
 * @ClassName: MapsModel
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/4 15:06
 */
public class MapsModel<T> {

    private List<T> maps;

    public List<T> getMaps() {
        return maps;
    }

    public void setMaps(List<T> maps) {
        this.maps = maps;
    }
}
