package com.example.nhom12.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nhom12.entity.Doanhthu;
import com.example.nhom12.entity.Donhang;
import com.example.nhom12.entity.DonhangChitiet;
import com.example.nhom12.entity.Khachhang;
import com.example.nhom12.entity.Sanpham;
import com.example.nhom12.entity.Taikhoan;
import com.example.nhom12.service.KhachhangService;
import com.example.nhom12.service.TaikhoanService;
import com.example.nhom12.service.DoanhthuService;
import com.example.nhom12.service.DonhangChitietService;
import com.example.nhom12.service.DonhangService;
import com.example.nhom12.service.SanphamService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/quanlytaikhoan")
public class TaikhoanController {
	@Autowired
    private DonhangService donhangservice;
	@Autowired
    private DoanhthuService doanhthuservice;
	@Autowired
    private DonhangChitietService donhangchitietservice;
	@Autowired
    private TaikhoanService taikhoanService;
	@Autowired
    private SanphamService sanphamService;
	@Autowired
    private KhachhangService KhachhangService;
	public TaikhoanController (TaikhoanService taikhoanservice, SanphamService sanphamService, KhachhangService KhachhangService, DonhangChitietService donhangchitietservice, DonhangService donhangservice) {
		super();
		this.taikhoanService = taikhoanservice;
		this.sanphamService = sanphamService;
		this.KhachhangService = KhachhangService;
		this.donhangservice = donhangservice;
		this.donhangchitietservice = donhangchitietservice;
	}
	public void tinhtongsp(Model model) {
		long tongsp = sanphamService.laytongSP();
		long tongtk = taikhoanService.tongtaikhoan();
		List<Sanpham> allSanphams = sanphamService.getAllSanpham();
        Set<String> uniqueLoaisp = new HashSet<>();

        for (Sanpham sanpham : allSanphams) {
            uniqueLoaisp.add(sanpham.getLoaisp());
        }
        model.addAttribute("tongtk",tongtk);
        model.addAttribute("tongloaisp",uniqueLoaisp.size());
		model.addAttribute("tongsp",tongsp);
	}
	
	public void tinhTongTien(Model model) {
        // Lấy ngày hiện tại
        Date ngayHienTai = Date.valueOf(LocalDate.now());
//	 	LocalDate ngayHienTai1 = LocalDate.of(2024, 6, 5);
//
//        // Chuyển đổi từ LocalDate thành java.sql.Date
//        Date ngayHienTai = Date.valueOf(ngayHienTai1);

        // Lấy danh sách đơn hàng theo ngày hiện tại và trạng thái thanh toán (tt = 1)
        List<Donhang> donhangList = donhangservice.getDonhangByNgayAndTt(ngayHienTai, 1);

        // Tính tổng tiền
        float tongTien = 0;

        for (Donhang donhang : donhangList) {
            List<DonhangChitiet> donhangChitietList = donhangchitietservice.getDonhangChitietBymadh(donhang.getMadh());
            for (DonhangChitiet donhangChitiet : donhangChitietList) {
                Sanpham sanpham = sanphamService.getSanpham(donhangChitiet.getMasp());
                tongTien += donhangChitiet.getSl() * sanpham.getGiaban();
            }
        }
        Doanhthu doanhthu = doanhthuservice.getAll(ngayHienTai);
        if(doanhthu != null) {
        	doanhthu.setTien1n(tongTien);
        	doanhthuservice.update(doanhthu);
        }
        else {
        	doanhthu = new Doanhthu();
        	doanhthu.setTien1n(tongTien);
        	doanhthu.setNgay(ngayHienTai);
        	doanhthuservice.save(doanhthu);
        }

        // Đưa tổng tiền vào model để hiển thị trên giao diện
        model.addAttribute("tongTien", tongTien);
    }
	
