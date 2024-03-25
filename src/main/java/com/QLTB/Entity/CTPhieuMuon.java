package com.QLTB.Entity;

public class CTPhieuMuon {
	private String maPhieuMuon;
	private String maTB;
	private int trangThai;
	private String loaiTB;
	private String maPhong;
	private String tenLoaiTB;
	
	public CTPhieuMuon() {}
	
	
	public CTPhieuMuon(String maPhieuMuon, String maTB, int trangThai, String loaiTB, String maPhong,
			String tenLoaiTB) {
		
		this.maPhieuMuon = maPhieuMuon;
		this.maTB = maTB;
		this.trangThai = trangThai;
		this.loaiTB = loaiTB;
		this.maPhong = maPhong;
		this.tenLoaiTB = tenLoaiTB;
	}
	public String getMaPhieuMuon() {
		return maPhieuMuon;
	}
	public void setMaPhieuMuon(String maPhieuMuon) {
		this.maPhieuMuon = maPhieuMuon;
	}
	public String getMaTB() {
		return maTB;
	}
	public void setMaTB(String maTB) {
		this.maTB = maTB;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public String getLoaiTB() {
		return loaiTB;
	}
	public void setLoaiTB(String loaiTB) {
		this.loaiTB = loaiTB;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getTenLoaiTB() {
		return tenLoaiTB;
	}
	public void setTenLoaiTB(String tenLoaiTB) {
		this.tenLoaiTB = tenLoaiTB;
	}
	
	
}
