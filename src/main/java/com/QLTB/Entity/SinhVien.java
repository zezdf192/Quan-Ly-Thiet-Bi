package com.QLTB.Entity;

public class SinhVien {
	private String maSV;
	private String tenSV;
	private String lop;
	private String taiKhoan;
	
	public SinhVien() {}
	
	public SinhVien(String maSV, String tenSV, String lop, String taiKhoan) {
		super();
		this.maSV = maSV;
		this.tenSV = tenSV;
		this.lop = lop;
		this.taiKhoan = taiKhoan;
	}
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getTenSV() {
		return tenSV;
	}
	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
	
}
