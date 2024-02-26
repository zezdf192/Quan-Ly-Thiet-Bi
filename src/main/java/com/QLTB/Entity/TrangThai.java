package com.QLTB.Entity;

public class TrangThai {
	private int maTinhTrang;
	private String tenTinhTrang;
	
	
	public TrangThai(int maTinhTrang, String tenTinhTrang) {
		
		this.maTinhTrang = maTinhTrang;
		this.tenTinhTrang = tenTinhTrang;
	}
	
	public TrangThai() {
		
		
	}
	public int getMaTinhTrang() {
		return maTinhTrang;
	}
	public void setMaTinhTrang(int maTinhTrang) {
		this.maTinhTrang = maTinhTrang;
	}
	public String getTenTinhTrang() {
		return tenTinhTrang;
	}
	public void setTenTinhTrang(String tenTinhTrang) {
		this.tenTinhTrang = tenTinhTrang;
	}

	
	
}
