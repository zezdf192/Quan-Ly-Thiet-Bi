package com.QLTB.Entity;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String CMND;
	private String email;
	private String sdt;
	private String taiKhoan;
	private int dangLamViec;
	
	public NhanVien() {
		super();
	}
	
	
	
	public NhanVien(String maNV, String tenNV, String cMND, String email, String sdt, String taiKhoan,
			int dangLamViec) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		CMND = cMND;
		this.email = email;
		this.sdt = sdt;
		this.taiKhoan = taiKhoan;
		this.dangLamViec = dangLamViec;
	}



	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNv) {
		this.maNV = maNv;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public int getDangLamViec() {
		return dangLamViec;
	}
	public void setDangLamViec(int dangLamViec) {
		this.dangLamViec = dangLamViec;
	}
	
	
}