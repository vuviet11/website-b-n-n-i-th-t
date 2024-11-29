package com.example.nhom12.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nhom12.entity.Doanhthu;
import com.example.nhom12.entity.Donhang;
import com.example.nhom12.entity.DonhangChitiet;
import com.example.nhom12.entity.DonhangDTO;
import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.entity.Nhanvien;
import com.example.nhom12.entity.Sanpham;
import com.example.nhom12.entity.Taikhoan;
import com.example.nhom12.service.DoanhthuService;
import com.example.nhom12.service.DonhangChitietService;
import com.example.nhom12.service.DonhangService;
import com.example.nhom12.service.KhachhangService;
import com.example.nhom12.service.SanphamService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/quanlydonhang")
public class DonghangController {
	@Autowired
	private KhachhangService khachhangService;
	@Autowired
	private DoanhthuService doanhthuService;
	@Autowired
	private SanphamService sanphamService;
	@Autowired
    private DonhangChitietService donhangchitietService;
	@Autowired
    private DonhangService donhangService;
	public DonghangController (DonhangService donhangService) {
		super();
		this.donhangService = donhangService;
	}
	@Autowired
	public DonghangController(DonhangChitietService donhangchitietService) {
	    this.donhangchitietService = donhangchitietService;
	}
	@GetMapping
	public String listDonhang(Model model) {
	    List<Donhang> dsdh = donhangService.getTt(1);
	    List<DonhangDTO> donhangDTOs = new ArrayList<>();

	    for (Donhang donhang : dsdh) {
	        List<DonhangChitiet> dsdhct = donhangchitietService.getDonhangChitietBymadh(donhang.getMadh());
	        for (DonhangChitiet donhangChitiet : dsdhct) {
	            Sanpham sanpham = sanphamService.getSanpham(donhangChitiet.getMasp());
	            donhangDTOs.add(new DonhangDTO(donhang, donhangChitiet, sanpham));
	        }
	    }
	    model.addAttribute("donhangDTOs", donhangDTOs);
	    return "quanlydonhang";
	}
	@GetMapping("/dathang")
	public String listDathang(Model model) {
	    List<Donhang> dsdh = donhangService.getTt(0);
	    List<DonhangDTO> donhangDTOs = new ArrayList<>();

	    for (Donhang donhang : dsdh) {
	        List<DonhangChitiet> dsdhct = donhangchitietService.getDonhangChitietBymadh(donhang.getMadh());
	        for (DonhangChitiet donhangChitiet : dsdhct) {
	            Sanpham sanpham = sanphamService.getSanpham(donhangChitiet.getMasp());
	            donhangDTOs.add(new DonhangDTO(donhang, donhangChitiet, sanpham));
	        }
	    }
	    model.addAttribute("donhangDTOs", donhangDTOs);
	    return "quanlydathang";
	}
	@GetMapping("/adddonhang")
	public String addDonhang(Model model) {
		DonhangDTO donhangDTO = new DonhangDTO();
        model.addAttribute("donhangDTO", donhangDTO); 
		return "themdonhang";
	}
	@PostMapping("/savedonhang")
	public String saveDonhang(@ModelAttribute("donhangDTO") DonhangDTO donhangDTO) {
	    // Set default date if it's null
	    if (donhangDTO.getDonhang().getNgay() == null) {
	        donhangDTO.getDonhang().setNgay(new java.sql.Date(System.currentTimeMillis()));
	    }

	    // 1. Save the order (Donhang) into the database
	    Donhang savedDonhang = donhangService.saveDonhang(donhangDTO.getDonhang());

	    // 2. Retrieve the saved order to get its ID
	    int madh = savedDonhang.getMadh();

	    // 3. Set up order details (DonhangChitiet)
	    donhangDTO.getDonhangChitiet().setDonhang(savedDonhang);
	    donhangDTO.getDonhangChitiet().setMadh(madh);

	    // 4. Save the order details (DonhangChitiet) into the database
	    DonhangChitiet savedDonhangChitiet = donhangchitietService.saveDonhangChitiet(donhangDTO.getDonhangChitiet());
	    return "redirect:/quanlydonhang";
	}
	@GetMapping("/themdhct/{madh}")
	public String addDHCT(@PathVariable(value = "madh") int madh, Model model) {
		DonhangDTO donhangDTO = new DonhangDTO();
	    Donhang donhang = donhangService.getDonhang(madh);
	    donhangDTO.setDonhang(donhang); // Thiết lập đơn hàng vào DonhangDTO
	    model.addAttribute("donhangDTO", donhangDTO);
	    return "themdhct";
	}
	@PostMapping("/savedhct")
	public String saveDHCT(@ModelAttribute("donhangDTO") DonhangDTO donhangDTO
	                       ) {
		 int madh = donhangDTO.getDonhang().getMadh();

	    donhangDTO.getDonhangChitiet().setMadh(madh);

	    // Save the order details (DonhangChitiet) into the database
	    donhangchitietService.saveDonhangChitiet(donhangDTO.getDonhangChitiet());

	    // Redirect to the list of orders after successfully saving
	    return "redirect:/quanlydonhang";
	}
	@GetMapping("/updateDonhang/{madh}/{masp}")
	public String updateNhanvien(@PathVariable(value = "madh") int madh, Model model,
			@PathVariable(value = "masp") String masp) {
		 // Lấy thông tin đơn hàng
        Donhang donhang = donhangService.getDonhang(madh);
        // Lấy thông tin đơn hàng chi tiết
        DonhangChitiet donhangChitiet = donhangchitietService.getDonhangChitiet(madh, masp);

        // Tạo đối tượng DonhangDTO và đổ dữ liệu vào
        DonhangDTO donhangDTO = new DonhangDTO();
        donhangDTO.setDonhang(donhang);
        donhangDTO.setDonhangChitiet(donhangChitiet);

        // Đưa DonhangDTO vào model để gửi đến view
        model.addAttribute("donhangDTO", donhangDTO);

        return "suadonhang";
	}
	@PostMapping("/editdh/{madh}/{masp}")
	public String updateDonhang(@PathVariable(value = "madh") int madh,
	                            @PathVariable(value = "masp") String masp,
	                            @ModelAttribute("donhangDTO") DonhangDTO donhangDTO,
	                            Model model) {
	    // Lấy thông tin đơn hàng từ cơ sở dữ liệu bằng id
	    Donhang existingDonhang = donhangService.getDonhang(madh);
	    existingDonhang.setMadh(madh);
	    existingDonhang.setMakh(donhangDTO.getDonhang().getMakh());
	    existingDonhang.setTt(donhangDTO.getDonhang().getTt());

	    // Cập nhật đơn hàng
	    donhangService.updateDonhang(existingDonhang);

	    // Lấy thông tin chi tiết đơn hàng
	    DonhangChitiet existingdhct = donhangchitietService.getDonhangChitiet(madh, masp);
	    existingdhct.setSl(donhangDTO.getDonhangChitiet().getSl());

	    // Nếu tình trạng là 1, cập nhật số lượng sản phẩm
	    if (donhangDTO.getDonhang().getTt() == 1) {
	        Sanpham sanpham = sanphamService.getSanpham(donhangDTO.getDonhangChitiet().getMasp());
	        int newQuantity = sanpham.getSoluong() - donhangDTO.getDonhangChitiet().getSl();

	        // Cập nhật số lượng sản phẩm
	        sanpham.setSoluong(newQuantity);
	        sanphamService.saveSanpham(sanpham);
	    }

	    // Điều hướng về trang quản lý đơn hàng
	    return "redirect:/quanlydonhang";
	}
	@GetMapping("/duyet/{madh}")
	public String duyetDonhang(@PathVariable(value = "madh") int madh,
	                            @ModelAttribute("donhangDTO") DonhangDTO donhangDTO,
	                            Model model) {
		// Lấy thông tin đơn hàng từ cơ sở dữ liệu bằng id
        Donhang existingDonhang = donhangService.getDonhang(madh);
        if (existingDonhang == null) {
            // Xử lý khi đơn hàng không tồn tại
            model.addAttribute("error", "Đơn hàng không tồn tại");
            return "error"; // Trang lỗi tùy chỉnh của bạn
        }

        // Cập nhật tình trạng đơn hàng
        existingDonhang.setTt(1);
        donhangService.updateDonhang(existingDonhang);

        // Cập nhật số lượng sản phẩm trong kho
        List<DonhangChitiet> donhangChitietList = donhangchitietService.getDonhangChitietBymadh(madh);
        for (DonhangChitiet donhangChitiet : donhangChitietList) {
            Sanpham sanpham = sanphamService.getSanpham(donhangChitiet.getMasp());
            if (sanpham != null) {
                int newQuantity = sanpham.getSoluong() - donhangChitiet.getSl();
                sanpham.setSoluong(newQuantity);
                sanphamService.saveSanpham(sanpham);
            } else {
                // Xử lý khi sản phẩm không tồn tại
                model.addAttribute("error", "Sản phẩm không tồn tại");
                return "error"; // Trang lỗi tùy chỉnh của bạn
            }
        }

        // Điều hướng về trang quản lý đơn hàng
        return "redirect:/quanlydonhang/dathang";
	}
	@GetMapping("/deleteDonhang/{madh}/{masp}")
	public String deleteDonhang(@PathVariable(value = "masp") String masp,
			@PathVariable(value = "madh") int madh) {
		List<DonhangChitiet> listdhct = this.donhangchitietService.getDonhangChitietBymadh(madh);
		if (listdhct != null && listdhct.size() > 1) {
	        // Nếu có nhiều hơn 1 DonhangChitiet, chỉ xóa DonhangChitiet
	        this.donhangchitietService.deleteDonhangChitiet(madh, masp);
	    } else if (listdhct != null && listdhct.size() == 1) {
	        // Nếu chỉ có 1 DonhangChitiet, xóa cả Donhang và DonhangChitiet
	        this.donhangService.deleteDonhang(madh);
	        this.donhangchitietService.deleteDonhangChitiet(madh, masp);
	    }
		return "redirect:/quanlydonhang";
	}
	@PostMapping("/timkiem")
	 public String searchDonhang(@RequestParam(value = "searchType", required = false) String searchType,
             @RequestParam(value = "searchValue", required = false) String searchValue,
			 Model model) {
		 List<Donhang> dsdh = new ArrayList<>();
		    List<DonhangDTO> donhangDTOs = new ArrayList<>();

		    if (searchType == null || searchValue == null || searchValue.trim().isEmpty()) {
		        dsdh = donhangService.getAllDonhang();
		    } else {
		        switch (searchType) {
		            case "madh":
		                
		                    int madh = Integer.parseInt(searchValue);
		                    dsdh = donhangService.searchDonhang(madh);
		                
		                break;
		            case "ngay":
		                
		                    Date ngay = Date.valueOf(searchValue); // Ensure searchValue is in yyyy-MM-dd format
		                    dsdh = donhangService.searchDonhangByNgay(ngay);
		                
		                break;
		            default:
		                dsdh = donhangService.getAllDonhang();
		                break;
		        }
		    }

		    for (Donhang donhang : dsdh) {
		        List<DonhangChitiet> dsdhct = donhangchitietService.getDonhangChitietBymadh(donhang.getMadh());
		        for (DonhangChitiet donhangChitiet : dsdhct) {
		            Sanpham sanpham = sanphamService.getSanpham(donhangChitiet.getMasp());
		            donhangDTOs.add(new DonhangDTO(donhang, donhangChitiet, sanpham));
		        }
		    }

		    model.addAttribute("donhangDTOs", donhangDTOs);
		    return "quanlydonhang";
    }
	@GetMapping("/daduyet/{makh}")
	public String listDaduyet(@PathVariable("makh") int makh ,Model model) {
	    List<Donhang> dsdh = donhangService.getmakh(makh,1);
	    List<DonhangDTO> donhangDTOs = new ArrayList<>();
	    Khachhang kh = khachhangService.getKH(makh);
	    float gia = 0;
	    for (Donhang donhang : dsdh) {
	        List<DonhangChitiet> dsdhct = donhangchitietService.getDonhangChitietBymadh(donhang.getMadh());
	        for (DonhangChitiet donhangChitiet : dsdhct) {
	            Sanpham sanpham = sanphamService.getSanpham(donhangChitiet.getMasp());
	            donhangDTOs.add(new DonhangDTO(donhang, donhangChitiet, sanpham));
	            gia += sanpham.getGiaban() * donhangChitiet.getSl(); 
	        }
	    }
	    model.addAttribute("gia",gia);
	    model.addAttribute("dskh",kh);
	    model.addAttribute("donhangDTOs", donhangDTOs);
	    return "thanhtoan";
	}
	@PostMapping("/updatesoluong/{masp}/{madh}")
	public String updatesoluong(@PathVariable("masp") String masp,
	                            @PathVariable("madh") int madh,
	                            @RequestParam("soluong") int soluong,
	                            Model model) {
	    // Cập nhật số lượng sản phẩm trong đơn hàng chi tiết
	    DonhangChitiet donhangchitiet = donhangchitietService.getDonhangChitiet(madh, masp);
	    donhangchitiet.setSl(soluong);
	    donhangchitietService.saveDonhangChitiet(donhangchitiet);

	    // Lấy thông tin đơn hàng và các sản phẩm để hiển thị lại trong giỏ hàng
	    List<Sanpham> SP = new ArrayList<>();
	    List<DonhangChitiet> sl_ctsp = new ArrayList<>();
	    double totalPrice = 0;
	    int sl = 0;
	    Set<Sanpham> uniqueProducts = new HashSet<>();
	    List<Donhang> donhang = donhangService.getmakh(donhangchitiet.getDonhang().getMakh());
	    for (Donhang dh : donhang) {
	        if (dh != null && dh.getTt() == 0) {
	            List<DonhangChitiet> dhct = donhangchitietService.getDonhangChitietBymadh(dh.getMadh());
	            for (DonhangChitiet DhCt : dhct) {
	                Sanpham s = sanphamService.getSanpham(DhCt.getMasp());
	                SP.add(s);
	                sl_ctsp.add(DhCt);
	                totalPrice += DhCt.getSl() * s.getGiaban();
	                sl += DhCt.getSl();
	                uniqueProducts.add(s);
	            }
	        }
	    }
	    int differentProductCount = uniqueProducts.size();

	    // Thêm các thuộc tính cần thiết vào model để hiển thị lại trong giỏ hàng
	    model.addAttribute("ngayDonHang", donhang.get(0).getNgay());
	    model.addAttribute("sl", sl);
	    model.addAttribute("tongmasp", differentProductCount);
	    model.addAttribute("totalPrice", totalPrice);
	    model.addAttribute("slsp", sl_ctsp);
	    model.addAttribute("SanphamInCart", SP);

	    // Chuyển hướng về trang giỏ hàng sau khi cập nhật thành công
	    return "redirect:/quanlytaikhoan/giohang";
	}

}
