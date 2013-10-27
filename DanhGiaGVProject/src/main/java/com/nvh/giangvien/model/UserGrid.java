package com.nvh.giangvien.model;

import java.util.List;

public class UserGrid {

	private int totalPages;
	private int currentPage;
	private long totalRecords;
	private List<User> userData;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<User> getUserData() {
		return userData;
	}

	public void setUserData(List<User> userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		return "UserGrid [totalPages=" + totalPages + ", currentPage="
				+ currentPage + ", totalRecords=" + totalRecords
				+ ", userData=" + userData + "]";
	}
	
	

}
