package com.nvh.giangvien.model;

public class SearchCriteriaPage {

	private String id;
	private String hoten;
	private int typeacount;
	private int offset;
	private int pageSize;
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public int getTypeacount() {
		return typeacount;
	}

	public void setTypeacount(int typeacount) {
		this.typeacount = typeacount;
	}

}