	 public void giohang(int makh, Model model) {
	        List<Sanpham> SP = new ArrayList<>();
	        List<DonhangChitiet> sl_ctsp = new ArrayList<>();
	        double totalPrice = 0;
	        int sl = 0;
	        Set<Sanpham> uniqueProducts = new HashSet<>();
	        List<Donhang> donhang = donhangservice.getmakh(makh);
	        for (Donhang dh : donhang) {
	            if (dh != null && dh.getTt() == 0) {
	                List<DonhangChitiet> dhct = donhangchitietservice.getDonhangChitietBymadh(dh.getMadh());
	                for (DonhangChitiet DhCt : dhct) {
	                    Sanpham s = sanphamService.getSanpham(DhCt.getMasp());
	                    SP.add(s);
	                    sl_ctsp.add(DhCt);
	                    totalPrice += DhCt.getSl() * s.getGiaban();
	                    sl += DhCt.getSl();
	                    uniqueProducts.add(s); // Thêm mã sản phẩm vào set
	                }
	            }
	        }
	        int differentProductCount = uniqueProducts.size(); // Số lượng sản phẩm khác nhau
	        model.addAttribute("sl", sl); // Thêm số lượng sản phẩm vào model
	        model.addAttribute("tongmasp", differentProductCount); // Thêm tổng masp vào model
	        model.addAttribute("totalPrice", totalPrice);
	        model.addAttribute("slsp", sl_ctsp);
	        model.addAttribute("SanphamInCart", SP);
	    }
	
	
	@GetMapping("/login")
	public String myPage() {
        return "dangnhap"; 
    }
	@PostMapping("/login")
	public String login(@RequestParam("user") String user,
            @RequestParam("password") String password,
            Model model, HttpSession session) {
			Optional<String> tp = taikhoanService.checkAccount(user, password);
			if (tp.isPresent()) {
				Taikhoan account =  taikhoanService.getAccountUser(user);
				session.setAttribute("session", account);
			    model.addAttribute("userName",account.getUser());
				System.out.print(tp.get());
			    switch (tp.get()) {
			    case "admin":
			    	 tinhTongTien(model);
			    	 tinhtongsp(model);
			        return "redirect:/quanlytaikhoan/giaodienadmin";
			    case "nhân viên":
			    	tinhTongTien(model);
			    	tinhtongsp(model);
			        return "redirect:/quanlytaikhoan/giaodiennhanvien";
			    case "khách hàng":
			    	List<Sanpham> dssp = sanphamService.getAllSanpham();

			        // Loại bỏ các phần tử trùng lặp dựa trên loaisp
			        Set<String> loaispSet = dssp.stream()
			                                    .map(Sanpham::getLoaisp)
			                                    .collect(Collectors.toSet());
			        List<Sanpham> uniqueSanpham = dssp.stream()
			                                          .filter(sp -> loaispSet.remove(sp.getLoaisp()))
			                                          .collect(Collectors.toList());
				    model.addAttribute("qlsp",uniqueSanpham);
					model.addAttribute("dssp",dssp);
			        return "redirect:/quanlytaikhoan/giaodienkhachhang";
			    default:
			        return "redirect:/quanlytaikhoan/giaodienkhachhang";
			}
			} else {
			return "dangnhap";
			}
		}
	
