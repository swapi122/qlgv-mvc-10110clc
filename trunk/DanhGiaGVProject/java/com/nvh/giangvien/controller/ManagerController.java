package com.nvh.giangvien.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.nvh.applicationscope.UserGrid;
import com.nvh.giangvien.model.BangDanhGia;
import com.nvh.giangvien.model.BangDanhGiaKq;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.CauHoiKq;
import com.nvh.giangvien.model.LoaiCauHoi;
import com.nvh.giangvien.model.MonHoc;
import com.nvh.giangvien.model.SearchCriteria;
import com.nvh.giangvien.model.ThoiKhoaBieu;
import com.nvh.giangvien.model.ThongBao;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.BangDanhGiaKqService;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.MonHocService;
import com.nvh.giangvien.service.ThoiKhoaBieuService;
import com.nvh.giangvien.service.ThongBaoService;
import com.nvh.giangvien.service.UserService;
import com.nvh.util.DisplayResult;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private ThoiKhoaBieuService tkbService;
	
	@Autowired
	private MonHocService mhService;
	
	final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private BangDanhGiaService bdgService;

	@Autowired
	private BangDanhGiaKqService dgkqService;
	@Autowired
	private ThongBaoService tbService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String logined(Model model) {
		List<ThongBao> tkbs = tbService.findAll();
		model.addAttribute("tblist", tkbs);
		return "manager";
	}
	
	@RequestMapping(value="/tracuugv" ,method = RequestMethod.GET)
	public String tracuugiangvien(Model model){
		List<User> gvs = userService.findByTypeAccount(4);
		model.addAttribute("gvs", gvs);
		return "tracuugiangvien";
	}
	
	@RequestMapping(value="/tracuugv/{id}", method = RequestMethod.GET)
	public String kqgiangvien(@PathVariable String id ,Model model, HttpServletRequest request){
		User user = userService.findById(id);
		Set<MonHoc> mhs = user.getMhs();
		model.addAttribute("gv", user);
		List<BangDanhGia> dslchs = bdgService.findAll();
		model.addAttribute("lchs", dslchs);
		model.addAttribute("tkbs", mhs);
		return "kqdanhgiaGV";
	}
	
	@RequestMapping(value = "kqdanhgiagv/{id}",method = RequestMethod.GET)
	public String showkqdanhgiaGV(@PathVariable String id, Model model , HttpServletRequest request) {
		// lay bang danh gia mau
		User gv = userService.findById(id);
		model.addAttribute("gv", gv);
		BangDanhGia bdg = bdgService.findById(Integer.parseInt(request.getParameter("iddg")));
		if (bdg == null) {
			model.addAttribute("message", "Chưa chọn bảng đánh giá");
			return "kqdanhgia";
		}
		model.addAttribute("bangdanhgia", bdg);
		List<LoaiCauHoi> lchs = new ArrayList<LoaiCauHoi>(bdg.getLchs());
		Collections.sort(lchs);
		model.addAttribute("lchs", lchs);

		MonHoc mh = mhService.findById(request.getParameter("mmh"));
		model.addAttribute("mh", mh);
		List<ThoiKhoaBieu> tkbs = tkbService.findByMonhoc(mh);
		List<BangDanhGiaKq> dgkqs = new ArrayList<BangDanhGiaKq>();
		for (ThoiKhoaBieu thoiKhoaBieu : tkbs) {
			BangDanhGiaKq dgkq = null;
			if ((dgkq = dgkqService.findByMonhocdg(thoiKhoaBieu)) != null) {
				dgkqs.add(dgkq);
			}
		}
		List<DisplayResult> kqs = new ArrayList<DisplayResult>();
		for (CauHoi cauHoi : bdg.getCauhois()) {
			DisplayResult kq = new DisplayResult();
			kq.setCh(cauHoi);
			kq.setMch(cauHoi.getId());
			int a = 0, b = 0, c = 0, d = 0;
			for (BangDanhGiaKq bangDanhGiaKq : dgkqs) {
				if (bangDanhGiaKq.getLoaiBang().getId() == bdg.getId()) {
					for (CauHoiKq chkq : bangDanhGiaKq.getCauhoikqs()) {
						if (chkq.getCauhoi().getId().equals(cauHoi.getId())) {
							switch (chkq.getKetqua()) {
							case 'A':
								a++;
								break;
							case 'B':
								b++;
								break;
							case 'C':
								c++;
								break;
							case 'D':
								d++;
								break;
							}
						}
					}
				}
			}
			kq.setNumA(((double) a / dgkqs.size()) * 100);
			kq.setNoidungA("Rất Tốt : " + kq.getNumA() + "%");
			kq.setNumB(((double) b / dgkqs.size()) * 100);
			kq.setNoidungB("Tốt : " + kq.getNumB() + "%");
			kq.setNumC(((double) c / dgkqs.size()) * 100);
			kq.setNoidungC("Bình Thường : " + kq.getNumC() + "%");
			kq.setNumD(((double) d / dgkqs.size()) * 100);
			kq.setNoidungD("Chưa Tốt : " + kq.getNumD() + "%");
			kqs.add(kq);
		}
		model.addAttribute("kqs", kqs);
		return "showkqdanhgiaGV";
	}
}
