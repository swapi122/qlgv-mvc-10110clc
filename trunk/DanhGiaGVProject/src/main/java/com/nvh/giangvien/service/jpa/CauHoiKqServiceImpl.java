package com.nvh.giangvien.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nvh.giangvien.model.CauHoiKq;
import com.nvh.giangvien.repository.CauHoiKqRepository;
import com.nvh.giangvien.service.CauHoiKqService;

@Service("JpaCauHoiKqService")
public class CauHoiKqServiceImpl implements CauHoiKqService {

	@Autowired
	private CauHoiKqRepository kqRepository;
	
	@Override
	public List<CauHoiKq> findAll() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(kqRepository.findAll());
	}

	@Override
	public CauHoiKq findById(int id) {
		// TODO Auto-generated method stub
		return kqRepository.findOne(id);
	}

	@Override
	public CauHoiKq save(CauHoiKq ch) {
		// TODO Auto-generated method stub
		return kqRepository.save(ch);
	}

	@Override
	public void delete(CauHoiKq ch) {
		// TODO Auto-generated method stub
		kqRepository.delete(ch);
	}

}
