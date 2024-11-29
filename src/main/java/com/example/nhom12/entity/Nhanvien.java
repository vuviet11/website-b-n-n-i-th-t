package com.example.nhom12.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbnhanvien")
public class Nhanvien {

	@Id
	@Column(name = "MANV", nullable = false)
	private String maNV;
	
	@Column(name = "TENNV")
	private String tenNV;
	
	@Column(name = "DIACHINV")
	private String diaChiNV;
	
	@Column(name = "SDT")
	private int sdt;
	
	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getDiaChiNV() {
		return diaChiNV;
	}

	public void setDiaChiNV(String diaChiNV) {
		this.diaChiNV = diaChiNV;
	}

	public int getSdt() {
		return sdt;
	}

	public void setSdt(int sdt) {
		this.sdt = sdt;
	}
	
}
