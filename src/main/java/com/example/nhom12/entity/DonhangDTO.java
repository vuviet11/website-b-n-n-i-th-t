package com.example.nhom12.entity;

public class DonhangDTO {
	 	private Donhang donhang;
	    private DonhangChitiet donhangChitiet;
	    private Sanpham sanpham;

	    public DonhangDTO(Donhang donhang, DonhangChitiet donhangChitiet,Sanpham sanpham) {
	        this.donhang = donhang;
	        this.donhangChitiet = donhangChitiet;
	        this.sanpham = sanpham;
	    }

	    public Sanpham getSanpham() {
			return sanpham;
		}

		public void setSanpham(Sanpham sanpham) {
			this.sanpham = sanpham;
		}

		public DonhangDTO() {
			// TODO Auto-generated constructor stub
		}

		public Donhang getDonhang() {
	        return donhang;
	    }

	    public void setDonhang(Donhang donhang) {
	        this.donhang = donhang;
	    }

	    public DonhangChitiet getDonhangChitiet() {
	        return donhangChitiet;
	    }

	    public void setDonhangChitiet(DonhangChitiet donhangChitiet) {
	        this.donhangChitiet = donhangChitiet;
	    }

}
