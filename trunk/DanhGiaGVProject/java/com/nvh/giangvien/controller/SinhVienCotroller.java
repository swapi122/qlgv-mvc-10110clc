package com.nvh.giangvien.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import com.nvh.applicationscope.BangDanhGiaChoose;
import com.nvh.giangvien.model.BangDanhGia;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.ThoiKhoaBieu;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.CauHoiService;
import com.nvh.giangvien.service.LoaiCauHoiService;
import com.nvh.giangvien.service.ThoiKhoaBieuService;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienCotroller {

	private Logger log = LoggerFactory.getLogger(SinhVienCotroller.class);

	@Autowired
	private ThoiKhoaBieuService tkbService;

	@Autowired
	private CauHoiService chService;
	
	@Autowired
	private LoaiCauHoiService lchService;
	
	@Autowired
	private BangDanhGiaChoose choose;

	@Autowired
	private BangDanhGiaService bdgService;

	@RequestMapping(method = RequestMethod.GET)
	public String logined(Model model, HttpServletRequest request) {
		// load tkb theo sinh vien do
		User use = (User)request.getSession().getAttribute("account");
		List<ThoiKhoaBieu> tkbs = tkbService.findBySV(use);
		model.addAttribute("tkblist", tkbs);
		return "sinhvien";
	}

	@RequestMapping(value = "/danhgia/{id}", method = RequestMethod.GET)
	public String danhgia(@PathVariable("id") int id, Model model) {
		log.info("Start Danh Gia" + id);
		// lay bang danh gia hien tai
		BangDanhGia bdg = bdgService.findById(choose.getId());
		// gom nhom
		ThoiKhoaBieu tkb = tkbService.findById(id);
		model.addAttribute("thoikhoabieu",tkb);
		model.addAttribute("bangdanhgia",bdg);
		return "sinhviendgia";
	}
}
