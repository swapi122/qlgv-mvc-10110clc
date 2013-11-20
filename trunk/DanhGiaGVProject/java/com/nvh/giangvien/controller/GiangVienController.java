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
		model.addAttribute("bangdanhgia", bdg);
		List<LoaiCauHoi> lchs = new ArrayList<LoaiCauHoi>(bdg.getLchs());
		Collections.sort(lchs);
		model.addAttribute("lchs", lchs);

		MonHoc mh = mhService.findById(id);
		List<ThoiKhoaBieu> tkbs = tkbService.findByMonhoc(mh);

		for (CauHoi cauHoi : bdg.getCauhois()) {
			// tinh %a %b %c %d cua moi cau hoi
			int numA = 0, numB = 0, numC = 0, numD = 0;
			List<CauHoiKq> chkqs = chkqService.findByCauhoi(cauHoi);
			for (CauHoiKq cauHoiKq : chkqs) {
				if (tkbs.contains(cauHoiKq.getBangkq().getMonhocdg())) {
					switch (cauHoiKq.getKetqua()) {
					case 'A':
						numA++;
						break;
					case 'B':
						numB++;
						break;
					case 'C':
						numC++;
						break;
					case 'D':
						numD++;
						break;
					}
				}
			}

		}

		return "showkqdanhgia";
	}

}
