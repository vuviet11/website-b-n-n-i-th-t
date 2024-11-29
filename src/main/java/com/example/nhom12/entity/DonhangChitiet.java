package com.example.nhom12.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbdonhang_chitiet")
public class DonhangChitiet {
	@Id
	@Column(name = "STT", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stt;
	
	@Column(name = "MADH")
	private int madh;
	
	@Column(name = "MASP")
	private String masp;
	
	@Column(name = "SOLUONG")
	private int sl;

	@ManyToOne
	@JoinColumn(name = "madh", referencedColumnName = "MADH",insertable = false, updatable = false)
    private Donhang donhang;
	@ManyToOne
	@JoinColumn(name = "masp", referencedColumnName = "MASP",insertable = false, updatable = false)
    private Sanpham sanpham;
	
	
	public Sanpham getSanpham() {
		return sanpham;
	}
	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public int getMadh() {
		return madh;
	}
	public void setMadh(int madh) {
		this.madh = madh;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	public int getSl() {
		return sl;
	}
	public void setSl(int sl) {
		this.sl = sl;
	}
	public Donhang getDonhang() {
		return donhang;
	}
	public void setDonhang(Donhang donhang) {
		this.donhang = donhang;
	}
}
