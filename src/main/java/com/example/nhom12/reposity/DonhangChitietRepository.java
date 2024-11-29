package com.example.nhom12.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nhom12.entity.DonhangChitiet;
import java.util.List;




public interface DonhangChitietRepository extends  JpaRepository<DonhangChitiet,Integer> {
	List<DonhangChitiet> findByMadh(int madh);
	DonhangChitiet findByMadhAndMasp(int madh,String masp);
	void deleteByMadhAndMasp(int madh, String masp);
	
}
