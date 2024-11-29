package com.example.nhom12.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.nhom12.entity.Sanpham;
import com.example.nhom12.reposity.SanphamRepository;
import com.example.nhom12.service.SanphamService;

@Service
public class SanphamServiceImpl implements SanphamService  {
	private SanphamRepository sanphamRepository;
	public SanphamServiceImpl(SanphamRepository sanphamRepository) {
		super();
		this.sanphamRepository = sanphamRepository;
	}
	@Override
	public List<Sanpham> getAllSanpham() {
	return sanphamRepository.findAll();
	}
	@Override
	public Sanpham saveSanpham(Sanpham sanpham) {
	return sanphamRepository.save(sanpham);
	}
	@Override
	public Sanpham getSanpham(String masp) {
	return sanphamRepository.findById(masp).get();
	}
	@Override
	public Sanpham updateSanpham(Sanpham sanpham) {
	return sanphamRepository.save(sanpham);
	}
	@Override
	public void deleteSanpham(String masp) {
		sanphamRepository.deleteById(masp);
	}
	@Override
    public List<Sanpham> getSanphamBySoluongLessThan(int soluong) {
        return sanphamRepository.findBySoluongLessThan(soluong);
    }
	@Override
	public List<Sanpham> getSanphamByMasp(String masp){
		return sanphamRepository.findByMasp(masp);
	}
	@Override
	public List<Sanpham> getListTensp(String tensp){
		return sanphamRepository.findByTenspContaining(tensp);
	}
	@Override
	public List<Sanpham> findSanpham(String tensp) {
		return sanphamRepository.findByTenspLike("%"+tensp+"%");
	}
	@Override
	public List<Sanpham> findLoaisanpham(String loaisp) {
		return sanphamRepository.findByLoaisp(loaisp);
	}
	@Override
	public long laytongSP() {
		return sanphamRepository.count();
	}

}
