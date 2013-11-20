package com.nvh.giangvien.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nvh.giangvien.model.BangDanhGiaKq;
import com.nvh.giangvien.model.ThoiKhoaBieu;

public interface BangDanhGiaKqRepository extends CrudRepository<BangDanhGiaKq, Integer> {

	public BangDanhGiaKq findByMonhocdg(ThoiKhoaBieu tkb);

}
