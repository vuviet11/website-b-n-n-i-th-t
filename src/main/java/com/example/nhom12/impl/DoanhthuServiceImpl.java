package com.example.nhom12.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nhom12.entity.Doanhthu;
import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.reposity.DoanhthuRepository;
import com.example.nhom12.service.DoanhthuService;
@Service
public class DoanhthuServiceImpl implements DoanhthuService {
    @Autowired
    private DoanhthuRepository doanhthuRepository;

    public DoanhthuServiceImpl(DoanhthuRepository doanhthuRepository) {
	super();
	this.doanhthuRepository = doanhthuRepository;
	}
    
    @Override
	public Doanhthu save(Doanhthu dt) {
	return doanhthuRepository.save(dt);
	}
    @Override
    public Doanhthu getAll(Date ngay) {
    	return doanhthuRepository.findByNgay(ngay);
    }
    @Override
	public Doanhthu update(Doanhthu dt) {
	return doanhthuRepository.save(dt);
	}
}
