package com.nvh.giangvien.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;












import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nvh.giangvien.model.BangDanhGia;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.LoaiCauHoi;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.CauHoiService;
import com.nvh.giangvien.service.LoaiCauHoiService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private BangDanhGiaService dgService;

	@Autowired
	private LoaiCauHoiService lchService;
	
	@Autowired
	private CauHoiService chService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "admin";
	}

	@RequestMapping(params ="qldg", method = RequestMethod.GET)
	public String quanlyDanhGia(HttpSession session) {
		List<BangDanhGia> dgs = dgService.findAll();
		logger.info(dgs.toString() +"");
		session.setAttribute("danhsachdg", dgs);
		return "admin/qldanhgia";
	}
	
	@RequestMapping(value = "/{id}" , method=RequestMethod.GET)
	public String showBang(@PathVariable("id") int id, Model model){
		logger.info("Get " + id);
		BangDanhGia bdg = dgService.findById(id);
		List<LoaiCauHoi> lchs = lchService.findAll();
		model.addAttribute("bangdanhgia", bdg);
		model.addAttribute("dslch", lchs);
		return "admin/showbang";
	}
	
	@RequestMapping(value = "/{id}" , method=RequestMethod.POST)
	public String deleteBang(@PathVariable("id") int id, Model model){
		logger.info("Xoa " + id);
		BangDanhGia bdg = dgService.findById(id);
		bdg.getBangkqs().clear();
		bdg.getCauhois().clear();
		dgService.delete(bdg);
		return "redirect:/admin?qldg";
	}
	
	
	@RequestMapping(value = "/{id}?form" , method=RequestMethod.GET)
	public String updateBangForm(@PathVariable("id") int id, Model model){
		logger.info("update " + id);
		return "admin/update";
	}
	
	@RequestMapping(value = "/{id}?form" , method=RequestMethod.POST)
	public String updateBang(@PathVariable("id") int id, Model model){
		logger.info("Update " + id);
		return "redirect:/admin?qldg";
	}
	
	@RequestMapping(params = "form" ,method = RequestMethod.POST)
	public String themBang(HttpServletRequest request, RedirectAttributes redirect) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf8");
		BangDanhGia bgd = new BangDanhGia();
		logger.info("Save " + request.getParameter("tenbang"));
		bgd.setTenbang(request.getParameter("tenbang"));
		bgd.setNgaytao(new Date());
		dgService.save(bgd);
		return "redirect:/admin?qldg";
	}
	
	@RequestMapping(params = "form" , method = RequestMethod.GET)
	public String createForm(){
		logger.info("Load Form Them Bang Danh Gia" );
		return "admin/thembang";
	}

	@RequestMapping(value = "question", method = RequestMethod.POST)
	public String getQuestion(HttpServletRequest request, Model model) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf8");
		logger.info("Call tao cau hoi");
		CauHoi ch = new CauHoi();
		ch.setId(request.getParameter("id"));
		ch.setNoidung(request.getParameter("noidung"));
		BangDanhGia bdg = dgService.findById(Integer.parseInt(request.getParameter("bangid")));
		ch.setBang(bdg);
		logger.info(request.getParameter("typequestion"));
		LoaiCauHoi lch = lchService.findById(Integer.parseInt(request.getParameter("typequestion")));
		ch.setLoaicau(lch);
		chService.save(ch);
		logger.info(ch.toString());
		return "redirect:/admin?qldg";
	}
	
	@RequestMapping(value = "question/{id}", method = RequestMethod.POST)
	public String deleteQuestion(@PathVariable("id") String id ,HttpServletRequest request) throws UnsupportedEncodingException{
		logger.info("Call delete cau hoi");
		CauHoi ch = chService.findById(id);
		BangDanhGia bdg = dgService.findById(Integer.parseInt(request.getParameter("bangid")));
		logger.info(ch.getId());
		bdg.getCauhois().remove(ch);
		dgService.save(bdg);
		logger.info("Xoa thanh cong");
		return "redirect:/admin?qldg";
	}
}
