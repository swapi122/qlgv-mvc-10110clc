package com.nvh.giangvien.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="monhoc")
public class MonHoc implements Serializable {

	private String ID;
	private	String tenMH;
	private Set<ThoiKhoaBieu> thoikhoabieu = new HashSet<ThoiKhoaBieu>();
	private Set<User> gvs = new HashSet<User>();
	
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
		return tenMH;
	}
	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}
	
	@OneToMany(mappedBy="monhoc", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ThoiKhoaBieu> getThoikhoabieu() {
		return thoikhoabieu;
	}
	public void setThoikhoabieu(Set<ThoiKhoaBieu> thoikhoabieu) {
		this.thoikhoabieu = thoikhoabieu;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "giangvien_monhoc" , 
		joinColumns = @JoinColumn(name="IDMH") , 
		inverseJoinColumns = @JoinColumn(name = "IDGV"))
	public Set<User> getGvs() {
		return gvs;
	}
	public void setGvs(Set<User> gvs) {
		this.gvs = gvs;
	}
	
	
}
