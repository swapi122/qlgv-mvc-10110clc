package com.nvh.giangvien.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LOAICAUHOI")
public class LoaiCauHoi implements Serializable{
	private int id;
	private String tenloai;
	private Set<CauHoi> cauhois = new HashSet<CauHoi>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="TENLOAI")
	public String getTenloai() {
		return tenloai;
	}

	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}

	@OneToMany(mappedBy = "loaicau" , cascade = CascadeType.ALL , orphanRemoval = true)
	public Set<CauHoi> getCauhois() {
		return cauhois;
	}

	public void setCauhois(Set<CauHoi> cauhois) {
		this.cauhois = cauhois;
	}

}
