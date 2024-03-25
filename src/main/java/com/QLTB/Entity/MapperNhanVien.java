package com.QLTB.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperNhanVien implements RowMapper<NhanVien> {

	@Override
	public NhanVien mapRow(ResultSet rs, int rowNum) throws SQLException {
		NhanVien nv = new NhanVien();
		nv.setMaNV(rs.getString("MANV"));
		nv.setTenNV(rs.getString("TENNV"));
		nv.setCMND(rs.getString("CMND"));
		nv.setEmail(rs.getString("EMAIL"));
		nv.setSdt(rs.getString("SDT"));
		nv.setTaiKhoan(rs.getString("TAIKHOAN"));
		nv.setDangLamViec(rs.getByte("DANGLAMVIEC"));
		return nv;
	}
}