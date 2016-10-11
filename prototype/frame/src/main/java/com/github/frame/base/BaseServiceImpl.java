package com.github.frame.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Override
    public Pagination<T> doPage(T t, Pagination<T> p) {

        int itemCount = getBaseDAO().selectCount(t);

        List<T> itemList = itemCount == 0 ? new ArrayList<T>() : getBaseDAO().selectPage(t, new RowBounds(p.getPageSize(), (p.getPageNo() - 1) * p.getPageSize()));

        p.setItemCount(itemCount);
        p.setItemList(itemList);
        p.setPageCount((itemCount + p.getPageSize() - 1) / p.getPageSize());

        return p;
    }

    @Override
    public List<T> doSearch() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int doSave(T t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int doUpdate(List<T> ts) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int doAdd(T t) {
        logger.info("新增操作：" + getInfo(t));
        return getBaseDAO().insertByVO(t);
    }

    public int doSave(List<T> ts) {
        logger.info("批量新增操作：" + getInfo(ts));
        return getBaseDAO().batchInsertByList(ts);
    }

    public int doDelete(T t) {
        logger.info("删除操作：" + getInfo(t));
        return getBaseDAO().deleteByVO(t);
    }

    public int doUpdate(T t) {
        logger.info("更新操作：" + getInfo(t));
        return getBaseDAO().updateByVO(t);
    }

    public int doBatchUpdate(List<T> ts) {
        logger.info("批量更新操作：" + getInfo(ts));
        return getBaseDAO().batchUpdateByList(ts);
    }

    public T doFind(T t) {
        logger.info("查看操作：" + getInfo(t));
        return getBaseDAO().selectByVO(t);
    }

    public List<T> doFindAll() {
        return doFindAll(null);
    }

    public List<T> doFindAll(T t) {
        logger.info("查询操作：" + getInfo(t));
        return getBaseDAO().selectForList(t);
    }

    public List<T> doFindLimit(T t) {
        if (!(t != null && t instanceof Pagination)) {
            return new ArrayList<T>();
        }
        Pagination<T> p = (Pagination<T>) t;
        return getBaseDAO().selectForList(t, new RowBounds(p.getPageSize(), (p.getPageNo() - 1) * p.getPageSize()));
    }

    public List<T> doFindLimit() {
        Pagination<T> t = new Pagination<T>();
        return getBaseDAO().selectForList(null, new RowBounds(t.getPageSize(), (t.getPageNo() - 1) * t.getPageSize()));
    }

    public String getInfo(Object t) {
        return t == null ? StringUtils.EMPTY : t.toString();
    }

    public abstract BaseDAO<T> getBaseDAO();

}