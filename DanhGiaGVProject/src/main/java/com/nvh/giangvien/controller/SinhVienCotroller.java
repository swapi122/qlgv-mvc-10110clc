package com.nvh.giangvien.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienCotroller {

	@RequestMapping(method = RequestMethod.GET)
	public String logined(){
		return "sinhvien";
	}
	
}
