package com.nvh.giangvien.service;

import java.util.List;

import com.nvh.giangvien.model.BangDanhGia;

public interface BangDanhGiaService {

	public List<BangDanhGia> findAll();

	public BangDanhGia findById(int id);
	
	public BangDanhGia save(BangDanhGia ch);

	public void delete(BangDanhGia ch);
	
}