	@GetMapping("/logout")
	public String out(Model model, HttpSession session) {    
	    Taikhoan userSession = (Taikhoan) session.getAttribute("session");
	    Optional<String> tp = taikhoanService.checkAccount(userSession.getUser(),userSession.getPassword());
		if (tp.isPresent()) {
			session.invalidate();
		    model.addAttribute("userName","none");
		    switch (tp.get()) {
		    case "admin":
		    	 tinhTongTien(model);
		        return "giaodienadmin";
		    case "nhân viên":
		        return "giaodiennhanvien";
		    case "khách hàng":
		    	List<Sanpham> dssp = sanphamService.getAllSanpham();

		        // Loại bỏ các phần tử trùng lặp dựa trên loaisp
		        Set<String> loaispSet = dssp.stream()
		                                    .map(Sanpham::getLoaisp)
		                                    .collect(Collectors.toSet());
		        List<Sanpham> uniqueSanpham = dssp.stream()
		                                          .filter(sp -> loaispSet.remove(sp.getLoaisp()))
		                                          .collect(Collectors.toList());
			    model.addAttribute("qlsp",uniqueSanpham);
				model.addAttribute("dssp",dssp);
		        return "giaodienkhachhang";
		    default:
		        return "giaodienkhachhang";
		}
		} else {
		return "dangnhap";
		}
	}
	@GetMapping("/giaodienkhachhang")
	public String giaodienkhachhang(Model model, HttpSession session) {
	    Taikhoan userSession = (Taikhoan) session.getAttribute("session");
	    if (userSession == null) {
		    model.addAttribute("userName","none");
	    }
	    else {
	        model.addAttribute("userName",userSession.getUser());
	        model.addAttribute("makh",userSession.getMakh());
	        int makh = userSession.getMakh();
	        giohang(makh,model);
	    }


		 List<Sanpham> dssp = sanphamService.getAllSanpham();

	        // Loại bỏ các phần tử trùng lặp dựa trên loaisp
	        Set<String> loaispSet = dssp.stream()
	                                    .map(Sanpham::getLoaisp)
	                                    .collect(Collectors.toSet());
	        List<Sanpham> uniqueSanpham = dssp.stream()
	                                          .filter(sp -> loaispSet.remove(sp.getLoaisp()))
	                                          .collect(Collectors.toList());
	    model.addAttribute("qlsp",uniqueSanpham);
		model.addAttribute("dssp",dssp);
        return "giaodienkhachhang"; 
    }
	@GetMapping("/giaodienadmin")
	public String giaodienadmin(Model model) {
		tinhTongTien(model);
		tinhtongsp(model);
        return "giaodienadmin"; 
    }
	@GetMapping("/giaodiennhanvien")
	public String giaodiennhanvien(Model model) {
		tinhTongTien(model);
		tinhtongsp(model);
        return "giaodiennhanvien"; 
    }
	@GetMapping
	public String listAccounts(Model model) {
		List<Taikhoan> accounts = taikhoanService.getAllAccounts();
        model.addAttribute("qltk", accounts);
        return "quanlytaikhoan";
	}
	@GetMapping("/new")
	public String createAccountForm(Model model) {
	// create student object to hold student form data
	Taikhoan tk = new Taikhoan();
	model.addAttribute("qltk", tk);
	return "themtaikhoan";
	}
	@PostMapping("/add")
	public String saveAccount(@ModelAttribute("qltk") Taikhoan taikhoan, Model model) {
	    if (taikhoan.getThanphan() == null || taikhoan.getThanphan().isEmpty()) {
	        taikhoan.setThanphan("nhân viên");
	    }

	    if (taikhoan.getMakh() == 0) { // Kiểm tra makh có giá trị 0 (kiểu int)
	        taikhoan.setMakh(2); // Thiết lập mặc định là 2 nếu makh = 0
	    }

	    taikhoanService.saveAccount(taikhoan);

	    // Lấy lại danh sách tài khoản sau khi đã lưu thành công
	    List<Taikhoan> accounts = taikhoanService.getAllAccounts();
	    model.addAttribute("qltk", accounts);

	    return "redirect:/quanlytaikhoan";
	}
	@GetMapping("/{id}")
	public String deleteAccount(@PathVariable("id") String id) {
		taikhoanService.deleteAccountUser(id);
	return "redirect:/quanlytaikhoan";
	}
	
	@GetMapping("/edit/{id}")
	public String editAccountForm(@PathVariable("id") String id, Model model) {
	model.addAttribute("qltk", taikhoanService.getAccountUser(id));
	return "suataikhoan";
	}
	@PostMapping("/{id}")
	public String updateAccount(@PathVariable("id") String id,
	@ModelAttribute("qltk") Taikhoan taikhoan,
	Model model) {
		
	// get student from database by id
		Taikhoan existingAccount = taikhoanService.getAccountUser(id);
		existingAccount.setUser(id);
		existingAccount.setPassword(taikhoan.getPassword());
		existingAccount.setManv(taikhoan.getManv());
	// save updated student object
		taikhoanService.updateAccount(existingAccount);
	return "redirect:/quanlytaikhoan";
	}
	@PostMapping("/timkiem")
	public String SearchTaikhoan(@RequestParam(value ="search", required = false) String user,Model model) {
		List<Taikhoan> dstk;
	    if (user == null || user.isEmpty()) {
	        // Nếu user rỗng hoặc null, lấy toàn bộ danh sách tài khoản
	        dstk = taikhoanService.getAllAccounts();
	    } else {
	        // Nếu user có giá trị, tìm kiếm tài khoản theo user
	        dstk = taikhoanService.searchUser(user);
	    }
	    model.addAttribute("qltk", dstk);
	    return "quanlytaikhoan";
	}
	
