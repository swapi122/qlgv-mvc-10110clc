package com.nvh.giangvien.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nvh.giangvien.model.MonHoc;
import com.nvh.giangvien.model.ThoiKhoaBieu;
import com.nvh.giangvien.model.User;

public interface ThoiKhoaBieuRepository extends CrudRepository<ThoiKhoaBieu, Integer> {
	
	public List<ThoiKhoaBieu> findBySv(User user);
	
	public List<ThoiKhoaBieu> findByGv(User user);
	
	public List<ThoiKhoaBieu> findByMonhoc(MonHoc mh);
}
