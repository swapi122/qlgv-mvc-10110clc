package com.nvh.giangvien.service;

import java.util.List;

import com.nvh.giangvien.model.CauHoi;

public interface CauHoiService {

	public List<CauHoi> findAll();

	public CauHoi findById(String id);

	public CauHoi save(CauHoi ch);

	public void delete(CauHoi ch);
	
}
