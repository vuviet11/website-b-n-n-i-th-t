package com.example.nhom12.impl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.entity.Taikhoan;
import com.example.nhom12.reposity.KhachhangRepository;
import com.example.nhom12.reposity.TaikhoanRepository;
import com.example.nhom12.service.TaikhoanService;

@Service
public class TaikhoanServiceImpl implements TaikhoanService {
	 @Autowired
	    private TaikhoanRepository taikhoanRepository;

	    @Autowired
	    private KhachhangRepository khachhangRepository;
	
	public TaikhoanServiceImpl(TaikhoanRepository taikhoanRepository) {
		super();
		this.taikhoanRepository = taikhoanRepository;
	}
	@Override
	public List<Taikhoan> getAllAccounts() {
	return taikhoanRepository.findAll();
	}
	@Override
	public Taikhoan saveAccount(Taikhoan student) {
	return taikhoanRepository.save(student);
	}
	@Override
	public Taikhoan getAccountUser(String user) {
	return taikhoanRepository.findById(user).get();
	}
	@Override
	public Taikhoan updateAccount(Taikhoan taikhoan) {
	return taikhoanRepository.save(taikhoan);
	}
	@Override
	public void deleteAccountUser(String user) {
		taikhoanRepository.deleteById(user);
	}
	@Override
	public Optional<Taikhoan> findUser(String user) {
		return taikhoanRepository.findById(user);
	}
	@Override
	 public Optional<String> checkAccount(String user, String pass) {
        Optional<Taikhoan> tk = findUser(user);
        if (tk.isPresent() && tk.get().getPassword().equals(pass)) {
            return Optional.of(tk.get().getThanphan());
        } else {
            return Optional.empty(); // hoặc có thể trả về null tùy theo thiết kế
        }
    }
	@Override
	public List<Taikhoan> searchUser(String user){
		return taikhoanRepository.findByUser(user);
	}
	@Override
	public long tongtaikhoan(){
		return taikhoanRepository.count();
	}
	


}
