package com.nvh.giangvien.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CAUHOI_KQ")
public class CauHoiKq {
	private int id;
	private char ketqua;
	private BangDanhGiaKq bangkq;
	private CauHoi cauhoi;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="KETQUA")
	public char getKetqua() {
		return ketqua;
	}
	public void setKetqua(char ketqua) {
		this.ketqua = ketqua;
	}
	
	@ManyToOne
	@JoinColumn(name="BANGDANHGIA_KQ_ID")
	public BangDanhGiaKq getBangkq() {
		return bangkq;
	}
	public void setBangkq(BangDanhGiaKq bangkq) {
		this.bangkq = bangkq;
	}
	
	@ManyToOne
	@JoinColumn(name ="IDCH")
	public CauHoi getCauhoi() {
		return cauhoi;
	}
	public void setCauhoi(CauHoi cauhoi) {
		this.cauhoi = cauhoi;
	}
	
}
