package com.nvh.giangvien.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.repository.CauHoiRepository;
import com.nvh.giangvien.service.CauHoiService;

@Service("JpaCauHoiService")
public class CauHoiServiceImpl implements CauHoiService {

	@Autowired
	private CauHoiRepository chRepository;
	
	@Override
	public List<CauHoi> findAll() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(chRepository.findAll());
	}

	@Override
	public CauHoi findById(String id) {
		// TODO Auto-generated method stub
		return chRepository.findOne(id);
	}

	@Override
	public CauHoi save(CauHoi ch) {
		// TODO Auto-generated method stub
		return chRepository.save(ch);
	}

	@Override
	public void delete(CauHoi ch) {
		// TODO Auto-generated method stub
		chRepository.delete(ch);
	}

}
