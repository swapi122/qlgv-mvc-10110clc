package com.nvh.giangvien.service.jpa;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvh.giangvien.model.FileBean;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.ImportService;
import com.nvh.giangvien.service.UserService;

@Service
public class ImportServiceImpl implements ImportService {

	@Autowired
	private UserService userService;

	@Override
	public ArrayList<String> importFile(FileBean fileBean) {
		// TODO Auto-generated method stub

		ByteArrayInputStream bis = new ByteArrayInputStream(fileBean
				.getFileData().getBytes());
		Workbook workbook = null;
		ArrayList<String> dup = new ArrayList<String>();
		try {
			if (fileBean.getFileData().getOriginalFilename().endsWith("xls")) {
				workbook = new HSSFWorkbook(bis);
			} else if (fileBean.getFileData().getOriginalFilename()
					.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(bis);
			} else {
				throw new IllegalArgumentException(
						"Received file does not have a standard excel extension.");
			}
			// Get first sheet from the workbook
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				try {
					if (userService.findById(row.getCell(0).getStringCellValue()) != null) {
						// da co user co id do.
						dup.add(row.getCell(0).getStringCellValue());
					} else {
						User user = new User();
						user.setId(row.getCell(0).getStringCellValue());
						user.setHoten(row.getCell(1).getStringCellValue());
						user.setNgaysinh(row.getCell(2).getDateCellValue());
						user.setGioitinh(row.getCell(3).getBooleanCellValue());
						user.setNoisinh(row.getCell(4).getStringCellValue());
						user.setTypeaccount((int) row.getCell(5)
								.getNumericCellValue());
						user.setPassword(row.getCell(0).getStringCellValue());
						userService.save(user);
					}	
				} catch (Exception e) {
					// TODO: handle exception
					ArrayList<String> error = new ArrayList<String>();
					error.add("Cấu trúc file Excel không đúng! Vui lòng nhập lại");
					return error;
				}
			}
			return dup;
		} catch (IOException e) {
			ArrayList<String> error = new ArrayList<String>();
			error.add("Cấu trúc file Excel không đúng! Vui lòng nhập lại");
			return error;
		}
	}

}
