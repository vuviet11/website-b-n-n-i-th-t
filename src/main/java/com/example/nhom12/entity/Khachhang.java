package com.example.nhom12.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
@Entity
@Table(name = "tbkhachhang")
public class Khachhang {
	@Id
	@Column(name = "MAKH", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int makh;
	
	@Column(name = "TENKH")
	private String tenkh;
	
	@Column(name = "DIACHIKH")
	private String diachikh;
	
	@Column(name = "SDT")
	private int sdt;
	
	@OneToMany(mappedBy = "khachhang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<Taikhoan> dstk;

	public List<Taikhoan> getDstk() {
		return dstk;
	}

	public void setDstk(List<Taikhoan> dstk) {
		this.dstk = dstk;
	}

	public int getMakh() {
		return makh;
	}

	public void setMakh(int makh) {
		this.makh = makh;
	}

	public String getTenkh() {
		return tenkh;
	}

	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}

	public String getDiachikh() {
		return diachikh;
	}

	public void setDiachikh(String diachikh) {
		this.diachikh = diachikh;
	}

	public int getSdt() {
		return sdt;
	}

	public void setSdt(int sdt) {
		this.sdt = sdt;
	}
	
}
