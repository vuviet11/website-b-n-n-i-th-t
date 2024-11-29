package com.example.nhom12.service;

import java.util.List;

import com.example.nhom12.entity.Sanpham;

public interface SanphamService {
	List<Sanpham> getAllSanpham();
	Sanpham saveSanpham(Sanpham sanpham);
	Sanpham getSanpham(String masp);
	Sanpham updateSanpham(Sanpham sanpham);
	void deleteSanpham(String masp);
	List<Sanpham> getSanphamBySoluongLessThan(int soluong);
	List<Sanpham> getSanphamByMasp(String masp);
	List<Sanpham> getListTensp(String tensp);
	List<Sanpham> findSanpham(String tensp);
	List<Sanpham> findLoaisanpham(String loaisp);
	long laytongSP();

}
