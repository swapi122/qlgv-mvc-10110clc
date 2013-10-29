package com.nvh.giangvien.service;

import java.util.List;

import com.nvh.giangvien.model.BangDanhGiaKq;

public interface BangDanhGiaKqService {

	public List<BangDanhGiaKq> findAll();

	public BangDanhGiaKq findById(int id);

	public BangDanhGiaKq save(BangDanhGiaKq ch);

	public void delete(BangDanhGiaKq ch);
	
}
