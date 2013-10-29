package com.nvh.giangvien.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.LoaiCauHoi;

public interface CauHoiRepository extends CrudRepository<CauHoi, String> {

	@Query("select distinct b from CauHoi b left join fetch b.kqs k where b.id = :id")
	public CauHoi findById(@Param("id") String id);
	
	public List<CauHoi> findByloaicau(LoaiCauHoi lch);
}
