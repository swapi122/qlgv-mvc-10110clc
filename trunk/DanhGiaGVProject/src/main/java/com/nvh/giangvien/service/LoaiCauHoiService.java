package com.nvh.giangvien.service;

import java.util.List;

import com.nvh.giangvien.model.LoaiCauHoi;


public interface LoaiCauHoiService {

	public List<LoaiCauHoi> findAll();

	public LoaiCauHoi findById(int id);

	public LoaiCauHoi save(LoaiCauHoi lch);

	public void delete(LoaiCauHoi lch);
	
}
