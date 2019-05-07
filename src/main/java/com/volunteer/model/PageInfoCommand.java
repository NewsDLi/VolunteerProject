package com.volunteer.model;

import java.util.List;

/**
 * 分页结果
 * @author NewsDLee
 *
 */
public class PageInfoCommand<T> {

	/**
	 * 总页数
	 */
	private Integer pageCount;
	
	/**
	 * 获取当前页的数据信息
	 */
	private List<T> infos;

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getInfos() {
		return infos;
	}

	public void setInfos(List<T> infos) {
		this.infos = infos;
	}

}
