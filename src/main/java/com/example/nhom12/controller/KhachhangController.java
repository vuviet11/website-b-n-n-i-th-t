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

import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.entity.Nhanvien;
import com.example.nhom12.entity.Taikhoan;
import com.example.nhom12.service.KhachhangService;


@Controller
@RequestMapping("/quanlykhachhang")
public class KhachhangController {
	@Autowired
	private KhachhangService khachhangService;
	
	@GetMapping
	public String listKhachhang(Model model) {
		model.addAttribute("dskh", khachhangService.getAllKH());
		return "quanlykhachhang";
	}
	
	@GetMapping("/addkhachhang")
	public String addKhachhang(Model model) {
		Khachhang kh = new Khachhang();
		model.addAttribute("kh", kh);
		return "themkhachhang";
	}
	
	@PostMapping("/savekhachhang")
	public String saveKhachhang(@ModelAttribute("kh") Khachhang kh) {
		khachhangService.saveKhachhang(kh);
		return "redirect:/quanlykhachhang";
	}
	
	@GetMapping("/updateKhachhang/{makh}")
	public String updateNhanvien(@PathVariable(value = "makh") int makh, Model model) {
		Khachhang kh = khachhangService.getKH(makh);
		model.addAttribute("kh", kh);
		return "suakhachhang";
	}
	
	@GetMapping("/deleteKhachhang/{makh}")
	public String deleteKhachhang(@PathVariable(value = "makh") int makh) {
		this.khachhangService.deleteKH(makh);
		return "redirect:/quanlykhachhang";
	}
	
	@PostMapping("/timkiem")
	public String SearchNhanvien(@RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchValue", required = false) String searchValue,
			Model model) {
		 List<Khachhang> dskh;
		// Kiểm tra nếu không có thông tin tìm kiếm hoặc giá trị tìm kiếm rỗng
		    if (searchType == null || searchValue == null || searchValue.trim().isEmpty()) {
		    	dskh = khachhangService.getAllKH();
		    } else {
		            switch (searchType) {
		                case "makh":
		                    // Tìm sản phẩm theo mã sản phẩm
		                	int makh = Integer.parseInt(searchValue);
		                	dskh = khachhangService.getKHbyMakh(makh);
		                    break;
		                case "tenkh":
		                    // Tìm sản phẩm theo tên sản phẩm
		                	dskh = khachhangService.getListTenkh(searchValue);
		                    break;
		                default:
		                    // Mặc định lấy danh sách tất cả sản phẩm
		                	dskh = khachhangService.getAllKH();
		                    break;
		            }
		    }

		    model.addAttribute("dskh", dskh);
		    return "quanlykhachhang";
	}
	@PostMapping("/{makh}")
	public String updateAccount(@PathVariable("makh") int makh,
			@RequestParam("tenkh") String tenkh,@RequestParam("dckh") String dckh,
			@RequestParam("sdtkh") int sdt,Model model) {
	// get student from database by id
		Khachhang existingkh = khachhangService.getKH(makh);
		existingkh.setTenkh(tenkh);
		existingkh.setDiachikh(dckh);
		existingkh.setSdt(sdt);
	// save updated student object
		khachhangService.updateKH(existingkh);
	return "redirect:/quanlydonhang/daduyet/{makh}";
	}
}
