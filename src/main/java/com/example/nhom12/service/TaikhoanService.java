package com.example.nhom12.service;

import java.util.List;

import java.util.Optional;

import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.entity.Taikhoan;

public interface TaikhoanService {
	List<Taikhoan> getAllAccounts();
	Taikhoan saveAccount(Taikhoan taikhoan);
	Taikhoan getAccountUser(String user);
	Taikhoan updateAccount(Taikhoan taikhoan);
	void deleteAccountUser(String user);
	Optional<Taikhoan> findUser(String user);
	Optional<String> checkAccount(String user, String pass);
	List<Taikhoan> searchUser(String user);
	long tongtaikhoan();
}
