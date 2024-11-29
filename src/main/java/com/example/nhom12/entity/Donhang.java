package com.example.nhom12.entity;

import java.util.Date;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbdonhang")
public class Donhang {
	@Id
	@Column(name = "MADH", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int madh;
	
	@Column(name = "MAKH")
	private int makh;
	
	@Column(name = "NGAY")
	private Date ngay;
	@Column(name = "TINHTRANG")
	private int tt;
	
	@OneToMany(mappedBy = "donhang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<DonhangChitiet> dhctList;
	
	public int getMadh() {
		return madh;
	}

	public void setMadh(int madh) {
		this.madh = madh;
	}

	public int getMakh() {
		return makh;
	}

	public void setMakh(int makh) {
		this.makh = makh;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public int getTt() {
		return tt;
	}

	public void setTt(int tt) {
		this.tt = tt;
	}

	public List<DonhangChitiet> getDhctList() {
		return dhctList;
	}

	public void setDhctList(List<DonhangChitiet> dhctList) {
		this.dhctList = dhctList;
	}

}
