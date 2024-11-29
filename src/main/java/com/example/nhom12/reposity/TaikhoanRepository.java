package com.example.nhom12.reposity;

import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.entity.Taikhoan;
import java.util.List;


public interface TaikhoanRepository extends  JpaRepository<Taikhoan,String> {
	 List<Taikhoan> findByUser(String user);
	 
}
