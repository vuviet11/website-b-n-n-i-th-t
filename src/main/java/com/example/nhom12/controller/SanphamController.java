package com.example.nhom12.controller;


import java.io.IOException;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nhom12.entity.Nhanvien;
import com.example.nhom12.entity.Sanpham;
import com.example.nhom12.entity.Taikhoan;
import com.example.nhom12.service.SanphamService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/quanlysanpham")
public class SanphamController {
	private SanphamService sanphamService;
	public SanphamController (SanphamService sanphamService) {
		super();
		this.sanphamService = sanphamService;
	}
	
	@GetMapping("/giaodienadmin")
	public String giaodienadmin() {
        return "giaodienadmin"; 
    }
	@GetMapping
	public String listSanpham(Model model) {
		model.addAttribute("qlsp", sanphamService.getAllSanpham());
		return "quanlysanpham";
	}
	@GetMapping("/new")
	public String createSanphamForm(Model model) {
		Sanpham sanpham = new Sanpham();
		model.addAttribute("qlsp", sanpham);
		return "themsanpham";
	}

	@PostMapping
	 public String saveSanpham(
	            @ModelAttribute Sanpham sanpham,
	            @RequestParam("imgFile") MultipartFile imgFile,
	            RedirectAttributes redirectAttributes) {

	        if (!imgFile.isEmpty()) {
	            try {
	                // Define the path where you want to save the file in the static directory
	                String uploadDir = "src/main/resources/static/thiet_ke_noi_that";
	                String fileName = imgFile.getOriginalFilename();
	                Path filePath = Paths.get(uploadDir, fileName);

	                // Save the file locally
	                Files.copy(imgFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	                // Set the image path to the entity
	                sanpham.setImg(fileName); // Save the relative path for serving the image
	            } catch (IOException e) {
	                e.printStackTrace();
	                redirectAttributes.addFlashAttribute("message", "File upload failed");
	                return "redirect:/quanlysanpham";
	            }
	        }

	        // Save the entity using your service layer
	        sanphamService.saveSanpham(sanpham);

	        redirectAttributes.addFlashAttribute("message", "Sanpham saved successfully");
	        return "redirect:/quanlysanpham";
	    }


	@GetMapping("/{id}")
	public String deleteSanpham(@PathVariable("id") String id) {
		sanphamService.deleteSanpham(id);
	return "redirect:/quanlysanpham";
	}
	
	@GetMapping("/edit/{id}")
	public String editSanphamForm(@PathVariable("id") String id, Model model) {
	model.addAttribute("qlsp", sanphamService.getSanpham(id));
	return "suasanpham";
	}
	@PostMapping("/{id}")
	public String updateSanpham(@PathVariable("id") String id,
	@ModelAttribute("qlsp") Sanpham sanpham,
	@RequestParam("imgFile") MultipartFile imgFile,
	Model model) {
	// get student from database by id
		Sanpham existingSanpham = sanphamService.getSanpham(id);
		existingSanpham.setMasp(id);
		existingSanpham.setTensp(sanpham.getTensp());
		existingSanpham.setLoaisp(sanpham.getLoaisp());
		existingSanpham.setSoluong(sanpham.getSoluong());
		existingSanpham.setGianhap(sanpham.getGianhap());
		existingSanpham.setGiaban(sanpham.getGiaban());
		existingSanpham.setNoidung(sanpham.getNoidung());
		 if (!imgFile.isEmpty()) {
		        try {
		            // Define the path where you want to save the file in the static directory
		            String uploadDir = "src/main/resources/static/thiet_ke_noi_that";
		            String fileName = imgFile.getOriginalFilename();
		            Path filePath = Paths.get(uploadDir, fileName);

		            // Save the file locally
		            Files.copy(imgFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		            // Set the image path to the entity
		            existingSanpham.setImg(fileName); // Save the relative path for serving the image
		        } catch (IOException e) {
		            e.printStackTrace();
		            return "redirect:/quanlysanpham";
		        }
		    }
		sanphamService.updateSanpham(existingSanpham);
		return "redirect:/quanlysanpham";
	}
	 @GetMapping("/thongkesanpham")
	 public String listThongkeSP(Model model) {
			model.addAttribute("qlsp", sanphamService.getSanphamBySoluongLessThan(30));
			return "thongkesp";
		}
	 @PostMapping("/timkiem")
		public String SearchSanpham(@RequestParam(value = "searchType", required = false) String searchType,
	             @RequestParam(value = "searchValue", required = false) String searchValue,
				Model model) {
		 List<Sanpham> dssp;

		    // Kiểm tra nếu không có thông tin tìm kiếm hoặc giá trị tìm kiếm rỗng
		    if (searchType == null || searchValue == null || searchValue.trim().isEmpty()) {
		        dssp = sanphamService.getAllSanpham();
		    } else {
		            switch (searchType) {
		                case "masp":
		                    // Tìm sản phẩm theo mã sản phẩm
		                    dssp = sanphamService.getSanphamByMasp(searchValue);
		                    break;
		                case "tensp":
		                    // Tìm sản phẩm theo tên sản phẩm
		                    dssp = sanphamService.getListTensp(searchValue);
		                    break;
		                default:
		                    // Mặc định lấy danh sách tất cả sản phẩm
		                    dssp = sanphamService.getAllSanpham();
		                    break;
		            }
		    }

		    // Thêm danh sách sản phẩm vào model để hiển thị trên view
		    model.addAttribute("qlsp", dssp);
		    return "quanlysanpham"; // Trả về view quản lý sản phẩm
	}
	 @GetMapping("/timloaisp/{loaisp}")
	 public String Timloaisp(@PathVariable("loaisp") String loaisp, Model model, HttpSession session) {
		    Taikhoan userSession = (Taikhoan) session.getAttribute("session");
		    if (userSession == null) {
			    model.addAttribute("userName","none");
		    }
		    else {
		        model.addAttribute("userName",userSession.getUser());
		    }

	     List<Sanpham> dssp = sanphamService.getAllSanpham();

	     // Loại bỏ các phần tử trùng lặp dựa trên loaisp
	     Set<String> loaispSet = dssp.stream()
	                                 .map(Sanpham::getLoaisp)
	                                 .collect(Collectors.toSet());
	     List<Sanpham> uniqueSanpham = dssp.stream()
	                                       .filter(sp -> loaispSet.remove(sp.getLoaisp()))
	                                       .collect(Collectors.toList());

	     model.addAttribute("qlsp", uniqueSanpham);
	     model.addAttribute("dssp", sanphamService.findLoaisanpham(loaisp));
	     return "giaodienkhachhang";
	 }
	 
	 

}
