package com.example.nhom12.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nhom12.entity.HDNhapChiTiet;
import com.example.nhom12.reposity.HDNhapChiTietRepository;
import com.example.nhom12.service.HDNhapChiTietService;

@Service
public class HDNhapChiTietServiceImpl implements HDNhapChiTietService {

	@Autowired
	private HDNhapChiTietRepository hdnhapchitietRepository;
	
	@Override
	public List<HDNhapChiTiet> getAllHDNhapChiTiet() {
		// TODO Auto-generated method stub
		return hdnhapchitietRepository.findAll();
	}

	@Override
	@Transactional
	public void saveHDNhapChiTiet(HDNhapChiTiet hdnhapchitiet) {
		// TODO Auto-generated method stub
		this.hdnhapchitietRepository.save(hdnhapchitiet);
	}

	@Override
	public HDNhapChiTiet getHDNhapChiTietById(int stt) {
		// TODO Auto-generated method stub
		Optional<HDNhapChiTiet> optional = hdnhapchitietRepository.findById(stt);
		HDNhapChiTiet hdnhapchitiet = null;
		if (optional.isPresent()) {
			hdnhapchitiet = optional.get();
		} else {
			throw new RuntimeException("HDNhapChiTiet not found for id :: " + stt);
		}
		return hdnhapchitiet;
	}

	@Override
	public void deleteHDNhapChiTietById(int stt) {
		// TODO Auto-generated method stub
		this.hdnhapchitietRepository.deleteById(stt);
	}

}
