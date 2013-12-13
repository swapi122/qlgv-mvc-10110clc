package com.nvh.giangvien.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nvh.giangvien.model.ThongBao;
import com.nvh.giangvien.repository.ThongBaoRepository;
import com.nvh.giangvien.service.ThongBaoService;

@Service
public class ThongBaoServiceImpl implements ThongBaoService {

	@Autowired
	private ThongBaoRepository tbRepository;
	
	@Override
	public List<ThongBao> findAll() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(tbRepository.findAll());
	}

	@Override
	public ThongBao save(ThongBao tb) {
		// TODO Auto-generated method stub
		return tbRepository.save(tb);
	}

	@Override
	public void delete(ThongBao tb) {
		// TODO Auto-generated method stub
		tbRepository.delete(tb);
	}

	@Override
	public ThongBao findById(int id) {
		// TODO Auto-generated method stub
		return tbRepository.findOne(id);
	}

}
