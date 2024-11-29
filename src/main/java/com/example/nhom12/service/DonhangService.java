package com.example.nhom12.service;

import java.sql.Date;
import java.util.List;

import com.example.nhom12.entity.Donhang;




public interface DonhangService {
	List<Donhang> getAllDonhang();
	Donhang saveDonhang(Donhang dh);
	Donhang getDonhang(int madh);
	Donhang updateDonhang(Donhang dh);
	void deleteDonhang(int madh);
	List<Donhang> searchDonhang(int madh);
	List<Donhang> getDonhangByNgayAndTt(Date ngay, int tt);
	List<Donhang> searchDonhangByNgay(Date ngay);
	List<Donhang> getmakh(int makh);
	List<Donhang> getmakh(int makh,Date day);
	List<Donhang> getmakh(int makh,int ttinhtrang);
	List<Donhang> getTt(int tt);
}
