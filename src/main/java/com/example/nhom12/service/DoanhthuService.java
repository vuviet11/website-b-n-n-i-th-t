package com.example.nhom12.service;

import java.sql.Date;
import java.util.List;

import com.example.nhom12.entity.Doanhthu;

public interface DoanhthuService {

	Doanhthu save(Doanhthu doanhthu);
	Doanhthu getAll(Date ngay);
	Doanhthu update(Doanhthu doanhthu);
}
