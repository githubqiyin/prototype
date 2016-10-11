package com.github.frame.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private static Logger logger = Logger.getLogger(BaseServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<T> doSearch(T t) {

		logger.info("查询列表操作：" + getInfo(t));

		if (!(t != null && t instanceof Pagination)) {
			return new Pagination<T>();
		}

		Pagination<T> p = (Pagination<T>) t;

		// 查询总数
		int recordCount = getBaseDAO().selectForCount(t);

		// 查询列表
		List<T> recordList = new ArrayList<T>();
		if (recordCount != 0) {
			recordList = getBaseDAO().selectForList(
					t,
					new RowBounds(p.getPageSize(), (p.getPageNo() - 1)
							* p.getPageSize()));
		}

		// 组装分页
		int currentPage = ((Pagination<T>) t).getPageNo();
		int totalPage = (recordCount + p.getPageSize() - 1) / p.getPageSize();
		int pageSize = p.getPageSize();

		return new Pagination<T>(currentPage, totalPage, pageSize, recordCount,
				recordList);
	}

	@Override
	public int doCount(T t) {

		logger.info("查询数量操作：" + getInfo(t));

		// 查询总数
		return getBaseDAO().selectForCount(t);
	}

	@Override
	public int doAdd(T t) {
		logger.info("新增操作：" + getInfo(t));
		return getBaseDAO().insertByVO(t);
	}

	@Override
	public int doBatchInsert(List<T> ts) {
		logger.info("批量新增操作：" + getInfo(ts));
		return getBaseDAO().batchInsertByList(ts);
	}

	@Override
	public int doDelete(T t) {
		logger.info("删除操作：" + getInfo(t));
		return getBaseDAO().deleteByVO(t);
	}

	@Override
	public int doUpdate(T t) {
		logger.info("更新操作：" + getInfo(t));
		return getBaseDAO().updateByVO(t);
	}

	@Override
	public int doBatchUpdate(List<T> ts) {
		logger.info("批量更新操作：" + getInfo(ts));
		return getBaseDAO().batchUpdateByList(ts);
	}

	@Override
	public T doFind(T t) {
		logger.info("查看操作：" + getInfo(t));
		return getBaseDAO().selectByVO(t);
	}

	@Override
	public List<T> doFindAll() {
		return doFindAll(null);
	}

	@Override
	public List<T> doFindAll(T t) {
		logger.info("查询操作：" + getInfo(t));
		return getBaseDAO().selectForList(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> doFindLimit(T t) {
		if (!(t != null && t instanceof Pagination)) {
			return new ArrayList<T>();
		}
		Pagination<T> p = (Pagination<T>) t;
		return getBaseDAO().selectForList(
				t,
				new RowBounds(p.getPageSize(), (p.getPageNo() - 1)
						* p.getPageSize()));
	}

	@Override
	public List<T> doFindLimit() {
		Pagination<T> t = new Pagination<T>();
		return getBaseDAO().selectForList(
				null,
				new RowBounds(t.getPageSize(), (t.getPageNo() - 1)
						* t.getPageSize()));
	}

	public String getInfo(Object t) {
		return t == null ? StringUtils.EMPTY : t.toString();
	}

	public abstract BaseDAO<T> getBaseDAO();
}