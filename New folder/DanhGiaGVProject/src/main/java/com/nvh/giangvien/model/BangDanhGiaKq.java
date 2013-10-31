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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BANGDANHGIA_KQ")
public class BangDanhGiaKq implements Serializable{

	private int id;
	private ThoiKhoaBieu monhocdg;
	private BangDanhGia loaiBang;
	private Set<CauHoiKq> cauhoikqs = new HashSet<CauHoiKq>();
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name="ID")
	public ThoiKhoaBieu getMonhocdg() {
		return monhocdg;
	}

	public void setMonhocdg(ThoiKhoaBieu monhocdg) {
		this.monhocdg = monhocdg;
	}

	@ManyToOne
	@JoinColumn(name ="IDBDG")
	public BangDanhGia getLoaiBang() {
		return loaiBang;
	}

	public void setLoaiBang(BangDanhGia loaiBang) {
		this.loaiBang = loaiBang;
	}

	@OneToMany(mappedBy="bangkq" ,cascade = CascadeType.ALL , orphanRemoval = true)
	public Set<CauHoiKq> getCauhoikqs() {
		return cauhoikqs;
	}

	public void setCauhoikqs(Set<CauHoiKq> cauhoikqs) {
		this.cauhoikqs = cauhoikqs;
	}
	
}
