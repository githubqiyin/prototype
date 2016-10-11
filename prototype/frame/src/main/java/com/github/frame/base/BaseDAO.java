package com.github.frame.base;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseDAO<T> extends Serializable {

    public int selectCount(T t);

    public List<T> selectPage(T t, RowBounds rowBounds);

    public List<T> selectList(T t);

    public T select(T t);

    public int insert(T t);

    public int update(T t);

    public int delete(T t);

    public int batchInsert(List<T> ts);

    public int batchUpdate(List<T> ts);

    public void call(T t);

}