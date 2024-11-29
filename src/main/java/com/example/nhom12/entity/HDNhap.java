package com.example.nhom12.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tbhoadon_nhap")
public class HDNhap {
	@Id
	@Column(name = "MAHDN", nullable = false)
	private String mahdn;
	
	@ManyToOne
	@JoinColumn(name = "MANV", referencedColumnName = "MANV")
	private Nhanvien nhanvien;
	
	@Column(name = "NGAYNHAP")
	private Date ngaynhap;
	
	@OneToMany(mappedBy = "hdnhap", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HDNhapChiTiet> hdnhapchitiets = new ArrayList<HDNhapChiTiet>();
	
	public HDNhap() {
	}

	public HDNhap(String mahdn, Nhanvien nhanvien, Date ngaynhap, List<HDNhapChiTiet> hdnhapchitiets) {
		this.mahdn = mahdn;
		this.nhanvien = nhanvien;
		this.ngaynhap = ngaynhap;
		this.hdnhapchitiets = hdnhapchitiets;
	}
	
	public String getMahdn() {
		return mahdn;
	}

	public void setMahdn(String mahdn) {
		this.mahdn = mahdn;
	}

	public Nhanvien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public Date getNgaynhap() {
		return ngaynhap;
	}

	public void setNgaynhap(Date ngaynhap) {
		this.ngaynhap = ngaynhap;
	}
	
	public List<HDNhapChiTiet> getHdnhapchitiets() {
		return hdnhapchitiets;
	}

	public void setHdnhapchitiets(List<HDNhapChiTiet> hdnhapchitiets) {
		this.hdnhapchitiets = hdnhapchitiets;
	}
}
