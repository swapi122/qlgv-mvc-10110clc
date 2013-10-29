package com.nvh.giangvien.controller;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.UserService;

@Controller
@RequestMapping("/login")
public class AccountController {
	public static final String ACCOUNT_ATTRIBUTE = "account";
	final Logger logger = LoggerFactory.getLogger(AccountController.class);
	// controller dang nhap va dang xuat
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String handleLogin(@RequestParam String username,
			@RequestParam String password, RedirectAttributes redirect,
			HttpServletRequest request){
		try {
			User user = userService.findById(username);
			request.getSession().setAttribute(ACCOUNT_ATTRIBUTE, user);
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
		} catch (Exception e) {
			// TODO: handle exception
			redirect.addFlashAttribute("exception", e); 
			return "redirect:/login"; 
		}
		
	}

	@RequestMapping(params = "logout", method = RequestMethod.GET)
	public String handlerLogout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
