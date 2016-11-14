package com.github.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private static Logger logger = Logger.getLogger(BaseServiceImpl.class);

    @Override
    public void doPage(T t, Pagination<T> p) {

        logger.info("查询分页");

        int itemCount = getBaseDAO().selectCount(t);

        List<T> itemList = itemCount == 0 ? new ArrayList<T>() : getBaseDAO().selectList(t, new RowBounds(p.getPageSize(), (p.getPageNo() - 1) * p.getPageSize()));

        p.setItemCount(itemCount);
        p.setItemList(itemList);
        p.setPageCount((itemCount + p.getPageSize() - 1) / p.getPageSize());
    }

    @Override
    public T doFind(T t) {

        logger.info("查询对象");

        return getBaseDAO().select(t);
    }

    @Override
    public List<T> doSearch() {
        logger.info("查询列表");
        return doSearch(null);
    }

    @Override
    public List<T> doSearch(T t) {
        logger.info("查询列表");
        return getBaseDAO().selectList(t);
    }

    @Override
    public int doSave(T t) {
        logger.info("保存对象");
        return getBaseDAO().insert(t);
    }

    @Override
    public int doSave(List<T> ts) {
        logger.info("保存列表");
        if (CollectionUtils.isEmpty(ts)) {
            return 0;
        }
        return getBaseDAO().batchInsert(ts);
    }

    @Override
    public int doUpdate(T t) {
        logger.info("更新对象");
        return getBaseDAO().update(t);
    }

    @Override
    public int doUpdate(List<T> ts) {
        logger.info("更新列表");
        if (CollectionUtils.isEmpty(ts)) {
            return 0;
        }
        return getBaseDAO().batchUpdate(ts);
    }

    public int doDelete(T t) {
        logger.info("删除对象");
        return getBaseDAO().delete(t);
    }

    public abstract BaseDAO<T> getBaseDAO();

}