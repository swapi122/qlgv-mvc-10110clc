package com.nvh.giangvien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nvh.util.LoaiCauHoiComparator;

@Entity
@Table(name = "LOAICAUHOI")
public class LoaiCauHoi implements Serializable, Comparable<LoaiCauHoi>{
	private int id;
	private String tenloai;
	private Set<CauHoi> cauhois = new HashSet<CauHoi>();
	private Set<BangDanhGia> bdgs = new HashSet<BangDanhGia>();
	
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

	@OneToMany(mappedBy = "loaicau" , cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.EAGER)
	public Set<CauHoi> getCauhois() {
		return cauhois;
	}

	public void setCauhois(Set<CauHoi> cauhois) {
		this.cauhois = cauhois;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "bdg_lch" , 
		joinColumns = @JoinColumn(name= "IDLCH") , 
		inverseJoinColumns = @JoinColumn(name= "IDDGB"))
	public Set<BangDanhGia> getBdgs() {
		return bdgs;
	}

	public void setBdgs(Set<BangDanhGia> bdgs) {
		this.bdgs = bdgs;
	}

	@Override
	public String toString() {
		return "LoaiCauHoi [id=" + id + ", tenloai=" + tenloai + ", cauhois="
				+ cauhois + "]";
	}

	@Override
	public int compareTo(LoaiCauHoi o) {
		// TODO Auto-generated method stub
		return getId() - o.getId();
	}

	
}
