package com.nvh.util;

import com.nvh.giangvien.model.CauHoi;

public class DisplayResult {

	private String mch;
	private double numA;
	private double numB;
	private double numC;
	private double numD;
	private CauHoi ch;
	
	public double getNumA() {
		return numA;
	}
	public void setNumA(double numA) {
		this.numA = numA;
	}
	public double getNumB() {
		return numB;
	}
	public void setNumB(double numB) {
		this.numB = numB;
	}
	public double getNumC() {
		return numC;
	}
	public void setNumC(double numC) {
		this.numC = numC;
	}
	public double getNumD() {
		return numD;
	}
	public void setNumD(double numD) {
		this.numD = numD;
	}
	public CauHoi getCh() {
		return ch;
	}
	public void setCh(CauHoi ch) {
		this.ch = ch;
	}

	
	
	
	@Override
	public String toString() {
		return "DisplayResult [mch=" + mch + ", numA=" + numA + ", numB="
				+ numB + ", numC=" + numC + ", numD=" + numD + "]";
	}
	public DisplayResult() {
		super();
		// TODO Auto-generated constructor stub
		mch = "";
		numA = 0;
		numB = 0;
		numC = 0;
		numD = 0;
	}

	public String getMch() {
		return mch;
	}
	public void setMch(String mch) {
		this.mch = mch;
	}

	
}
