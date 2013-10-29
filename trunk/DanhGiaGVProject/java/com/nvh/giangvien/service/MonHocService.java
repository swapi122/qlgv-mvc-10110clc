package com.nvh.giangvien.service;

import java.util.List;

import com.nvh.giangvien.model.MonHoc;

public interface MonHocService {

	public List<MonHoc> findAll();

	public MonHoc findById(String id);

	public MonHoc save(MonHoc tkb);

	public void delete(MonHoc tkb);
	
}
