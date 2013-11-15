package com.nvh.util;

import java.util.Comparator;

import com.nvh.giangvien.model.CauHoi;
import com.nvh.giangvien.model.LoaiCauHoi;

public class LoaiCauHoiComparator implements Comparator<LoaiCauHoi> {

	@Override
	public int compare(LoaiCauHoi arg0, LoaiCauHoi arg1) {
		// TODO Auto-generated method stub
		return arg0.getId() - arg1.getId();
	}

}
