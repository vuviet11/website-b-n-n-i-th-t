package com.example.nhom12.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbdoanhthu")
public class Doanhthu {
	@Id
	@Column(name = "MADT", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int madt;
	
	@Column(name = "TIEN1N")
	private Float tien1n;
	@Column(name = "NGAY")
	private Date ngay;
	
	public int getMadt() {
		return madt;
	}

	public void setMadt(int madt) {
		this.madt = madt;
	}

	public Float getTien1n() {
		return tien1n;
	}

	public void setTien1n(Float tien1n) {
		this.tien1n = tien1n;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
}
