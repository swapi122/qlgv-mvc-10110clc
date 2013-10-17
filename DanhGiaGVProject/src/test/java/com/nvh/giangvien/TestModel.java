package com.nvh.giangvien;

import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nvh.giangvien.model.BangDanhGia;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.LoaiCauHoi;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.CauHoiService;
import com.nvh.giangvien.service.LoaiCauHoiService;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext mContext = new ClassPathXmlApplicationContext("root-context.xml");
		BangDanhGiaService dgService = (BangDanhGiaService) mContext.getBean("JpaBangDanhGiaService");
		LoaiCauHoiService lchService = (LoaiCauHoiService) mContext.getBean("JpaLoaiCauHoiService");
		CauHoiService chService = (CauHoiService) mContext.getBean("JpaCauHoiService");
		System.out.println(dgService.findAll());
		CauHoi ch = new CauHoi();
		ch.setId("CH01");
		ch.setNoidung("la la la");
		BangDanhGia bdg = dgService.findById(12);
		ch.setBang(bdg);
		LoaiCauHoi lch = lchService.findById(1);
		ch.setLoaicau(lch);
		chService.save(ch);
	}

}
