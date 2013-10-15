package com.nvh.giangvien.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.nvh.giangvien.model.ThoiKhoaBieu;
import com.nvh.giangvien.repository.ThoiKhoaBieuRepository;
import com.nvh.giangvien.service.ThoiKhoaBieuService;

@Service("JpaThoikhoabieuService")
public class ThoiKhoaBieuServiceImpl implements ThoiKhoaBieuService {

	@Autowired
	private ThoiKhoaBieuRepository tkbRepository;
	
	@Override
	public List<ThoiKhoaBieu> findAll() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(tkbRepository.findAll());
	}

	@Override
	public ThoiKhoaBieu findById(int id) {
		// TODO Auto-generated method stub
		return tkbRepository.findOne(id);
	}

	@Override
	public List<ThoiKhoaBieu> findBySV(String idsv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ThoiKhoaBieu save(ThoiKhoaBieu tkb) {
		// TODO Auto-generated method stub
		return tkbRepository.save(tkb);
	}

	@Override
	public void delete(ThoiKhoaBieu tkb) {
		// TODO Auto-generated method stub
		tkbRepository.delete(tkb);
	}

}
