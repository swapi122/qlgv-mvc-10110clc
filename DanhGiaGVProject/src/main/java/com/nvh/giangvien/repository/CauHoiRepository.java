package com.nvh.giangvien.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nvh.giangvien.model.CauHoi;

public interface CauHoiRepository extends CrudRepository<CauHoi, String> {

	@Query("select distinct b from CauHoi b left join fetch b.kqs k where b.id = :id")
	public CauHoi findById(@Param("id") String id);
	
}
