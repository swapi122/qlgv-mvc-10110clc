package com.nvh.giangvien.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "THOIKHOABIEU")
public class ThoiKhoaBieu implements Serializable {
	
	private int id;
	private User sv;
	private User gv;
	private MonHoc monhoc;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="IDSV")
	public User getSv() {
		return sv;
	}

	public void setSv(User sv) {
		this.sv = sv;
	}

	@ManyToOne
	@JoinColumn(name="IDGV")
	public User getGv() {
		return gv;
	}

	public void setGv(User gv) {
		this.gv = gv;
	}

	@ManyToOne
	@JoinColumn(name="MONHOC_ID")
	public MonHoc getMonhoc() {
		return monhoc;
	}

	public void setMonhoc(MonHoc monhoc) {
		this.monhoc = monhoc;
	}


	
}
