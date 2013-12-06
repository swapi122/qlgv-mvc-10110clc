package com.nvh.giangvien.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.BangDanhGiaKqService;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.CauHoiKqService;
import com.nvh.giangvien.service.MonHocService;
import com.nvh.giangvien.service.ThoiKhoaBieuService;
import com.nvh.util.DisplayResult;

@Controller
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

	@RequestMapping(value = "/gvien", method = RequestMethod.GET)
	public String logined() {
		return "giangvien";
	}

	@RequestMapping(value = "/gvien/kqdanhgia", method = RequestMethod.GET)
	public String kqdanhgia(HttpServletRequest request, Model model) {
		User user = (User) request.getSession().getAttribute("account");
		Set<MonHoc> mhs = user.getMhs();

		log.info(mhs.toString());
		model.addAttribute("tkbs", mhs);
		return "kqdanhgia";
	}

	@RequestMapping(value = "/gvien/kqdanhgia/{id}", method = RequestMethod.GET)
	public String showkqdanhgia(@PathVariable String id, Model model) {
		// lay bang danh gia mau
		BangDanhGia bdg = bdgService.findById(choose.getId());
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
		log.error("BangDanhGiakq  : " + dgkqs.toString());
		log.info("TKB : " + tkbs.toString());
		log.info("bang ket qua : " + kqs.toString());
		model.addAttribute("kqs", kqs);
		return "showkqdanhgia";
	}

}
