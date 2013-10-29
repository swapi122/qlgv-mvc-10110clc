package com.nvh.giangvien.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nvh.giangvien.model.LoaiCauHoi;

public interface LoaiCauHoiRepository extends CrudRepository<LoaiCauHoi, Integer> {

	@Query("select distinct b from LoaiCauHoi b left join fetch b.cauhois k where b.id = :id")
	public LoaiCauHoi findById(@Param("id") int id);
	
}
