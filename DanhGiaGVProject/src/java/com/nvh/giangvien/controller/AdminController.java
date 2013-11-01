package com.nvh.giangvien.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.nvh.applicationscope.BangDanhGiaChoose;
import com.nvh.applicationscope.UserGrid;
import com.nvh.giangvien.model.BangDanhGia;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.LoaiCauHoi;
import com.nvh.giangvien.model.SearchCriteria;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.CauHoiService;
import com.nvh.giangvien.service.LoaiCauHoiService;
import com.nvh.giangvien.service.UserService;

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

	@Autowired
	private UserService userService;

	@Autowired
	private BangDanhGiaChoose choose;

	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "admin";
	}

	@RequestMapping(params = "qldg", method = RequestMethod.GET)
	public String quanlyDanhGia(HttpSession session) {
		List<BangDanhGia> dgs = dgService.findAll();
		logger.info(dgs.toString() + "");
		session.setAttribute("danhsachdg", dgs);
		return "admin/qldanhgia";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showBang(@PathVariable("id") int id, Model model) {
		logger.info("Get " + id);
		BangDanhGia bdg = dgService.findById(id);
		List<LoaiCauHoi> lchs = lchService.findAll();
		model.addAttribute("bangdanhgia", bdg);
		model.addAttribute("dslch", lchs);
		return "admin/showbang";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String deleteBang(@PathVariable("id") int id, Model model) {
		logger.info("Xoa " + id);
		BangDanhGia bdg = dgService.findById(id);
		bdg.getBangkqs().clear();
		bdg.getCauhois().clear();
		dgService.delete(bdg);
		return "redirect:/admin?qldg";
	}

	@RequestMapping(value = "/{id}", params = "update", method = RequestMethod.POST)
	public String updateBang(@PathVariable("id") int id,
			HttpServletRequest request) {
		logger.info("Update " + id);
		BangDanhGia bdg = dgService.findById(id);
		bdg.setTenbang(request.getParameter("noidung"));
		dgService.save(bdg);
		return "redirect:/admin?qldg";
	}

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

	@RequestMapping(value = "question", method = RequestMethod.POST)
	public String getQuestion(HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf8");
		logger.info("Call tao cau hoi");
		CauHoi ch = new CauHoi();
		ch.setId(request.getParameter("id"));
		ch.setNoidung(request.getParameter("noidung"));
		BangDanhGia bdg = dgService.findById(Integer.parseInt(request
				.getParameter("bangid")));
		ch.setBang(bdg);
		logger.info(request.getParameter("typequestion"));
		LoaiCauHoi lch = lchService.findById(Integer.parseInt(request
				.getParameter("typequestion")));
		ch.setLoaicau(lch);
		chService.save(ch);
		logger.info(ch.toString());
		return "redirect:/admin?qldg";
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
	public String setDanhGia(@PathVariable("id") int id, Model model) {
		logger.info("Set Danh Gia");
		choose.setId(id);
		return "admin/setdanhgia";
	}

	@RequestMapping(value = "qluserlist", method = RequestMethod.GET)
	public String getUser() {
		return "admin/qluserlist";
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
			@RequestParam(value = "hoten", required = false) String hoten,
			@RequestParam(value = "typeaccount", required = false) Integer typeaccount)
			throws UnsupportedEncodingException {
		logger.info("Listing contacts for grid with page: {}, rows: {}", page,
				rows);
		logger.info("Listing contacts for grid with sort: {}, order: {}",
				sortBy, order);
		logger.info("is search {}", isSearch);
		logger.info("Search field id : {}", id);
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
}
