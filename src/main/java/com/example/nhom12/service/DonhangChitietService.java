package com.example.nhom12.service;

import java.util.List;

import com.example.nhom12.entity.DonhangChitiet;



public interface DonhangChitietService {
	DonhangChitiet saveDonhangChitiet(DonhangChitiet dhct);
	List<DonhangChitiet> getAllDonhangChitiet();
	DonhangChitiet getDonhangChitiet(int stt);
	DonhangChitiet updateDonhangChitiet(DonhangChitiet dhct);
	void deleteDonhangChitiet(int madh, String masp);
	List<DonhangChitiet> getDonhangChitietBymadh(int madh);
	DonhangChitiet getDonhangChitiet(int madh, String masp);	
}
