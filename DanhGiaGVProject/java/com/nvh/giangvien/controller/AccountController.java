package com.nvh.giangvien.controller;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.UserService;

@Controller
public class AccountController {
	public static final String ACCOUNT_ATTRIBUTE = "account";
	final Logger logger = LoggerFactory.getLogger(AccountController.class);
	// controller dang nhap va dang xuat
	@Autowired
	private UserService userService;

	@RequestMapping(value = "logined" , method = RequestMethod.GET)
	public String home(HttpSession model){
		User user = userService.findById(SecurityContextHolder.getContext().getAuthentication().getName());
		model.setAttribute(ACCOUNT_ATTRIBUTE, user);
		String weblink = "redirect:/";
		switch (user.getTypeaccount()) {
		case 0:
			weblink += "sinhvien";
			break;
		case 1:
			weblink += "giangvien";
			break;
		case 2:
			weblink += "manager";
			break;
		case 3:
			weblink += "admin";
			break;
		}
		return weblink;

	}

	@RequestMapping(value = "/login")
	public String login(Model model, @RequestParam(required=false) String message) {
		return "login";
	}

	@RequestMapping(value = "/denied")
	public String denied(){
		return "denied";
	}
	
	@RequestMapping(value = "/login/failure")
	public String loginFailure(){
		String message = "Đang nhập thất bại";
		return "redirect:/login?message=" +message;
	}
	
	@RequestMapping(value = "/logout/success")
	public String logoutSuccess(){
		String message = "Đang nhập thất bại";
		return "logoutSuccess";
	}
	
	@RequestMapping(value="/sinhvien")
	public String getUserPage() {
		return "sinhvien";
	}
	
	@RequestMapping(value="/admin")
	public String getAdminPage() {
		return "admin";
	}
	
	@RequestMapping(value="/giangvien")
	public String getGVPage() {
		return "giangvien";
	}
	
	@RequestMapping(value="/manager")
	public String getManagerPage() {
		return "manager";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String handlerLogout(HttpServletRequest request) {
		request.getSession(false).invalidate();
		return "login";
	}
}
