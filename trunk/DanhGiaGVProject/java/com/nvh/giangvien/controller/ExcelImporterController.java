package com.nvh.giangvien.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nvh.giangvien.model.FileBean;
import com.nvh.giangvien.service.ImportService;

@Controller
@RequestMapping("/upload")
public class ExcelImporterController {

	@Autowired
	private ImportService importService;
	
    @RequestMapping(method = RequestMethod.POST)
    public String upload(FileBean uploadItem, BindingResult result) {
        importService.importFile(uploadItem);
        return "import/importDone";
    }

}