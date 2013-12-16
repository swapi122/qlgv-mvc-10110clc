package com.nvh.giangvien.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;

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
import com.nvh.giangvien.model.MonHoc;
import com.nvh.giangvien.model.ThoiKhoaBieu;
import com.nvh.giangvien.model.ThongBao;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.BangDanhGiaKqService;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.CauHoiKqService;
import com.nvh.giangvien.service.MonHocService;
import com.nvh.giangvien.service.ThoiKhoaBieuService;
import com.nvh.giangvien.service.ThongBaoService;
import com.nvh.giangvien.service.UserService;
import com.nvh.util.DisplayResult;

@Controller
@RequestMapping("/gvien")
public class GiangVienController {

	@Autowired
	private ThoiKhoaBieuService tkbService;

	@Autowired
	private MonHocService mhService;

	@Autowired
	private BangDanhGiaKqService dgkqService;

	@Autowired
	private BangDanhGiaService bdgService;

	@Autowired
	private BangDanhGiaChoose choose;

	@Autowired
	private CauHoiKqService chkqService;

	private Logger log = LoggerFactory.getLogger(GiangVienController.class);

	@Autowired
	private ThongBaoService tbService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String logined(Model model) {
		List<ThongBao> tkbs = tbService.findAll();
		model.addAttribute("tblist", tkbs);
		return "giangvien";
	}

	@RequestMapping(value="changepwd",method = RequestMethod.GET)
	public String changePass(Model model) {
		return "changepasswordGV";
	}

		
	
	@RequestMapping(value = "kqdanhgia", method = RequestMethod.GET)
	public String kqdanhgia(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("account");
		Set<MonHoc> mhs = user.getMhs();
		List<BangDanhGia> dslchs = bdgService.findAll();
		model.addAttribute("lchs", dslchs);
		log.info(mhs.toString());
		model.addAttribute("tkbs", mhs);
		return "kqdanhgia";
	}

	@RequestMapping(value = "kqdanhgia/{id}",method = RequestMethod.GET)
	public String showkqdanhgia(@PathVariable String id, Model model , HttpServletRequest request) {
		// lay bang danh gia mau
		BangDanhGia bdg;
		if(Integer.parseInt(request.getParameter("iddg")) == choose.getId()){
			bdg = choose.getBgd();
		}else{
			bdg = bdgService.findById(Integer.parseInt(request.getParameter("iddg")));
		}
		if (bdg == null) {
			model.addAttribute("message", "Chưa chọn bảng đánh giá");
			return "kqdanhgia";
		}
		model.addAttribute("bangdanhgia", bdg);
		List<LoaiCauHoi> lchs = new ArrayList<LoaiCauHoi>(bdg.getLchs());
		Collections.sort(lchs);
		model.addAttribute("lchs", lchs);

		MonHoc mh = mhService.findById(id);
		model.addAttribute("mh", mh);
		List<ThoiKhoaBieu> tkbs = tkbService.findByMonhoc(mh);
		List<BangDanhGiaKq> dgkqs = new ArrayList<BangDanhGiaKq>();
		for (ThoiKhoaBieu thoiKhoaBieu : tkbs) {
			if(thoiKhoaBieu.getDgkq()!= null){ dgkqs.add(thoiKhoaBieu.getDgkq());}
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
		log.error("BangDanhGiakq  : " + dgkqs.toString());
		log.info("TKB : " + tkbs.toString());
		log.info("bang ket qua : " + kqs.toString());
		model.addAttribute("kqs", kqs);
		return "showkqdanhgia";
	}
	
	@RequestMapping(value="/info", method = RequestMethod.GET)
	public String getInfo(Model model, HttpServletRequest request){
		User user = userService.findById(request.getParameter("id"));
		model.addAttribute("user", user);
		return "infogiangvien";
	}

}
