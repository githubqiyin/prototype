package com.github.frame.base;

import java.util.List;

public interface BaseService<T> {

	public List<T> doFindAll();

	public List<T> doFindAll(T t);

	public List<T> doFindLimit(T t);

	public List<T> doFindLimit();

	public Pagination<T> doSearch(T t);

	public int doCount(T t);

	public int doAdd(T t);

	public int doDelete(T t);

	public int doUpdate(T t);

	public T doFind(T t);

	public int doBatchInsert(List<T> ts);

	public int doBatchUpdate(List<T> ts);

}