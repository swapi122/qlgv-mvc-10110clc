package com.nvh.giangvien.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CAUHOI")
public class CauHoi implements Serializable{
	private String id;
	private String noidung;
	private BangDanhGia bang;
	private Set<CauHoiKq> kqs = new HashSet<CauHoiKq>();
	private LoaiCauHoi loaicau;
	
	@Id
	@Column(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="NOIDUNG")
	public String getNoidung() {
		return noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	@ManyToOne
	@JoinColumn(name= "BANGDANHGIA_ID")
	public BangDanhGia getBang() {
		return bang;
	}

	public void setBang(BangDanhGia bang) {
		this.bang = bang;
	}
	
	@OneToMany(mappedBy = "cauhoi" , cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<CauHoiKq> getKqs() {
		return kqs;
	}

	public void setKqs(Set<CauHoiKq> kqs) {
		this.kqs = kqs;
	}

	@ManyToOne
	@JoinColumn(name ="LOAICAUHOI_ID")
	public LoaiCauHoi getLoaicau() {
		return loaicau;
	}

	public void setLoaicau(LoaiCauHoi loaicau) {
		this.loaicau = loaicau;
	}

	
}
