package com.nvh.giangvien.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nvh.giangvien.model.BangDanhGiaKq;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.CauHoiKq;

public interface CauHoiKqRepository extends CrudRepository<CauHoiKq, Integer> {
	
	public List<CauHoiKq> findByKetqua(char kq);
	public List<CauHoiKq> findByCauhoi(CauHoi ch);
	public List<CauHoiKq> findByCauhoiAndBangkq(CauHoi ch, BangDanhGiaKq bangkq);
}
