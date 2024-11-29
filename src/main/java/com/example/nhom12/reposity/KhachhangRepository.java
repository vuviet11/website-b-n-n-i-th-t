package com.example.nhom12.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.entity.Sanpham;

@Repository
public interface KhachhangRepository extends JpaRepository<Khachhang,Integer> {

	List<Khachhang> findByMakh(int makh);
	List<Khachhang> findByTenkhContaining(String tenkh);
}
