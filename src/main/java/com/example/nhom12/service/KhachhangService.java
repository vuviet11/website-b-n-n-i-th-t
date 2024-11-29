package com.example.nhom12.service;

import java.util.List;

import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.entity.Sanpham;


public interface KhachhangService {
	Khachhang saveKhachhang(Khachhang kh);
	List<Khachhang> getAllKH();
	Khachhang getKH(int makh);
	Khachhang updateKH(Khachhang khachhang);
	void deleteKH(int makh);
	List<Khachhang> getKHbyMakh(int kh);
	List<Khachhang> getListTenkh(String tenkh);

}
