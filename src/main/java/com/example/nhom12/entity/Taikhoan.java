package com.example.nhom12.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "tbtaikhoan")

public class Taikhoan {
	@Id
    @Column(name = "user", nullable = false)
	private String user;
	@Column(name = "password")
	private String password;
	@Column(name = "thanphan")
	private String thanphan;
	@Column(name = "manv")
	private String manv;
	@Column(name = "makh")
	private int makh;
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "makh", referencedColumnName = "makh", insertable = false, updatable = false)
    private Khachhang khachhang;
	
	public Taikhoan() {
		
	}
	public Taikhoan(String user,String pass,String thanphan,String manv, int makh) {
		this.user = user;
		this.password = pass;
		this.thanphan = thanphan;
		this.manv = manv;
		this.makh = makh;
	}
	public Taikhoan(String user,String pass) {
		this.user = user;
		this.password = pass;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getThanphan() {
		return thanphan;
	}
	public void setThanphan(String thanphan) {
		this.thanphan = thanphan;
	}
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public int getMakh() {
		return makh;
	}
	public void setMakh(int makh) {
		this.makh = makh;
	}
	public Khachhang getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(Khachhang khachhang) {
        this.khachhang = khachhang;
    }
}
