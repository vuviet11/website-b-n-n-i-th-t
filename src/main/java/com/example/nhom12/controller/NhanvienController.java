package com.example.nhom12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nhom12.entity.Nhanvien;
import com.example.nhom12.service.NhanvienService;

@Controller
@RequestMapping("/quanlynhanvien")
public class NhanvienController {
	
	@Autowired
	private NhanvienService nhanvienService;
	
	@GetMapping
	public String listNhanviens(Model model) {
		model.addAttribute("dsnv", nhanvienService.getAllNhanVien());
		return "quanlynhanvien";
	}
	
	@GetMapping("/addNhanvien")
	public String addNhanvien(Model model) {
		Nhanvien nv = new Nhanvien();
		model.addAttribute("nv", nv);
		return "themnhanvien";
	}
	
	@PostMapping("/saveNhanvien")
	public String saveNhanvien(@ModelAttribute("nv") Nhanvien nv) {
		nhanvienService.saveNhanVien(nv);
		return "redirect:/quanlynhanvien";
	}
	
	@GetMapping("/updateNhanvien/{maNV}")
	public String updateNhanvien(@PathVariable(value = "maNV") String maNV, Model model) {
		Nhanvien nv = nhanvienService.getNhanVienById(maNV);
		model.addAttribute("nv", nv);
		return "suanhanvien";
	}
	
	@GetMapping("/deleteNhanvien/{maNV}")
	public String deleteNhanvien(@PathVariable(value = "maNV") String maNV) {
		this.nhanvienService.deleteNhanVienById(maNV);
		return "redirect:/quanlynhanvien";
	}
	
	@PostMapping("/timkiem")
	public String SearchNhanvien(@RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchValue", required = false) String searchValue,
			Model model) {
		List<Nhanvien> dsnv;
		 if (searchType == null || searchValue == null || searchValue.trim().isEmpty()) {
			 dsnv = nhanvienService.getAllNhanVien();
		    } else {
		            switch (searchType) {
		                case "manv":
		                	dsnv = nhanvienService.searchNhanVienByMaNV(searchValue);
		                    break;
		                case "tennv":
		                    // Tìm sản phẩm theo tên sản phẩm
		                	dsnv = nhanvienService.getListTennv(searchValue);
		                    break;
		                default:
		                    // Mặc định lấy danh sách tất cả sản phẩm
		                	 dsnv = nhanvienService.getAllNhanVien();
		                    break;
		            }
		    }
	    model.addAttribute("dsnv", dsnv);
	    return "quanlynhanvien";
	}
	
}
