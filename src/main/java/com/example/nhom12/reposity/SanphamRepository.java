package com.example.nhom12.reposity;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.nhom12.entity.Sanpham;
@Repository
public interface SanphamRepository extends  JpaRepository<Sanpham,String> {

	List<Sanpham> findBySoluongLessThan(Integer soluong);

	List<Sanpham> findByMasp(String masp);

	List<Sanpham> findByTenspContaining(String tensp);
	
	List<Sanpham> findByTenspLike(String tensp);
	
	List<Sanpham> findByLoaisp(String loaisp);

	
}
