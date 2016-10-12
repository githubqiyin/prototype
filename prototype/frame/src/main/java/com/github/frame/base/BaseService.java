package com.github.frame.base;

import java.util.List;

public interface BaseService<T> {

    public void doPage(T t, Pagination<T> p);

    public T doFind(T t);

    public List<T> doSearch();

    public List<T> doSearch(T t);

    public int doSave(T t);

    public int doSave(List<T> ts);

    public int doUpdate(T t);

    public int doUpdate(List<T> ts);

    public int doDelete(T t);

}