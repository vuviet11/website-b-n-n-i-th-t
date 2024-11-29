package com.example.nhom12.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nhom12.entity.HDNhap;
import java.util.List;
import java.sql.Date;



@Repository
public interface HDNhapRepository extends JpaRepository<HDNhap, String> {
	List<HDNhap> findByMahdn(String mahdn);
	List<HDNhap> findByNgaynhap(Date ngaynhap);
}
