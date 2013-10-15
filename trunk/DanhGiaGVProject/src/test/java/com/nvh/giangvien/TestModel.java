package com.nvh.giangvien;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nvh.giangvien.model.BangDanhGia;
import com.nvh.giangvien.service.BangDanhGiaService;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext mContext = new ClassPathXmlApplicationContext("root-context.xml");
		BangDanhGiaService dgService = (BangDanhGiaService) mContext.getBean("JpaBangDanhGiaService");
		BangDanhGia mDG = dgService.findById(2);
		mDG.getBangkqs().clear();
		mDG.getCauhois().clear();
		dgService.delete(mDG);
	}

}
