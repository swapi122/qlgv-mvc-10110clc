package com.nvh.giangvien.service;

import java.util.ArrayList;

import com.nvh.giangvien.model.FileBean;

public interface ImportService {
	public ArrayList<String> importFile(FileBean fileBean);
}
