package com.nvh.giangvien.service;

import java.util.List;

import com.nvh.giangvien.model.CauHoiKq;


public interface CauHoiKqService {

	public List<CauHoiKq> findAll();

	public CauHoiKq findById(int id);

	public CauHoiKq save(CauHoiKq ch);

	public void delete(CauHoiKq ch);
	
}
