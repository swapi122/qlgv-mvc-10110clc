package com.nvh.giangvien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GiangVienController {

	@RequestMapping(value = "/giangvien", method = RequestMethod.GET)
	public String logined() {
		return "giangvien";
	}

}
