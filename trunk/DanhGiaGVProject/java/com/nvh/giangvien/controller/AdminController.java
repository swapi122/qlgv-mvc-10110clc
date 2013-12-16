package com.nvh.giangvien.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.nvh.applicationscope.BangDanhGiaChoose;
import com.nvh.applicationscope.UserGrid;
import com.nvh.giangvien.model.BangDanhGia;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.FileBean;
import com.nvh.giangvien.model.LoaiCauHoi;
import com.nvh.giangvien.model.SearchCriteria;
import com.nvh.giangvien.model.ThongBao;
import com.nvh.giangvien.model.TimeBean;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.CauHoiService;
import com.nvh.giangvien.service.ImportService;
import com.nvh.giangvien.service.LoaiCauHoiService;
import com.nvh.giangvien.service.ThongBaoService;
import com.nvh.giangvien.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private TimeBean time;

	@Autowired
	private BangDanhGiaService dgService;

	@Autowired
	private LoaiCauHoiService lchService;

	@Autowired
	private CauHoiService chService;

	@Autowired
	private UserService userService;

	@Autowired
	private BangDanhGiaChoose choose;

	@Autowired
	private ThongBaoService tbService;

	@Autowired
	private ImportService importService;

	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "admin";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params = "qldg", method = RequestMethod.GET)
	public String quanlyDanhGia(HttpSession session) {
		List<BangDanhGia> dgs = dgService.findAll();
		logger.info(dgs.toString() + "");
		session.setAttribute("danhsachdg", dgs);
		return "admin/qldanhgia";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showBang(@PathVariable("id") int id, Model model) {
		logger.info("Get " + id);
		BangDanhGia bdg = dgService.findById(id);
		List<LoaiCauHoi> lchs = lchService.findAll();
		List<LoaiCauHoi> lchs1 = new ArrayList<LoaiCauHoi>(bdg.getLchs());
		Collections.sort(lchs1);

		model.addAttribute("lchs1", lchs1);
		model.addAttribute("bangdanhgia", bdg);
		model.addAttribute("dslch", lchs);
		return "admin/showbang";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String deleteBang(@PathVariable("id") int id, Model model) {
		logger.info("Xoa " + id);
		BangDanhGia bdg = dgService.findById(id);
		bdg.getBangkqs().clear();
		bdg.getCauhois().clear();
		dgService.delete(bdg);
		return "redirect:/admin?qldg";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/{id}", params = "update", method = RequestMethod.POST)
	public String updateBang(@PathVariable("id") int id,
			HttpServletRequest request) {
		logger.info("Update " + id);
		BangDanhGia bdg = dgService.findById(id);
		bdg.setTenbang(request.getParameter("noidung"));
		dgService.save(bdg);
		return "redirect:/admin?qldg";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String themBang(HttpServletRequest request,
			RedirectAttributes redirect) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf8");
		BangDanhGia bgd = new BangDanhGia();
		logger.info("Save " + request.getParameter("tenbang"));
		bgd.setTenbang(request.getParameter("tenbang"));
		bgd.setNgaytao(new Date());
		dgService.save(bgd);
		return "redirect:/admin?qldg";
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm() {
		logger.info("Load Form Them Bang Danh Gia");
		return "admin/thembang";
	}

	// get all questio
	@RequestMapping(value = "getAllQuestion", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody
	String getAllQuestTion() {
		logger.info("Load All question");
		List<CauHoi> chs = chService.findAll();
		for (int i = 0 ; i < chs.size() ; i++){
			for(int j = 0; j< chs.size() ; j++){
				if(i != j){
					if(chs.get(i).getNoidung().equalsIgnoreCase(chs.get(j).getNoidung())){
						chs.remove(j);
					}
				}
			}
		}
		logger.info(chs.toString());
		Map<String, String> chsm = new HashMap<String, String>();
		for (CauHoi i : chs)
			chsm.put(i.getId(), i.getNoidung());
		String json = new Gson().toJson(chsm);
		return json;
	}

	@RequestMapping(value = "question", method = RequestMethod.POST)
	public @ResponseBody
	String getQuestion(HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf8");
		logger.info("Call tao cau hoi");
		BangDanhGia bdg = dgService.findById(Integer.parseInt(request
				.getParameter("bangid")));
		for (CauHoi chtemp : bdg.getCauhois()) {
			if (chtemp.getNoidung().equalsIgnoreCase(
					request.getParameter("noidung"))) {
				return "1";
			}
		}
		for (CauHoi chtemp : bdg.getCauhois()) {
			if (chtemp.getId().equalsIgnoreCase(request.getParameter("id"))) {
				return "2";
			}
		}
		CauHoi ch = new CauHoi();
		if (request.getParameter("id") == "") {
			return "4";	
		}
		ch.setId(request.getParameter("id"));
		if (request.getParameter("noidung") == "") {
			return "5";
		}
		ch.setNoidung(request.getParameter("noidung"));
		ch.setBang(bdg);
		logger.info(request.getParameter("typequestion"));
		LoaiCauHoi lch = lchService.findById(Integer.parseInt(request
				.getParameter("typequestion")));
		bdg.getLchs().add(lch);
		lch.getBdgs().add(bdg);
		ch.setLoaicau(lch);
		lchService.save(lch);
		dgService.save(bdg);
		chService.save(ch);
		logger.info(ch.toString());
		return "3";
	}

	@RequestMapping(value = "question/{id}", method = RequestMethod.POST)
	public String deleteQuestion(@PathVariable("id") String id,
			HttpServletRequest request) throws UnsupportedEncodingException {
		logger.info("Call delete cau hoi");
		CauHoi ch = chService.findById(id);
		
		ch.getBang().getCauhois().remove(ch);
		ch.getLoaicau().getCauhois().remove(ch);
		
		dgService.save(ch.getBang());
		lchService.save(ch.getLoaicau());
		logger.info("Xoa thanh cong");
		return "redirect:/admin?qldg";
	}

	@RequestMapping(value = "question/{id}", params = "updatequestion", method = RequestMethod.POST)
	public String updateQuestion(@PathVariable("id") String id,
			HttpServletRequest request) {
		logger.info("update cau hoi " + id);
		CauHoi ch = chService.findById(id);
		ch.setNoidung(request.getParameter("noidung"));
		// remove it from old loai cau hoi
		LoaiCauHoi lch = lchService.findById(Integer.parseInt(request
				.getParameter("typequestion")));
		ch.getLoaicau().getCauhois().remove(ch);

		// add it into new loai cau hoi
		lch.getCauhois().add(ch);
		ch.setLoaicau(lch);
		chService.save(ch);
		return "redirect:/admin?qldg";
	}

	// load UI set bang danh gia
	@RequestMapping(value = "setdanhgia", method = RequestMethod.GET)
	public String getDanhGia(Model model) {
		logger.info("Set Danh Gia");
		List<BangDanhGia> dslchs = dgService.findAll();
		model.addAttribute("lchs", dslchs);
		return "admin/setdanhgia";
	}

	@RequestMapping(value = "setdanhgia/{id}", method = RequestMethod.POST)
	public String setDanhGia(@PathVariable("id") int id, Model model,
			HttpServletRequest request) {
		String thoigianBD = request.getParameter("timebd");
		String thoigianKT = request.getParameter("timekt");
		DateTimeFormatter dateStringFormat = DateTimeFormat
				.forPattern("yyyy-MM-dd HH:mm:ss");
		time.setTimeBD(dateStringFormat.parseDateTime(thoigianBD));
		time.setTimeKT(dateStringFormat.parseDateTime(thoigianKT));
		BangDanhGia bdg = dgService.findById(id);
		logger.info("Set Danh Gia : " + time.getTimeBD().toString() + " | "
				+ time.getTimeKT().toString());
		choose.setId(id);
		choose.setBgd(bdg);
		return "admin/setdanhgia";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "qluserlist", method = RequestMethod.GET)
	public String getUser() {
		return "admin/qluserlist";
	}

	@RequestMapping(value = "qluserlist", params = "update", method = RequestMethod.POST)
	public String updateUser(HttpServletRequest request, Model model)
			throws ParseException, UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		User user = userService.findById(request.getParameter("id"));
		user.setHoten(request.getParameter("hoten"));
		user.setNoisinh(request.getParameter("noisinh"));
		user.setNgaysinh(new SimpleDateFormat("yyyy-MM-dd").parse(request
				.getParameter("ngaysinh")));
		user.setTypeaccount(Integer.parseInt(request
				.getParameter("loaiaccount")));
		logger.info(user.toString());
		userService.save(user);
		model.addAttribute("info", user);
		// update account sau khi update
		model.addAttribute("user", user);
		return "admin/userinfo";
	}

	// load UI quan ly user
	@RequestMapping(value = "qlusergrid", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	UserGrid listGrid(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order,
			@RequestParam(value = "_search", required = false) boolean isSearch,
			@RequestParam(value = "id", required = false) String id,

			@RequestParam(value = "typeaccount", required = false) Integer typeaccount,
			HttpServletRequest request) throws UnsupportedEncodingException {
		logger.info("Listing contacts for grid with page: {}, rows: {}", page,
				rows);
		logger.info("Listing contacts for grid with sort: {}, order: {}",
				sortBy, order);
		logger.info("is search {}", isSearch);
		request.setCharacterEncoding("UTF-8");
		logger.info("Search field id : {}", id);
		String hoten = request.getParameter("hoten");
		logger.info("search field hoten : {}", hoten);
		logger.info("search field typeacount : {}", typeaccount);

		// id
		if (id == null) {
			id = "%";
		} else {
			id = "%" + id + "%";
		}

		// hoten
		if (hoten == null) {
			hoten = "%";
		} else {
			hoten = "%" + hoten + "%";
		}

		if (typeaccount == null) {
			typeaccount = 0;
		}
		SearchCriteria criteria = new SearchCriteria();
		criteria.setId(id);
		criteria.setHoten(hoten);
		criteria.setTypeacount(typeaccount);

		// Process order by
		Sort sort = null;
		String orderBy = sortBy;
		if (orderBy != null && orderBy.equals("typeaccount"))
			orderBy = "typeaccount";
		if (orderBy != null && order != null) {
			if (order.equals("desc")) {
				sort = new Sort(Sort.Direction.DESC, orderBy);
			} else
				sort = new Sort(Sort.Direction.ASC, orderBy);
		}
		// Constructs page request for current page
		// Note: page number for Spring Data JPA starts with 0, while jqGrid
		// starts with 1

		PageRequest pageRequest = null;
		if (sort != null) {
			pageRequest = new PageRequest(page - 1, rows, sort);
		} else {
			pageRequest = new PageRequest(page - 1, rows);
		}

		/*
		 * Page<User> userPage = null; userPage =
		 * userService.findAllByPage(pageRequest);
		 */

		Page<User> userPage = userService.findUserByCriteria(criteria,
				pageRequest);

		// Construct the grid data that will return as JSON data
		UserGrid userGrid = new UserGrid();
		userGrid.setCurrentPage(userPage.getNumber() + 1);
		userGrid.setTotalPages(userPage.getTotalPages());
		userGrid.setTotalRecords(userPage.getTotalElements());
		userGrid.setUserData(Lists.newArrayList(userPage.iterator()));
		return userGrid;
	}

	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public String getUserInfo(@PathVariable("id") String id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("info", user);
		logger.info("send user ");
		return "admin/userinfo";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "user", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String saveUser(Model model, HttpServletRequest request)
			throws ParseException {
		try {
			if (userService.findById(request.getParameter("id")) != null) {
				// da co user nay.
				model.addAttribute("idtrung", request.getParameter("id"));
				return "User đã tồn tại [id : " + request.getParameter("id")
						+ "]";
			}
			User user = new User();
			user.setId(request.getParameter("id"));
			user.setHoten(request.getParameter("hoten"));
			user.setGioitinh(Boolean.parseBoolean(request
					.getParameter("noisinh")));
			user.setNgaysinh(new SimpleDateFormat("yyyy-MM-dd").parse(request
					.getParameter("ngaysinh")));
			user.setTypeaccount(Integer.parseInt(request
					.getParameter("loaiaccount")));
			userService.save(user);
			model.addAttribute("useradded", user);
			return "Thêm thành công";
		} catch (Exception e) {
			// TODO: handle exception
			return "Không thể thêm được vì " + e.getMessage();
		}
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request,
			@RequestParam CommonsMultipartFile[] fileUpload, Model model)
			throws Exception {
		ArrayList<String> dups = null;
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {
				FileBean fb = new FileBean();
				fb.setFileData(aFile);
				dups = importService.importFile(fb);
			}
		}
		model.addAttribute("dups", dups);
		// returns to the view "Result"
		return "admin/qluserlist";
	}

	// thong bao
	@RequestMapping(value = "qlthongbao", method = RequestMethod.GET)
	public String getThongbao(Model model, HttpServletRequest request) {
		List<ThongBao> tbs = tbService.findAll();
		model.addAttribute("tbs", tbs);
		return "quanlythongbao";
	}

	@RequestMapping(value = "qlthongbao", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String saveThongBao(HttpServletRequest request) throws ParseException {
		if (tbService.findById(Integer.parseInt(request.getParameter("id"))) != null) {
			// da ton tai thong bao nay
			return "Trùng id thông báo , vui lòng nhập ID khác!";
		}
		ThongBao tb = new ThongBao();
		tb.setId(Integer.parseInt(request.getParameter("id")));
		tb.setTenthongbao(request.getParameter("noidung"));
		tb.setNgaytao(new Date());
		tbService.save(tb);
		return "Lưu thành công!";
	}
}
