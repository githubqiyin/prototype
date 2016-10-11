package com.github.frame.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 */
public class Pagination<T> extends BaseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageNo = 1;

	private int totalPage = 1;

	private int pageSize = 10;

	private int recordCount = 0;

	private List<T> recordList = new ArrayList<T>();

	public Pagination() {
	}

	public Pagination(int pageNo, int totalPage, int pageSize, int recordCount,
			List<T> recordList) {
		this.pageNo = pageNo;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

}