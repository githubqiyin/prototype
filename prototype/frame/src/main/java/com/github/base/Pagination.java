package com.github.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pagination<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pageNo = 1;

    private int pageSize = 10;

    private int pageCount = 1;

    private int itemCount = 0;

    private List<T> itemList = new ArrayList<T>();

    public Pagination() {
    }

    public Pagination(int pageNo, int pageSize, int pageCount, int itemCount, List<T> itemList) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.itemCount = itemCount;
        this.itemList = itemList;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

}