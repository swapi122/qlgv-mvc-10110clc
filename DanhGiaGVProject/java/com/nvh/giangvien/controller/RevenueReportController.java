package com.nvh.giangvien.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.nvh.applicationscope.BangDanhGiaChoose;
import com.nvh.giangvien.model.BangDanhGia;
import com.nvh.giangvien.model.BangDanhGiaKq;
import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.CauHoiKq;
import com.nvh.giangvien.model.LoaiCauHoi;
import com.nvh.giangvien.model.MonHoc;
import com.nvh.giangvien.model.ThoiKhoaBieu;
import com.nvh.giangvien.model.User;
import com.nvh.giangvien.service.BangDanhGiaKqService;
import com.nvh.giangvien.service.BangDanhGiaService;
import com.nvh.giangvien.service.MonHocService;
import com.nvh.giangvien.service.ThoiKhoaBieuService;
import com.nvh.giangvien.service.UserService;
import com.nvh.util.DisplayResult;

@Controller
public class RevenueReportController{
	
	private Logger log = LoggerFactory.getLogger(RevenueReportController.class);

	@Autowired
	private MonHocService mhService;
	@Autowired
	private ThoiKhoaBieuService tkbService;
	@Autowired
	private BangDanhGiaKqService dgkqService;
	@Autowired
	private BangDanhGiaService bdgService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private BangDanhGiaChoose choose;

	@RequestMapping(value = "/download" , method= RequestMethod.GET)
	protected void handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String iddg = request.getParameter("iddg");
		String mhh = request.getParameter("mh");
		String iduser = request.getParameter("iduser");
		User user = userService.findById(iduser);
		log.info(iddg + "|" + mhh);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",": attachment; filename=\"baocao.xls\"");
		ServletOutputStream os = response.getOutputStream();
		
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFSheet worksheet = wb.createSheet("DanhGia");
		worksheet.setColumnWidth(0, (20 * 230));
		worksheet.setColumnWidth(2, (20 * 128));
		worksheet.setColumnWidth(1, (30 * 728));
		worksheet.setColumnWidth(3, (20 * 128));
		worksheet.setColumnWidth(4, (20 * 128));
		worksheet.setColumnWidth(5, (20 * 128));
		HSSFRow row = worksheet.createRow(0);

		HSSFRichTextString value = new HSSFRichTextString("Đánh giá giảng viên");
		value.applyFont(font);
		row.createCell(0).setCellValue(value);

		row = worksheet.createRow(1);
		row.createCell(0).setCellValue(
				new HSSFRichTextString("Khoa đào tạo chất lượng cao"));

		MonHoc mh = mhService.findById(mhh);
		
		worksheet.createRow(2);

		row= worksheet.createRow(4);
		row.createCell(0).setCellValue(new HSSFRichTextString("Giảng viên : "));
		row.createCell(1).setCellValue(new HSSFRichTextString(user.getHoten()));
		
		row= worksheet.createRow(5);
		row.createCell(0).setCellValue(new HSSFRichTextString("Môn giảng dạy : "));
		row.createCell(1).setCellValue(new HSSFRichTextString(mh.getTenMH()));
		
		worksheet.createRow(6);
		
		row = worksheet.createRow(7);

		HSSFCellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(HSSFColor.GREEN.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		font = wb.createFont();
		font.setColor(HSSFColor.WHITE.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		value = new HSSFRichTextString("Mã Câu");
		value.applyFont(font);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(value);
		cell.setCellStyle(style);

		value = new HSSFRichTextString("Nội Dung");
		value.applyFont(font);
		cell = row.createCell(1);
		cell.setCellValue(value);
		cell.setCellStyle(style);

		value = new HSSFRichTextString("Kết Quả A");
		value.applyFont(font);
		cell = row.createCell(2);
		cell.setCellValue(value);
		cell.setCellStyle(style);

		value = new HSSFRichTextString("Kết Quả B");
		value.applyFont(font);
		cell = row.createCell(3);
		cell.setCellValue(value);
		cell.setCellStyle(style);

		value = new HSSFRichTextString("Kết Quả C");
		value.applyFont(font);
		cell = row.createCell(4);
		cell.setCellValue(value);
		cell.setCellStyle(style);

		value = new HSSFRichTextString("Kết quả D");
		value.applyFont(font);
		cell = row.createCell(5);
		cell.setCellValue(value);
		cell.setCellStyle(style);

		int rowIndex = 8;
		BangDanhGia bdg;
		if(Integer.parseInt(request.getParameter("iddg")) == choose.getId()){
			bdg = choose.getBgd();
		}else{
			bdg = bdgService.findById(Integer.parseInt(request.getParameter("iddg")));
		}
		List<LoaiCauHoi> lchs = new ArrayList<LoaiCauHoi>(bdg.getLchs());
		Collections.sort(lchs);

		List<ThoiKhoaBieu> tkbs = tkbService.findByMonhoc(mh);
		List<BangDanhGiaKq> dgkqs = new ArrayList<BangDanhGiaKq>();
		for (ThoiKhoaBieu thoiKhoaBieu : tkbs) {
			if(thoiKhoaBieu.getDgkq()!= null){ dgkqs.add(thoiKhoaBieu.getDgkq());}
		}

		for (CauHoi cauHoi : bdg.getCauhois()) {
			row = worksheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(
					new HSSFRichTextString(cauHoi.getId()));
			row.createCell(1).setCellValue(
					new HSSFRichTextString(cauHoi.getNoidung()));
			int a = 0, b = 0, c = 0, d = 0;
			for (BangDanhGiaKq bangDanhGiaKq : dgkqs) {
				if (bangDanhGiaKq.getLoaiBang().getId() == bdg.getId()) {
					for (CauHoiKq chkq : bangDanhGiaKq.getCauhoikqs()) {
						if (chkq.getCauhoi().getId().equals(cauHoi.getId())) {
							switch (chkq.getKetqua()) {
							case 'A':
								a++;
								break;
							case 'B':
								b++;
								break;
							case 'C':
								c++;
								break;
							case 'D':
								d++;
								break;
							}
						}
					}
				}
			}
			row.createCell(2).setCellValue(((double) a / dgkqs.size()) * 100 + "%");
			row.createCell(3).setCellValue(((double) b / dgkqs.size()) * 100 + "%");
			row.createCell(4).setCellValue(((double) c / dgkqs.size()) * 100 + "%");
			row.createCell(5).setCellValue(((double) d / dgkqs.size()) * 100 + "%");
		}
		wb.write(os);
	}

}
