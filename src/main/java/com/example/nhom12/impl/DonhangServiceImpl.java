package com.example.nhom12.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nhom12.entity.Donhang;
import com.example.nhom12.entity.DonhangChitiet;
import com.example.nhom12.reposity.DonhangChitietRepository;
import com.example.nhom12.reposity.DonhangRepository;
import com.example.nhom12.service.DonhangService;

import jakarta.transaction.Transactional;



@Service
public class DonhangServiceImpl implements DonhangService {
	    @Autowired
	    private DonhangChitietRepository donhangchitietRepository;
	    @Autowired
	    private DonhangRepository donhangRepository;
	
	public DonhangServiceImpl(DonhangRepository donhangRepository) {
		super();
		this.donhangRepository = donhangRepository;
	}
	@Override
	public List<Donhang> getAllDonhang() {
	return donhangRepository.findAllByOrderByNgayDesc();
	}
	@Override
	public Donhang saveDonhang(Donhang dhct) {
	return donhangRepository.save(dhct);
	}
	@Override
	@Transactional
	public Donhang getDonhang(int stt) {
	return donhangRepository.findById(stt).orElse(null);
	}
	@Override
	public Donhang updateDonhang(Donhang dhct) {
	return donhangRepository.save(dhct);
	}
	
	@Override
	public void deleteDonhang(int stt) {
		donhangRepository.deleteById(stt);
	}

	@Override
	public List<Donhang> searchDonhang(int madh) {
	return donhangRepository.findByMadh(madh);
	}
	@Override
	public List<Donhang> getDonhangByNgayAndTt(Date ngay, int tt) {
        return donhangRepository.findByNgayAndTt(ngay, tt);
    }
	@Override
	public List<Donhang> searchDonhangByNgay(Date ngay) {
        return donhangRepository.findByNgay(ngay);
    }
	
	public List<Donhang> getmakh(int makh) {
	return donhangRepository.findByMakh(makh);
	}
	@Override
	public List<Donhang> getmakh(int makh, Date day) {
	return donhangRepository.findByMakhAndNgay(makh, day);
	}
	@Override
	public List<Donhang> getmakh(int makh,int tinhtrang) {
	return donhangRepository.findByMakhAndTt(makh, tinhtrang);
	}	
	@Override
	public List<Donhang> getTt(int tt) {
	return donhangRepository.findByTtOrderByNgayDesc(tt);
	}

}
