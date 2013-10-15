package com.nvh.giangvien.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MONHOC")
public class MonHoc implements Serializable {

	private String ID;
	private	String TenMH;
	private Set<ThoiKhoaBieu> thoikhoabieu = new HashSet<ThoiKhoaBieu>();
	@Id
	@Column(name="ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	@Column(name="TENMH")
	public String getTenMH() {
		return TenMH;
	}
	public void setTenMH(String tenMH) {
		TenMH = tenMH;
	}
	
	@OneToMany(mappedBy="monhoc", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ThoiKhoaBieu> getThoikhoabieu() {
		return thoikhoabieu;
	}
	public void setThoikhoabieu(Set<ThoiKhoaBieu> thoikhoabieu) {
		this.thoikhoabieu = thoikhoabieu;
	}
	
}
