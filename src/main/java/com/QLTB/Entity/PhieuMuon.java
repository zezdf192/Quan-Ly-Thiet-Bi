package com.QLTB.Entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PhieuMuon {
	private String maPhieuMuon;
	private String maPhong;
	private LocalDateTime  thoiDiemMuon;
	private LocalDateTime hanTra;
	//private LocalDateTime  thoiDiemTra;
	private String maSVMuon;
	private String maNVLap;
	public PhieuMuon(String maPhieuMuon, String maPhong, LocalDateTime  thoiDiemMuon,  LocalDateTime  hanTra,
			String maSVMuon, String maNVLap) {
		super();
		this.maPhieuMuon = maPhieuMuon;
		this.maPhong = maPhong;
		this.thoiDiemMuon = thoiDiemMuon;
		this.hanTra = hanTra;
		//this.thoiDiemTra = thoiDiemTra;
		this.maSVMuon = maSVMuon;
		this.maNVLap = maNVLap;
	}
	public PhieuMuon() {
		super();
	}
	public String getMaPhieuMuon() {
		return maPhieuMuon;
	}
	public void setMaPhieuMuon(String maPhieuMuon) {
		this.maPhieuMuon = maPhieuMuon;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public LocalDateTime  getThoiDiemMuon() {
		return thoiDiemMuon;
	}
	public void setThoiDiemMuon(LocalDateTime  thoiDiemMuon) {
		this.thoiDiemMuon = thoiDiemMuon;
	}
	
//	public LocalDateTime  getThoiDiemTra() {
//		return thoiDiemTra;
//	}
//	public void setThoiDiemTra(LocalDateTime  thoiDiemTra) {
//		this.thoiDiemTra = thoiDiemTra;
//	}
	
	
	
	public String getMaSVMuon() {
		return maSVMuon;
	}
	public LocalDateTime getHanTra() {
		return hanTra;
	}
	public void setHanTra(LocalDateTime hanTra) {
		this.hanTra = hanTra;
	}
	public void setMaSVMuon(String maSVMuon) {
		this.maSVMuon = maSVMuon;
	}
	public String getMaNVLap() {
		return maNVLap;
	}
	public void setMaNVLap(String maNVLap) {
		this.maNVLap = maNVLap;
	}
	
	
}
