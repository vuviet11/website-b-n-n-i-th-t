package com.example.nhom12.controller;

import java.sql.Date;
import java.util.ArrayList;
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

import com.example.nhom12.entity.Donhang;
import com.example.nhom12.entity.DonhangChitiet;
import com.example.nhom12.entity.DonhangDTO;
import com.example.nhom12.entity.HDNhap;
import com.example.nhom12.entity.HDNhapChiTiet;
import com.example.nhom12.entity.Nhanvien;
import com.example.nhom12.entity.Sanpham;
import com.example.nhom12.service.HDNhapService;
import com.example.nhom12.service.NhanvienService;
import com.example.nhom12.service.SanphamService;

@Controller
@RequestMapping("/quanlyhdnhap")
public class HDNhapController {
	
	@Autowired
	private HDNhapService hdnhapService;
	
	@Autowired
	private NhanvienService nhanvienService;
	
	@Autowired
	private SanphamService sanphamService;
	
	@GetMapping
	public String listHDNhaps(Model model) {
		model.addAttribute("dshdnhap", hdnhapService.getAllHDNhap());
		return "quanlyhdnhap";
	}
	
	@GetMapping("/addHDNhap")
	public String addHDNhap(Model model) {
		HDNhap hdnhap = new HDNhap();
		List<Nhanvien> dsnv = nhanvienService.getAllNhanVien();
		List<Sanpham> dssp = sanphamService.getAllSanpham();
		model.addAttribute("hdnhap", hdnhap);
		model.addAttribute("dsnv", dsnv);
		model.addAttribute("dssp", dssp);
		return "themhdnhap";
	}
	
	@PostMapping("/saveHDNhap")
	public String saveHDNhap(@ModelAttribute("hdnhap") HDNhap hdnhap) {
		hdnhapService.saveHDNhap(hdnhap);
		// Lấy danh sách chi tiết hoá đơn nhập
	    List<HDNhapChiTiet> chiTiets = hdnhap.getHdnhapchitiets();
	    
	    // Cập nhật số lượng sản phẩm trong bảng Sanpham
	    for (HDNhapChiTiet ct : chiTiets) {
	        String masp = ct.getSanpham().getMasp();
	        int soluongNhap = ct.getSoluong();
	        
	        // Lấy sản phẩm từ mã sản phẩm
	        Sanpham sanpham = sanphamService.getSanpham(masp);
	        
	        // Cập nhật số lượng mới
	        int soluongHienTai = sanpham.getSoluong();
	        sanpham.setSoluong(soluongHienTai + soluongNhap);
	        
	        // Lưu lại sản phẩm sau khi cập nhật
	        sanphamService.saveSanpham(sanpham);
	    }
		return "redirect:/quanlyhdnhap";
	}
	
	@GetMapping("/chiTietHDNhap/{mahdn}")
	public String chiTietHDNhap(@PathVariable(value = "mahdn") String mahdn, Model model) {
		HDNhap hdnhap = hdnhapService.getHDNhapById(mahdn);
		model.addAttribute("hdnhap", hdnhap);
		return "chitiethdn";
	}
	
	@GetMapping("/updateHDNhap/{mahdn}")
	public String updateHDNhap(@PathVariable(value = "mahdn") String mahdn, Model model) {
		HDNhap hdnhap = hdnhapService.getHDNhapById(mahdn);
		List<Nhanvien> dsnv = nhanvienService.getAllNhanVien();
		List<Sanpham> dssp = sanphamService.getAllSanpham();
		model.addAttribute("hdnhap", hdnhap);
		model.addAttribute("dsnv", dsnv);
		model.addAttribute("dssp", dssp);
		return "suahdnhap";
	}
	
	@GetMapping("/deleteHDNhap/{mahdn}")
	public String deleteHDNhap(@PathVariable(value = "mahdn") String mahdn) {
		this.hdnhapService.deleteHDNhapById(mahdn);
		return "redirect:/quanlyhdnhap";
	}
	@PostMapping("/timkiem")
	 public String searchDonhang(@RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchValue", required = false) String searchValue,
			 Model model) {
		 List<HDNhap> dshdn = new ArrayList<>();

		    if (searchType == null || searchValue == null || searchValue.trim().isEmpty()) {
		    	dshdn = hdnhapService.getAllHDNhap();
		    } else {
		        switch (searchType) {
		            case "mahd":
		            	dshdn = hdnhapService.searchMahd(searchValue);
		                
		                break;
		            case "ngay":
		                
		                    Date ngay = Date.valueOf(searchValue); // Ensure searchValue is in yyyy-MM-dd format
		                    dshdn = hdnhapService.searchNgay(ngay);
		                
		                break;
		            default:
		            	dshdn = hdnhapService.getAllHDNhap();
		                break;
		        }
		    }
		    model.addAttribute("dshdnhap", dshdn);
		    return "quanlyhdnhap";
   }
	
}
