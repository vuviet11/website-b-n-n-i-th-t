package com.example.nhom12.service;

import java.util.List;

import com.example.nhom12.entity.HDNhapChiTiet;

public interface HDNhapChiTietService {
	
	List<HDNhapChiTiet> getAllHDNhapChiTiet();
	
	void saveHDNhapChiTiet(HDNhapChiTiet hdnhapchitiet);
	
	HDNhapChiTiet getHDNhapChiTietById(int stt);
	
	void deleteHDNhapChiTietById(int stt);
}
