package indi.xk.report.utils;

import java.util.List;

/**
 * 分页封装函数
 * @author xk
 */
public class PageView<T> {
	/**
	 * 分页数据
	 */
	private List<T> records;

	/**
	 * 页码的开始索引类 这个类包含startindex开始索引 endindex结束索引 这个数是计算出来的
	 */
	private PageIndex pageindex;

	/**
	 * 总页数 这个数是计算出来的
	 * 
	 */
	private long pageCount;
	/**
	 * 每页显示几条记录
	 */
	private int pageSize = 10;

	/**
	 * 默认当前页为第一页 这个数是计算出来的
	 */
	private int pageNow = 1;

	/**
	 * 总记录数
	 */
	private long rowCount;

	/**
	 * 从第几条记录开始
	 */
	private int startPage;

	/**
	 * 是否有上页
	 */
	private boolean hasLast;

	/**
	 * 是否有下页
	 */
	private boolean hasNext;

	/**
	 * 规定显示5个页码
	 */
	private int pagecode = 5;

	private boolean searchByES;


	public boolean isSearchByES() {
		return searchByES;
	}

	public void setSearchByES(boolean searchByES) {
		this.searchByES = searchByES;
	}

	public PageView() {
	}

	/**
	 * 要获得记录的开始索引　即开始页码
	 * @return
	 */
	public int getFirstResult() {
		return (this.pageNow - 1) * this.pageSize;
	}

	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	/**
	 * 使用构造函数，强制必需输入每页显示数量和当前页
	 * @param pageSize 每页显示数量
	 * @param pageNow 当前页
	 */
	public PageView(int pageSize, int pageNow) {
		this.pageSize = pageSize;
		this.pageNow = pageNow;
	}

	/**
	 * 使用构造函数，强制必需输入当前页
	 * @param pageNow 当前页
	 */
	public PageView(int pageNow) {
		this.pageNow = pageNow;
		startPage = (this.pageNow - 1) * this.pageSize;
	}

	/**
	 * 查询结果方法把记录数结果集合放入到PageView对象
	 * @param rowCount 总记录数
	 * @param records 结果集合
	 */
	public void setQueryResult(long rowCount, List<T> records) {
		setRowCount(rowCount);
		setRecords(records);
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
		setPageCount(this.rowCount % this.pageSize == 0 ? this.rowCount
				/ this.pageSize : this.rowCount / this.pageSize + 1);
		hasNext = pageNow >= pageCount ? false : true;
		hasLast = pageNow <= 1 ? false : true;
	}

	public List<T> getRecords() {
		return records;
	}
	
	public void setRecords(List<T> records) {
		this.records = records;
	}

	public PageIndex getPageindex() {
		return pageindex;
	}

	public void setPageindex(PageIndex pageindex) {
		this.pageindex = pageindex;
	}

	/**
	 * WebTool这是一个分页工具类
	 * 
	 * pagecode要获得记录的开始索引即开始页码,pageNow当前页 pageCount总页数
	 * 这个工具类返回的是页索引PageIndex
	 * 在这个方法中存在一个问题，每页显示数量和当前页不能为空 必需输入
	 */
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
		this.pageindex = WebTool.getPageIndex(pagecode, pageNow, pageCount);
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public long getPageCount() {
		return pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getRowCount() {
		return rowCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public boolean isHasLast() {
		return hasLast;
	}

	public void setHasLast(boolean hasLast) {
		this.hasLast = hasLast;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public static int getIndex(String pageNow) {
		int start = 1;
		if (null == pageNow || "".equals(pageNow)) {
			start = 1;
		} else {
			if (Integer.parseInt(pageNow) > 1) {
				start = Integer.parseInt(pageNow) * 50 - 49;
			}
		}
		return start;
	}
}
