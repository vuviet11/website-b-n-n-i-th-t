package com.example.nhom12.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbhoadon_nhap_chitiet")
public class HDNhapChiTiet {

	@Id
	@Column(name = "STT", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stt;
	
	@ManyToOne
	@JoinColumn(name = "MAHDNHAP", referencedColumnName = "MAHDN")
	private HDNhap hdnhap;
	
	@ManyToOne
    @JoinColumn(name = "MASP", referencedColumnName = "MASP")
    private Sanpham sanpham;
	
	@Column(name = "SOLUONG")
	private int soluong;
	
	@Column(name = "GIANHAP")
	private float gianhap;
	
	public HDNhapChiTiet() {
		
	}

	public HDNhapChiTiet(int stt, HDNhap hdnhap, Sanpham sanpham, int soluong, float gianhap) {
		super();
		this.stt = stt;
		this.hdnhap = hdnhap;
		this.sanpham = sanpham;
		this.soluong = soluong;
		this.gianhap = gianhap;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public HDNhap getHdnhap() {
		return hdnhap;
	}

	public void setHdnhap(HDNhap hdnhap) {
		this.hdnhap = hdnhap;
	}
	
	public Sanpham getSanpham() {
		return sanpham;
	}

	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}
	
	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public float getGianhap() {
		return gianhap;
	}

	public void setGianhap(float gianhap) {
		this.gianhap = gianhap;
	}
}
