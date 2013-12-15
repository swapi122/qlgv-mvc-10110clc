package com.nvh.giangvien.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private boolean gioitinh;
	private String password;
	private String hoten;
	private Date ngaysinh;
	private String noisinh;
	private int typeaccount;
	private int version;
	private Set<ThoiKhoaBieu> thoikhoabieuSV = new HashSet<ThoiKhoaBieu>();
	private Set<ThoiKhoaBieu> lichdayGV = new HashSet<ThoiKhoaBieu>();
	private Set<MonHoc> mhs = new HashSet<MonHoc>();
	@Id
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "GIOITINH")
	public boolean getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}

	@Column(name = "HOTEN")
	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NGAYSINH")
	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	@Column(name = "NOISINH")
	public String getNoisinh() {
		return noisinh;
	}

	public void setNoisinh(String noisinh) {
		this.noisinh = noisinh;
	}

	@Column(name = "TYPEACCOUNT")
	public int getTypeaccount() {
		return typeaccount;
	}

	public void setTypeaccount(int typeaccount) {
		this.typeaccount = typeaccount;
	}

	@Version
	@Column(name = "VERSION")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "giangvien_monhoc" , 
		joinColumns = @JoinColumn(name="IDGV") , 
		inverseJoinColumns = @JoinColumn(name = "IDMH"))
	public Set<MonHoc> getMhs() {
		return mhs;
	}

	public void setMhs(Set<MonHoc> mhs) {
		this.mhs = mhs;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", gioitinh=" + gioitinh + ", password="
				+ password + ", hoten=" + hoten + ", ngaysinh=" + ngaysinh
				+ ", noisinh=" + noisinh + ", typeaccount=" + typeaccount
				+ ", version=" + version + "]";
	}

	
}
