package com.github.frame.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.RowBounds;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Override
    public void doPage(T t, Pagination<T> p) {

        int itemCount = getBaseDAO().selectCount(t);

        List<T> itemList = itemCount == 0 ? new ArrayList<T>() : getBaseDAO().selectList(t, new RowBounds(p.getPageSize(), (p.getPageNo() - 1) * p.getPageSize()));

        p.setItemCount(itemCount);
        p.setItemList(itemList);
        p.setPageCount((itemCount + p.getPageSize() - 1) / p.getPageSize());
    }

    @Override
    public T doFind(T t) {
        return getBaseDAO().select(t);
    }

    @Override
    public List<T> doSearch() {
        return doSearch(null);
    }

    @Override
    public List<T> doSearch(T t) {
        return getBaseDAO().selectList(t);
    }

    @Override
    public int doSave(T t) {
        return getBaseDAO().insert(t);
    }

    @Override
    public int doSave(List<T> ts) {
        if (CollectionUtils.isEmpty(ts)) {
            return 0;
        }
        return getBaseDAO().batchInsert(ts);
    }

    @Override
    public int doUpdate(T t) {
        return getBaseDAO().update(t);
    }

    @Override
    public int doUpdate(List<T> ts) {
        if (CollectionUtils.isEmpty(ts)) {
            return 0;
        }
        return getBaseDAO().batchUpdate(ts);
    }

    public int doDelete(T t) {
        return getBaseDAO().delete(t);
    }

    public abstract BaseDAO<T> getBaseDAO();

}