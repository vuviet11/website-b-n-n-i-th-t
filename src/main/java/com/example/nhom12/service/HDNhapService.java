package com.example.nhom12.service;

import java.sql.Date;
import java.util.List;

import com.example.nhom12.entity.HDNhap;

public interface HDNhapService {
	
	List<HDNhap> getAllHDNhap();
	
	void saveHDNhap(HDNhap hdnhap);
	
	HDNhap getHDNhapById(String mahdn);
	
	void deleteHDNhapById(String mahdn);
	List<HDNhap> searchMahd(String mahdn);
	List<HDNhap> searchNgay(Date ngay);
	
}
