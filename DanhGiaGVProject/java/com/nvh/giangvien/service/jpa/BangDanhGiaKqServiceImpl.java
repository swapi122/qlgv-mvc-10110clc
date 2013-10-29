package com.nvh.giangvien.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nvh.giangvien.model.BangDanhGiaKq;
import com.nvh.giangvien.repository.BangDanhGiaKqRepository;
import com.nvh.giangvien.service.BangDanhGiaKqService;

@Service("JpaBangDanhGiaKqService")
public class BangDanhGiaKqServiceImpl implements BangDanhGiaKqService {

	@Autowired
	private BangDanhGiaKqRepository bdgkqRepository;
	
	@Override
	public List<BangDanhGiaKq> findAll() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(bdgkqRepository.findAll());
	}

	@Override
	public BangDanhGiaKq findById(int id) {
		// TODO Auto-generated method stub
		return bdgkqRepository.findOne(id);
	}

	@Override
	public BangDanhGiaKq save(BangDanhGiaKq ch) {
		// TODO Auto-generated method stub
		return bdgkqRepository.save(ch);
	}

	@Override
	public void delete(BangDanhGiaKq ch) {
		// TODO Auto-generated method stub
		bdgkqRepository.delete(ch);
	}

}