	@PostMapping("/dangky")
	public String createTaikhoan(@RequestParam("user") String user,
	                             @RequestParam("password") String pass,
	                             @RequestParam("tenkh") String tenkh,
	                             @RequestParam("dckh") String diachikh,
	                             @RequestParam("sdt") int sdt,
	                             Model model) {
		
	    // Create a new Khachhang object and set its properties
	    Khachhang khachhang = new Khachhang();
	    khachhang.setTenkh(tenkh);
	    khachhang.setDiachikh(diachikh);
	    khachhang.setSdt(sdt);
	    Khachhang savedKhachhang = KhachhangService.saveKhachhang(khachhang);
        int generatedMakh = savedKhachhang.getMakh();
	    
	    Taikhoan taikhoan = new Taikhoan();
	    taikhoan.setUser(user);
	    taikhoan.setPassword(pass);
	    taikhoan.setMakh(generatedMakh);

	    // Kiểm tra và cài đặt mặc định cho thanh phần nếu cần
	    if (taikhoan.getThanphan() == null || taikhoan.getThanphan().isEmpty()) {
	        taikhoan.setThanphan("khách hàng");
	    }
	    
	    // Kiểm tra và xử lý trường hợp null cho Manv
	    if (taikhoan.getManv() != null && taikhoan.getManv().isEmpty()) {
	        taikhoan.setManv(null);
	    }

	    // Save the Taikhoan and associated Khachhang
	    taikhoanService.saveAccount(taikhoan);

	    // Redirect to the login page after successful registration
	    return "dangnhap";
	}
	
	@PostMapping("/sanpham")
	public String danhSachSanPham(@RequestParam("data") String tensp,Model model, HttpSession session) {
		 Taikhoan userSession = (Taikhoan) session.getAttribute("session");
		    if (userSession == null) {
			    model.addAttribute("userName","none");
		    }
		    else {
		        model.addAttribute("userName",userSession.getUser());
		    }

	 // Kiểm tra tensp không null hoặc rỗng
	    if (tensp != null && !tensp.isEmpty()) {
	        List<Sanpham> s = sanphamService.getListTensp(tensp);
	        model.addAttribute("dssp", s);
	    } else {
	        model.addAttribute("dssp", sanphamService.getAllSanpham());
	    }
	    List<Sanpham> dssp = sanphamService.getAllSanpham();

        // Loại bỏ các phần tử trùng lặp dựa trên loaisp
        Set<String> loaispSet = dssp.stream()
                                    .map(Sanpham::getLoaisp)
                                    .collect(Collectors.toSet());
        List<Sanpham> uniqueSanpham = dssp.stream()
                                          .filter(sp -> loaispSet.remove(sp.getLoaisp()))
                                          .collect(Collectors.toList());
        model.addAttribute("qlsp",uniqueSanpham);
	    // Các thuộc tính khác của model
	    return "giaodienkhachhang";
	}


	@GetMapping("/chitiet/{masp}")
	public String getttsp(@PathVariable("masp") String masp, Model model,HttpSession session) {
	    Taikhoan userSession = (Taikhoan) session.getAttribute("session");
	    if (userSession == null) {
		    model.addAttribute("userName","none");
	    }
	    else {
	        model.addAttribute("userName",userSession.getUser());
	        model.addAttribute("makh",userSession.getMakh());
	        int makh = userSession.getMakh();
	        giohang(makh,model);
	    }

		List<Sanpham> dssp = sanphamService.getAllSanpham();

        // Loại bỏ các phần tử trùng lặp dựa trên loaisp
        Set<String> loaispSet = dssp.stream()
                                    .map(Sanpham::getLoaisp)
                                    .collect(Collectors.toSet());
        List<Sanpham> uniqueSanpham = dssp.stream()
                                          .filter(sp -> loaispSet.remove(sp.getLoaisp()))
                                          .collect(Collectors.toList());
	    model.addAttribute("qlsp",uniqueSanpham);
		model.addAttribute("ctsp", sanphamService.getSanpham(masp));		
	return "chitiet";
    }
	
