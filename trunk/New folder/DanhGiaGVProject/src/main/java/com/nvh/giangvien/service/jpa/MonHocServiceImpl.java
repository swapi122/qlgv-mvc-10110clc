package com.nvh.giangvien.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nvh.giangvien.model.MonHoc;
import com.nvh.giangvien.repository.MonHocRepository;
import com.nvh.giangvien.service.MonHocService;

@Service("JpaMonHocService")
public class MonHocServiceImpl implements MonHocService {

	@Autowired
	private MonHocRepository mhRepository;
	
	@Override
	public List<MonHoc> findAll() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(mhRepository.findAll());
	}

	@Override
	public MonHoc findById(String id) {
		// TODO Auto-generated method stub
		return mhRepository.findOne(id);
	}

	@Override
	public MonHoc save(MonHoc tkb) {
		// TODO Auto-generated method stub
		return mhRepository.save(tkb);
	}

	@Override
	public void delete(MonHoc tkb) {
		// TODO Auto-generated method stub
		mhRepository.delete(tkb);
	}

}
