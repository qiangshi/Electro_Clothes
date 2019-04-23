package com.business.electr.clothes.bean;

import java.util.List;

public class PageModel<T> {

    private int count;
    private int total;
    private int page;
    private int limit;
    private List<T> list_data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getList_data() {
        return list_data;
    }

    public void setList_data(List<T> list_data) {
        this.list_data = list_data;
    }
}
