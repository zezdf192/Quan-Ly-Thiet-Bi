package com.QLTB.Entity;

public class TaiKhoan {
	private String tenTaiKhoan;
	private String matKhau;
	private String loaiTaiKhoan;
	
	
	public TaiKhoan(String tenTaiKhoan, String matKhau, String loaiTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.loaiTaiKhoan = loaiTaiKhoan;
	}
	
	public TaiKhoan() {
		
	}
	
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}
	public void setLoaiTaiKhoan(String loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
	}
	
	
}
