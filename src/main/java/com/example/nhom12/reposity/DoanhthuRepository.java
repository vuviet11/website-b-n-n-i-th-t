package com.example.nhom12.reposity;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nhom12.entity.Doanhthu;



public interface DoanhthuRepository extends JpaRepository<Doanhthu,Integer> {

	Doanhthu findByNgay(Date ngay);



}
