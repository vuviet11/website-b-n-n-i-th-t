package com.example.nhom12.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhom12.entity.Nhanvien;
import java.util.List;


@Repository
public interface NhanvienRopository extends JpaRepository<Nhanvien, String> {

	List<Nhanvien> findByMaNV(String maNV);
	List<Nhanvien> findByTenNVContaining(String tenNV);
}
