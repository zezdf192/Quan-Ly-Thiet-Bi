package com.QLTB.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperSinhVien implements RowMapper<SinhVien> {
	@Override
	public SinhVien mapRow(ResultSet rs, int rowNum) throws SQLException {
		SinhVien sv = new SinhVien();
		sv.setMaSV(rs.getString("MASV"));
		sv.setTenSV(rs.getString("TENSV"));
		sv.setLop(rs.getString("LOP"));
		sv.setTaiKhoan(rs.getString("TAIKHOAN"));
		return  sv;
	}
}
