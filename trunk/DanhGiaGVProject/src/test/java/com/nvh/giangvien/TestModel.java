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
		CauHoi ch = chService.findById("dasdasd");
		System.out.println(ch.getNoidung());
		BangDanhGia bdg = dgService.findById(1);
		bdg.getCauhois().remove(ch);
		dgService.save(bdg);
		//chService.delete(ch);
	}

}
