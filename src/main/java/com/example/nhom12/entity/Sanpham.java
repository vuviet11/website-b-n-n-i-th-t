package com.example.nhom12.entity;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "tbsanpham")
public class Sanpham {
	@Id
    @Column(name = "MASP", nullable = false)
	private String masp;
	@Column(name = "TENSP")
	private String tensp;
	@Column(name = "IMG")
	private String img;
	@Column(name = "LOAISP")
	private String loaisp;
	@Column(name = "SOLUONG")
	private Integer soluong;
	@Column(name = "GIANHAP")
	private Float gianhap;
	@Column(name = "GIABAN")
	private Float giaban;
	@Column(name = "NOIDUNG")
	private String noidung;
	@OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<DonhangChitiet> dssp;
	
	
	public List<DonhangChitiet> getDssp() {
		return dssp;
	}
	public void setDssp(List<DonhangChitiet> dssp) {
		this.dssp = dssp;
	}
	public Sanpham() {
		
	}
	public Sanpham(String masp,String tensp,String img, String loaisp,Integer soluong, Float gianhap,Float giaban,String noidung) {
		this.masp = masp;
		this.tensp = tensp;
		this.img = img;
		this.loaisp = loaisp;
		this.soluong = soluong;
		this.gianhap = gianhap;
		this.giaban = giaban;
		this.noidung = noidung;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLoaisp() {
		return loaisp;
	}
	public void setLoaisp(String loaisp) {
		this.loaisp = loaisp;
	}
	public Integer getSoluong() {
		return soluong;
	}
	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}
	public Float getGianhap() {
		return gianhap;
	}
	public void setGianhap(Float gianhap) {
		this.gianhap = gianhap;
	}
	public Float getGiaban() {
		return giaban;
	}
	public void setGiaban(Float giaban) {
		this.giaban = giaban;
	}
	public String getNoidung() {
		return noidung;
	}
	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
}
