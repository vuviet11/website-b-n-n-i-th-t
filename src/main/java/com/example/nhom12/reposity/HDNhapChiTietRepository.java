package com.example.nhom12.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhom12.entity.HDNhapChiTiet;

@Repository
public interface HDNhapChiTietRepository extends JpaRepository<HDNhapChiTiet, Integer> {

}
