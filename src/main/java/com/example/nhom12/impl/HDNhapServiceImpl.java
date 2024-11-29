package com.example.nhom12.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nhom12.entity.HDNhap;
import com.example.nhom12.entity.HDNhapChiTiet;
import com.example.nhom12.entity.Sanpham;
import com.example.nhom12.reposity.HDNhapRepository;
import com.example.nhom12.service.HDNhapService;

@Service
public class HDNhapServiceImpl implements HDNhapService {

	@Autowired
	private HDNhapRepository hdnhapRepository;
	
	@Override
	public List<HDNhap> getAllHDNhap() {
		// TODO Auto-generated method stub
		return hdnhapRepository.findAll();
	}

	@Override
	@Transactional
	public void saveHDNhap(HDNhap hdnhap) {
		// TODO Auto-generated method stub
		for (HDNhapChiTiet chiTiet : hdnhap.getHdnhapchitiets()) {
			chiTiet.setHdnhap(hdnhap);
			chiTiet.setSanpham(chiTiet.getSanpham());
			chiTiet.setSoluong(chiTiet.getSoluong());
			chiTiet.setGianhap(chiTiet.getGianhap());
		}
		this.hdnhapRepository.save(hdnhap);
	}

	@Override
	public HDNhap getHDNhapById(String mahdn) {
		// TODO Auto-generated method stub
		Optional<HDNhap> optional = hdnhapRepository.findById(mahdn);
		HDNhap hdnhap = null;
		if (optional.isPresent()) {
			hdnhap = optional.get();
		} else {
			throw new RuntimeException("HDNhap not found for mahdn :: " + mahdn);
		}

		return hdnhap;
	}

	@Override
	public void deleteHDNhapById(String mahdn) {
		// TODO Auto-generated method stub
		this.hdnhapRepository.deleteById(mahdn);
	}
	@Override
	public List<HDNhap> searchMahd(String mahdn) {
		// TODO Auto-generated method stub
		return hdnhapRepository.findByMahdn(mahdn);
	}
	@Override
	public List<HDNhap> searchNgay(Date ngay) {
		// TODO Auto-generated method stub
		return hdnhapRepository.findByNgaynhap(ngay);
	}

}
