/**
 * 
 */
package com.pxjg.util;

import java.util.List;

public class PageBean {

	// 总数
	private int total;

	// 当前页码,默认为1
	private int currentPage = 1;

	// 总页数
	private int totalPage;

	// 页面大小,默认为10
	private int pageSize = 10;

	// 开始,默认为0
	private int startCount = 0;

	// 当前页面的长度
	private int currentSize = 0;
	
	// 结束,默认为20
	private int endCount = 10;

	// 是否存在下一页
	private boolean hasNext;

	// 是否存在上一页
	private boolean hasPre;

	// ibatis的example查询实体
	private Object obj;

	// 查询出来的数据
	private List<?> datas;
	//param
	private String param;
	//title
	private String title;

	/**
	 * 首页
	 */
	public void first() {
		this.init(1);
	}

	/**
	 * 首页,带页面大小
	 * */
	public void first(int pageSize) {
		this.init(1, pageSize);
	}

	/**
	 * 下一页
	 * */
	public void next() {
		this.currentPage++;
		this.init(this.currentPage);
	}

	/**
	 * 上一页
	 * */
	public void pre() {
		this.currentPage--;
		this.init(this.currentPage);
	}

	/**
	 * 最后一页
	 * */
	public void last() {
		this.currentPage = this.totalPage;
		this.init(this.currentPage);
	}

	/***/

	/**
	 * 统一初始化所有参数
	 */
	public void init(int currentPage, int pageSize) {
		// 当传入的数不大于0时,把当前页面设为了1,页面大小设为10
		if (currentPage < 1) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
		if (pageSize < 1) {
			this.pageSize = 10;
		} else {
			this.pageSize = pageSize;
		}

		if(currentPage != 1){
			this.startCount = (this.currentPage - 1) * this.pageSize + 1;
		}else{
			this.startCount = (this.currentPage - 1) * this.pageSize;
		}
		this.endCount = startCount + pageSize;
		setHasNext(isHasNext());
		setHasPre(isHasPre());
	}

	/**
	 * 统一初始化所有参数
	 * 
	 * @param currentPage
	 */
	public void init(int currentPage) {
		// 当传入的数不大于0时,把当前页面设为了1,页面大小设为10
		if (currentPage < 1) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
		
		if(currentPage == 1){
			this.startCount = (this.currentPage - 1) * this.pageSize;
		}else{
			this.startCount = (this.currentPage - 1) * this.pageSize + 1;
		}
		this.endCount = startCount + pageSize;
		setHasNext(isHasNext());
		setHasPre(isHasPre());
	}

	public void setTotal(int total) {
		this.total = total;
		// 计算出总页数
		int a = this.total / this.pageSize; // 商
		int b = this.total % this.pageSize; // 余
		if (0 != b) {
			a++;
		}
		this.totalPage = a;
	}
	

	// 相应的getter&&setter
	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
		this.currentSize = datas.size();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

	public boolean isHasNext() {
		if (this.currentPage < this.totalPage) {
			return true;
		} else {
			return false;
		}
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	public boolean getHasNext(){
		return hasNext;
	}
	
	public boolean getHasPre(){
		return hasPre;
	}

	public boolean isHasPre() {
		if (this.currentPage > 1) {
			return true;
		} else {
			return false;
		}
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
}
