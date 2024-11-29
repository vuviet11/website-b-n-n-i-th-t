package com.example.nhom12.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.entity.Sanpham;
import com.example.nhom12.reposity.KhachhangRepository;
import com.example.nhom12.reposity.TaikhoanRepository;
import com.example.nhom12.service.KhachhangService;

@Service
public class KhachhangServiceImpl implements KhachhangService {
	@Autowired
    private TaikhoanRepository taikhoanRepository;

    @Autowired
    private KhachhangRepository khachhangRepository;
    
    public KhachhangServiceImpl(KhachhangRepository khachhangRepository) {
    	this.khachhangRepository = khachhangRepository;
    }
    @Override
   	public Khachhang saveKhachhang(Khachhang kh) {
   		return khachhangRepository.save(kh);
   	}
    @Override
	public List<Khachhang> getAllKH() {
	return khachhangRepository.findAll();
	}
    @Override
	public Khachhang getKH(int makh) {
	return khachhangRepository.findById(makh).get();
	}
	@Override
	public Khachhang updateKH(Khachhang kh) {
	return khachhangRepository.save(kh);
	}
	@Override
	public void deleteKH(int makh) {
		khachhangRepository.deleteById(makh);
	}
	@Override
	public List<Khachhang> getKHbyMakh(int makh){
		return khachhangRepository.findByMakh(makh);
	}
	@Override
	public List<Khachhang> getListTenkh(String tenkh){
		return khachhangRepository.findByTenkhContaining(tenkh);
	}
}
