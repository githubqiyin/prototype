package com.github.frame.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.RowBounds;

import com.github.frame.base.BaseDAO;
import com.github.frame.base.BaseService;
import com.github.frame.base.Pagination;
import com.github.frame.util.FrameConstant;
import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;

public abstract class CacheServiceImpl<T> extends BaseServiceImpl<T> implements BaseService<T> {

    @Override
    public void doPage(T t, Pagination<T> p) {

        int itemCount = getBaseDAO().selectCount(t);

        List<T> itemList = itemCount == 0 ? new ArrayList<T>() : getBaseDAO().selectList(t, new RowBounds(p.getPageSize(), (p.getPageNo() - 1) * p.getPageSize()));

        p.setItemCount(itemCount);
        p.setItemList(itemList);
        p.setPageCount((itemCount + p.getPageSize() - 1) / p.getPageSize());
    }

    @Override
    @ReadThroughSingleCache(namespace = FrameConstant.CACHE_NAMESPACE, expiration = FrameConstant.CACHE_CYCLE.LONG)
    public T doFind(@ParameterValueKeyProvider T t) {
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
    @InvalidateSingleCache(namespace = FrameConstant.CACHE_NAMESPACE)
    public int doUpdate(@ParameterValueKeyProvider T t) {
        return getBaseDAO().update(t);
    }

    @Override
    public int doUpdate(List<T> ts) {
        if (CollectionUtils.isEmpty(ts)) {
            return 0;
        }
        return getBaseDAO().batchUpdate(ts);
    }

    @Override
    @InvalidateSingleCache(namespace = FrameConstant.CACHE_NAMESPACE)
    public int doDelete(@ParameterValueKeyProvider T t) {
        return getBaseDAO().delete(t);
    }

    public abstract BaseDAO<T> getBaseDAO();

}