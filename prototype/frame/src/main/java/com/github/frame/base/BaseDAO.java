package com.github.frame.base;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseDAO<T> extends Serializable {

	public int selectForCount(T t);

	public List<T> selectForList(T t, RowBounds rowBounds);

	public List<T> selectForList(T t);

	public T selectByVO(T t);

	public int insertByVO(T t);

	public int updateByVO(T t);

	public int deleteByVO(T t);

	public int batchUpdateByList(List<T> ts);

	public int batchInsertByList(List<T> ts);

	public void call(T t);

}