package com.money.db;

public class Page {

	// 排序字段
	public String sortProperty;
	// 当前页码
	public int currentPage;
	// 分页大小
	public int pageSize;

	public String getLimit() {
		return (pageSize * (currentPage - 1)) + "," + pageSize;
	}
}
