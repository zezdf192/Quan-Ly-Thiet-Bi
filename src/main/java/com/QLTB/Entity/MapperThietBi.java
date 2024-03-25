package com.QLTB.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperThietBi implements RowMapper<ThietBi> {
	@Override
	public ThietBi mapRow(ResultSet rs, int rowNum) throws SQLException {
		ThietBi thietBi = new ThietBi();
		thietBi.setMaPhong(rs.getString("MAPHONG"));
		thietBi.setLoaiTB(rs.getString("LOAITBI"));
		thietBi.setMaTB(rs.getString("MATBI"));
		thietBi.setTenTB(rs.getString("TENLOAI"));
		thietBi.setTinhTrangTB(rs.getInt("TINHTRANGTB"));
		thietBi.setTenTinhTrang(rs.getString("TenTinhTrang"));
		return thietBi;
	}
}
