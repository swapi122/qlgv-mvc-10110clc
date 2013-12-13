package com.nvh.giangvien.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nvh.applicationscope.BangDanhGiaChoose;
import com.nvh.giangvien.model.BangDanhGia;
import com.nvh.giangvien.model.BangDanhGiaKq;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.CauHoiKq;
import com.nvh.giangvien.model.LoaiCauHoi;
import com.nvh.giangvien.model.ThoiKhoaBieu;
import com.nvh.giangvien.model.TimeBean;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.BangDanhGiaKqService;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.CauHoiKqService;
import com.nvh.giangvien.service.CauHoiService;
import com.nvh.giangvien.service.LoaiCauHoiService;
import com.nvh.giangvien.service.ThoiKhoaBieuService;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienCotroller {

	private Logger log = LoggerFactory.getLogger(SinhVienCotroller.class);

	@Autowired
	private TimeBean time;
	
	@Autowired
	private ThoiKhoaBieuService tkbService;

	@Autowired
	private CauHoiService chService;

	@Autowired
	private CauHoiKqService chkqService;

	@Autowired
	private LoaiCauHoiService lchService;

	@Autowired
	private BangDanhGiaChoose choose;

	@Autowired
	private BangDanhGiaService bdgService;

	@Autowired
	private BangDanhGiaKqService bdgkqService;

	@RequestMapping(method = RequestMethod.GET)
	public String logined(Model model, HttpServletRequest request) {
		// load tkb theo sinh vien do
		User use = (User) request.getSession().getAttribute("account");
		List<ThoiKhoaBieu> tkbs = tkbService.findBySV(use);
		model.addAttribute("tkblist", tkbs);
		return "sinhvien";
	}

	@RequestMapping(value = "/danhgia/{id}", method = RequestMethod.GET)
	public String danhgia(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		log.info("Start Danh Gia" + id);
		DateTime timeNow = new DateTime();
		int result = timeNow.compareTo(time.getTimeBD());
		log.info("Time BD : " + time.getTimeBD().toString() + " | Time KT : " + time.getTimeKT().toString());
		// lay bang danh gia hien tai
		if (choose.getId() == 0) {
			model.addAttribute("error", "Chưa có bảng đánh giá.");
			return "sinhviendgia";
		}
		if(result <= 0){
			model.addAttribute("error", "Chưa đến thời gian cho phép đánh giá");
			return "sinhviendgia";
		}else{
			//sau khi thoi gian bd.
			int resultKT = timeNow.compareTo(time.getTimeKT());
			if(resultKT == 1){
				//qua' han
				model.addAttribute("error", "Đã quá hạn đăng ký!. Vui lòng quay lại sau!");
				return "sinhviendgia";
			}
		}
		BangDanhGiaKq dgkq = bdgkqService.findByMonhocdg(tkbService.findById(id));
		if (dgkq == null) {
			BangDanhGia bdg = bdgService.findById(choose.getId());
			List<LoaiCauHoi> lchs = new ArrayList<LoaiCauHoi>(bdg.getLchs());
			Collections.sort(lchs);
			model.addAttribute("lchs", lchs);
			// gom nhom
			ThoiKhoaBieu tkb = tkbService.findById(id);
			model.addAttribute("thoikhoabieu",tkb);
			model.addAttribute("bangdanhgia", bdg);
			return "sinhviendgia";
		} else {
			//da danh gia mon hoc nay roi
			BangDanhGia bdg = bdgService.findById(choose.getId());
			List<LoaiCauHoi> lchs = new ArrayList<LoaiCauHoi>(bdg.getLchs());
			Collections.sort(lchs);
			model.addAttribute("lchs", lchs);
			// gom nhom
			ThoiKhoaBieu tkb = tkbService.findById(id);
			model.addAttribute("thoikhoabieu",tkb);
			model.addAttribute("bangdanhgia", bdg);
			model.addAttribute("bangdanhgiakq", dgkq);
			log.info(dgkq.toString());
			return "sinhviendgia";
		}
	}

	@RequestMapping(value = "/danhgia/{id}", method = RequestMethod.POST)
	public String luudanhgia(@PathVariable("id") int idBang,
			HttpServletRequest request, Model model) {
		try {
			int id = Integer.parseInt(request.getParameter("idtkb"));
			BangDanhGiaKq dgkq = new BangDanhGiaKq();
			// tim loai bang dung de danh gia
			dgkq.setLoaiBang(bdgService.findById(idBang));
			dgkq.setMonhocdg(tkbService.findById(id));

			dgkq.setNgaytao(new Date());
			// luu ket qua bang danh gia

			// luu cau hoi
			
			BangDanhGia lch = bdgService.findById(idBang);
			List<CauHoi> chs = chService.findByBangDanhGia(lch);
			for (CauHoi cauHoi : chs) {
				String kq = request.getParameter(cauHoi.getId());
				CauHoiKq chkq = new CauHoiKq();
				chkq.setBangkq(dgkq);
				chkq.setCauhoi(cauHoi);
				chkq.setKetqua(kq.charAt(0));
				dgkq.getCauhoikqs().add(chkq);
			}
			bdgkqService.save(dgkq);
			model.addAttribute("success", "Đánh giá lưu thành công!");
			return "danhgiasuccess";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("fail", "Đánh giá lưu không thành công!");
			return "danhgiasuccess";
		}
		
	}
	
	@RequestMapping(value = "/danhgia/{id}", params="update" ,method = RequestMethod.POST)
	public String updatedanhgia(@PathVariable("id") int idBang,
			HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("idtkb"));
		BangDanhGiaKq dgkq = bdgkqService.findByMonhocdg(tkbService.findById(id));
		// tim loai bang dung de danh gia

		// luu cau hoi
		for (CauHoiKq chkq : dgkq.getCauhoikqs()) {
			//dgkq.getCauhoikqs().remove(chkq);
			String kq = request.getParameter(String.valueOf(chkq.getId()));
			chkq.setKetqua(kq.charAt(0));
			chkqService.save(chkq);
		}
		dgkq.setNgaytao(new Date());
		bdgkqService.save(dgkq);
		return "sinhvien";
	}
}
