package com.nvh.giangvien.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nvh.giangvien.model.LoaiCauHoi;
import com.nvh.giangvien.repository.LoaiCauHoiRepository;
import com.nvh.giangvien.service.LoaiCauHoiService;

@Service("JpaLoaiCauHoiService")
public class LoaiCauHoiServiceImpl implements LoaiCauHoiService {

	@Autowired
	private LoaiCauHoiRepository lchRepository;
	
	@Override
	public List<LoaiCauHoi> findAll() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(lchRepository.findAll());
	}

	@Override
	public LoaiCauHoi findById(int id) {
		// TODO Auto-generated method stub
		return lchRepository.findOne(id);
	}

	@Override
	public LoaiCauHoi save(LoaiCauHoi lch) {
		// TODO Auto-generated method stub
		return lchRepository.save(lch);
	}

	@Override
	public void delete(LoaiCauHoi lch) {
		// TODO Auto-generated method stub
		lchRepository.delete(lch);

	}

}
