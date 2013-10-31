package com.nvh.giangvien.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Qualifier;

@Entity
@Table(name = "USER")
@NamedQueries({
	@NamedQuery(name = "User.findTKBForSV", query = "select distinct u from User u left join fetch u.thoikhoabieuSV where u.id = :id")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private boolean gioitinh;
	private String hoten;
	private Date ngaysinh;
	private String noisinh;
	private int typeaccount;
	private int version;
	private Set<ThoiKhoaBieu> thoikhoabieuSV = new HashSet<ThoiKhoaBieu>();
	private Set<ThoiKhoaBieu> lichdayGV = new HashSet<ThoiKhoaBieu>();
	
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

	@OneToMany(mappedBy="sv" , cascade =CascadeType.ALL, orphanRemoval = true)
	public Set<ThoiKhoaBieu> getThoikhoabieuSV() {
		return thoikhoabieuSV;
	}

	public void setThoikhoabieuSV(Set<ThoiKhoaBieu> thoikhoabieuSV) {
		this.thoikhoabieuSV = thoikhoabieuSV;
	}

	@OneToMany(mappedBy="gv", cascade = CascadeType.ALL , orphanRemoval = true)
	public Set<ThoiKhoaBieu> getLichdayGV() {
		return lichdayGV;
	}

	public void setLichdayGV(Set<ThoiKhoaBieu> lichdayGV) {
		this.lichdayGV = lichdayGV;
	}
	
}
