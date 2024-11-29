package com.example.nhom12.service;

import java.util.List;

import com.example.nhom12.entity.Nhanvien;

public interface NhanvienService {
	List<Nhanvien> getAllNhanVien();
	
	void saveNhanVien(Nhanvien nv);
	
	Nhanvien getNhanVienById(String maNV);
	
	void deleteNhanVienById(String maNV);
	List<Nhanvien> searchNhanVienByMaNV(String maNV);
	List<Nhanvien> getListTennv(String tennv);
	
}
