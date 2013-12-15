package com.nvh.giangvien.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="thongbao")
public class ThongBao {

	private int id;
	private String tenthongbao;
	private Date ngaytao;
	
	@Id
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "tenthongbao")
	public String getTenthongbao() {
		return tenthongbao;
	}
	public void setTenthongbao(String tenthongbao) {
		this.tenthongbao = tenthongbao;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "time")
	public Date getNgaytao() {
		return ngaytao;
	}
	public void setNgaytao(Date ngaytao) {
		this.ngaytao = ngaytao;
	}
	
	
}
