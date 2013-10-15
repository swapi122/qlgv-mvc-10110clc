package com.nvh.giangvien.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="BANGDANHGIA")
public class BangDanhGia implements Serializable {

	private int id;
	private String tenbang;
	private Set<BangDanhGiaKq> bangkqs = new HashSet<BangDanhGiaKq>();
	private Set<CauHoi> cauhois = new HashSet<CauHoi>();
	private Date ngaytao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="TENBANG")
	public String getTenbang() {
		return tenbang;
	}
	
	public void setTenbang(String tenbang) {
		this.tenbang = tenbang;
	}
	
	@OneToMany(mappedBy = "loaiBang" , cascade = CascadeType.ALL , orphanRemoval = true , fetch = FetchType.EAGER)
	public Set<BangDanhGiaKq> getBangkqs() {
		return bangkqs;
	}

	public void setBangkqs(Set<BangDanhGiaKq> bangkqs) {
		this.bangkqs = bangkqs;
	}

	@OneToMany(mappedBy = "bang" , cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<CauHoi> getCauhois() {
		return cauhois;
	}

	public void setCauhois(Set<CauHoi> cauhois) {
		this.cauhois = cauhois;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "NGAYTAO")
	public Date getNgaytao() {
		return ngaytao;
	}

	public void setNgaytao(Date ngaytao) {
		this.ngaytao = ngaytao;
	}

	
}
