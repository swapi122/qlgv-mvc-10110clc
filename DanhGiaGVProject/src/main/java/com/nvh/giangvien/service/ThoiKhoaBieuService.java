package com.nvh.giangvien.service;

import java.util.List;

import com.nvh.giangvien.model.ThoiKhoaBieu;

public interface ThoiKhoaBieuService {

	public List<ThoiKhoaBieu> findAll();

	public ThoiKhoaBieu findById(int id);
	
	public List<ThoiKhoaBieu> findBySV(String idsv);

	public ThoiKhoaBieu save(ThoiKhoaBieu tkb);

	public void delete(ThoiKhoaBieu tkb);
	
}
