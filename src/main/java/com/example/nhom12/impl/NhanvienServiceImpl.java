package com.example.nhom12.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nhom12.entity.Nhanvien;
import com.example.nhom12.reposity.NhanvienRopository;
import com.example.nhom12.service.NhanvienService;

@Service
public class NhanvienServiceImpl implements NhanvienService {

	@Autowired
	private NhanvienRopository nhanvienRepository;
	
	@Override
	public List<Nhanvien> getAllNhanVien() {
		// TODO Auto-generated method stub
		return nhanvienRepository.findAll();
	}

	@Override
	public void saveNhanVien(Nhanvien nv) {
		// TODO Auto-generated method stub
		this.nhanvienRepository.save(nv);
	}

	@Override
	public Nhanvien getNhanVienById(String maNV) {
		// TODO Auto-generated method stub
		Optional<Nhanvien> optional = nhanvienRepository.findById(maNV);
		Nhanvien nv = null;
		if (optional.isPresent()) {
			nv = optional.get();
		} else {
			throw new RuntimeException("NV not found for id :: " + maNV);
		}
		return nv;
	}

	@Override
	public void deleteNhanVienById(String maNV) {
		// TODO Auto-generated method stub
		this.nhanvienRepository.deleteById(maNV);
	}
	public List<Nhanvien> searchNhanVienByMaNV(String maNV){
		return nhanvienRepository.findByMaNV(maNV);
	}
	@Override
	public List<Nhanvien> getListTennv(String tennv){
		return nhanvienRepository.findByTenNVContaining(tennv);
	}
}
