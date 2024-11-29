package com.example.nhom12.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nhom12.entity.DonhangChitiet;
import com.example.nhom12.reposity.DonhangChitietRepository;
import com.example.nhom12.reposity.DonhangRepository;
import com.example.nhom12.service.DonhangChitietService;

import jakarta.transaction.Transactional;

@Service
public class DonhangChitietServiceImpl implements DonhangChitietService {
	 @Autowired
	    private DonhangRepository donhangRepository;
	    @Autowired
	    private DonhangChitietRepository donhangchitietRepository;
	
	public DonhangChitietServiceImpl(DonhangChitietRepository donhangchitietRepository) {
		super();
		this.donhangchitietRepository = donhangchitietRepository;
	}
	@Override
	public List<DonhangChitiet> getAllDonhangChitiet() {
	return donhangchitietRepository.findAll();
	}
	@Override
	public DonhangChitiet saveDonhangChitiet(DonhangChitiet dhct) {
	return donhangchitietRepository.save(dhct);
	}
	@Override
	public DonhangChitiet getDonhangChitiet(int stt) {
	return donhangchitietRepository.findById(stt).get();
	}
	@Override
	public DonhangChitiet updateDonhangChitiet(DonhangChitiet dhct) {
	return donhangchitietRepository.save(dhct);
	}
	 @Override
	 @Transactional
	    public void deleteDonhangChitiet(int madh, String masp) {
	        donhangchitietRepository.deleteByMadhAndMasp(madh, masp);
	    }
	@Override
	public List<DonhangChitiet> getDonhangChitietBymadh(int madh) {
	return donhangchitietRepository.findByMadh(madh);
	}
	 @Override
	    public DonhangChitiet getDonhangChitiet(int madh, String masp) {
	        return donhangchitietRepository.findByMadhAndMasp(madh, masp);
	   
		}	
}