	@PostMapping("/add/{masp}")
	public String addToCart(@PathVariable("masp") String masp,
	                        @RequestParam("soluong") int soluong,
	                        HttpSession session, Model model) {
	    Taikhoan usersession = (Taikhoan) session.getAttribute("session");
	    if (usersession == null) {
	        return "redirect:/quanlytaikhoan/login";
	    }

	    int makh = usersession.getMakh();
	    Date ngayHienTai = Date.valueOf(LocalDate.now());

	    List<Donhang> donhang = donhangservice.getmakh(makh, ngayHienTai);
	    Donhang dh = null;
	    for (Donhang dhTemp : donhang) {
	        if (dhTemp.getTt() == 0) {
	            dh = dhTemp;
	            break;
	        }
	    }

	    if (dh == null) {
	        // Nếu không có đơn hàng chưa duyệt, tạo mới đơn hàng
	        dh = new Donhang();
	        dh.setMakh(makh);
	        dh.setNgay(ngayHienTai);
	        dh.setTt(0);
	        dh = donhangservice.saveDonhang(dh);
	    }

	    // Xử lý chi tiết đơn hàng
	    DonhangChitiet existingDHCT = donhangchitietservice.getDonhangChitiet(dh.getMadh(), masp);
	    if (existingDHCT != null) {
	        existingDHCT.setSl(existingDHCT.getSl() + soluong);
	        donhangchitietservice.saveDonhangChitiet(existingDHCT);
	    } else {
	        DonhangChitiet dhct = new DonhangChitiet();
	        dhct.setMadh(dh.getMadh());
	        dhct.setMasp(masp);
	        dhct.setSl(soluong);
	        donhangchitietservice.saveDonhangChitiet(dhct);
	    }

	    giohang(makh,model);
	    return "redirect:/quanlytaikhoan/giohang";
	}

	
	@GetMapping("/giohang")
	public String giohang(Model model,HttpSession session) {
		Taikhoan usersession = (Taikhoan) session.getAttribute("session");
		if (usersession == null) {
		    return "redirect:/quanlytaikhoan/login";
		}
        model.addAttribute("userName",usersession.getUser());
	    model.addAttribute("log","login");
	    Taikhoan getusersession = (Taikhoan) session.getAttribute("session");
		int makh = getusersession.getMakh();
		
		giohang(makh,model);
		    return "/giohang";
    }	
	
	@GetMapping("/delete/{masp}")
	public String del(@PathVariable("masp") String masp, Model model,HttpSession session) {
		Taikhoan usersession = (Taikhoan) session.getAttribute("session");
		if (usersession == null) {
		        return "redirect:/quanlytaikhoan/login";
		}
		model.addAttribute("userName",usersession.getMakh());
	    model.addAttribute("log","login");
		Sanpham SP = new Sanpham();
		DonhangChitiet dHcT = new DonhangChitiet();
		Taikhoan getusersession = (Taikhoan) session.getAttribute("session");
		int makh = getusersession.getMakh();
		List<Donhang> donhang = donhangservice.getmakh(makh);
		double totalPrice = 0;
		
	    for(Donhang dh: donhang) {
			if(dh != null && dh.getTt() == 0){
				List<DonhangChitiet> dhct = donhangchitietservice.getDonhangChitietBymadh(dh.getMadh());
				if (dhct != null && dhct.size() > 1) {
			        // Nếu có nhiều hơn 1 DonhangChitiet, chỉ xóa DonhangChitiet
			        donhangchitietservice.deleteDonhangChitiet(dh.getMadh(), masp);
			    } else if (dhct != null && dhct.size() == 1) {
			        // Nếu chỉ có 1 DonhangChitiet, xóa cả Donhang và DonhangChitiet
			    	donhangservice.deleteDonhang(dh.getMadh());
			        donhangchitietservice.deleteDonhangChitiet(dh.getMadh(), masp);
			    }
				// Tính lại tổng giá trị của giỏ hàng
	            dhct = donhangchitietservice.getDonhangChitietBymadh(dh.getMadh()); // Lấy lại chi tiết đơn hàng sau khi xóa
	            for (DonhangChitiet DhCt : dhct) {
	                Sanpham s = sanphamService.getSanpham(DhCt.getMasp());
	                totalPrice += DhCt.getSl() * s.getGiaban(); // Cộng dồn tổng giá trị
	            }
			}
	    }
	    model.addAttribute("totalPrice", totalPrice); // Thêm tổng giá trị vào model
	    return "redirect:/quanlytaikhoan/giohang";
	}
}
