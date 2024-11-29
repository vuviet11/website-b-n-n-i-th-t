package com.example.nhom12.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nhom12.entity.Donhang;

import java.sql.Date;
import java.util.List;



public interface DonhangRepository extends  JpaRepository<Donhang,Integer>{
	List<Donhang> findByMadh(int madh);
	List<Donhang> findByNgayAndTt(Date ngay, int tt);
	List<Donhang> findByNgay(Date ngay);
	List<Donhang> findByMakhAndNgay(int makh, Date ngay);
	List<Donhang> findByMakhAndTt(int makh,int tinhttrang);
	List<Donhang> findByMakh(int makh);
	List<Donhang> findAllByOrderByNgayDesc();
	List<Donhang> findByTtOrderByNgayDesc(int tt);

}
