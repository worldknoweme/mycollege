package com.cx.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageEntity implements Serializable{
	private static final long serialVersionUID = 3428610204086857703L;
	private int totalResultSize = 0;
	private int totalPageSize = 0;
	private int pageSize = 10;
	private int currentPage = 1;
	private int startRow = 0;
	private boolean first = false;
	private boolean last = false;

	public PageEntity() {
	}

	public PageEntity(int _totalRows, int _pageSize) {
		this.totalResultSize = _totalRows;
		this.pageSize = _pageSize;
		this.totalPageSize = this.totalResultSize / this.pageSize;
		int mod = this.totalResultSize % this.pageSize;
		if(mod > 0) {
			++this.totalPageSize;
		}

		this.currentPage = 1;
		this.startRow = 0;
	}

	public PageEntity(int currentPage, int _totalRows, int _pageSize) {
		int totalPages1 = _totalRows / _pageSize;
		int mod1 = _totalRows % _pageSize;
		this.totalResultSize = _totalRows;
		this.pageSize = _pageSize;
		if(mod1 > 0) {
			++totalPages1;
		}

		if(currentPage > totalPages1) {
			--currentPage;
		}

		if(currentPage == 0) {
			this.setStart(1);
		} else {
			this.setStart(currentPage);
		}

		this.totalPageSize = this.totalResultSize / this.pageSize;
		int mod = this.totalResultSize % this.pageSize;
		if(mod > 0) {
			++this.totalPageSize;
		} else if(currentPage == 0) {
			boolean var7 = true;
		}

	}

	public boolean isFirst() {
		return this.getCurrentPage() <= 1;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		return this.getCurrentPage() >= this.getTotalPageSize();
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public List<Integer> getPageNums() {
		byte maxDispalyCount = 7;
		byte beforeCount = 3;
		ArrayList returnList = new ArrayList();
		int maxNum_new;
		if(this.getTotalPageSize() <= maxDispalyCount) {
			for(maxNum_new = 1; maxNum_new <= this.getTotalPageSize(); ++maxNum_new) {
				returnList.add(Integer.valueOf(maxNum_new));
			}

			return returnList;
		} else {
			maxNum_new = this.getCurrentPage() > beforeCount?maxDispalyCount:maxDispalyCount - this.currentPage;
			int discnt = 1;

			int i;
			for(i = beforeCount; i > 0; --i) {
				if(this.currentPage > i) {
					returnList.add(Integer.valueOf(this.currentPage - i));
					++discnt;
				}
			}

			returnList.add(Integer.valueOf(this.currentPage));

			for(i = 1; i <= maxNum_new && this.currentPage + i <= this.getTotalPageSize() && discnt < maxDispalyCount; ++i) {
				returnList.add(Integer.valueOf(this.currentPage + i));
				++discnt;
			}

			return returnList;
		}
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartRecord() {
		return (this.currentPage - 1) * this.pageSize;
	}

	public int getEndRecord() {
		return this.getStartRecord() + this.pageSize - 1;
	}

	public int getTotalResultSize() {
		return this.totalResultSize;
	}

	public void setTotalResultSize(int totalResultSize) {
		this.totalResultSize = totalResultSize;
	}

	public int getTotalPageSize() {
		return this.totalPageSize;
	}

	public void setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	public void setStart(int currentPage) {
		this.currentPage = currentPage;
		this.startRow = (currentPage - 1) * this.pageSize;
	}

	public int getStartRow() {
		return this.startRow;
	}

	public static PageEntity getPageEntity(int currentPage, int totalRows, int pageSize) {
		PageEntity pager = new PageEntity(totalRows, pageSize);
		if(currentPage == 0) {
			pager.setStart(1);
		} else {
			pager.setStart(currentPage);
		}

		return pager;
	}
	
}
