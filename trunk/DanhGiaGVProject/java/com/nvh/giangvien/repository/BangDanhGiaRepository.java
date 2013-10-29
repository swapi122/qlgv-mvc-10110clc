package com.nvh.giangvien.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nvh.giangvien.model.BangDanhGia;

public interface BangDanhGiaRepository extends CrudRepository<BangDanhGia, Integer> {

	@Query("select distinct b from BangDanhGia b left join fetch b.bangkqs k left join fetch b.cauhois c where b.id = :id")
	public BangDanhGia findById(@Param("id") int id);
	
}
