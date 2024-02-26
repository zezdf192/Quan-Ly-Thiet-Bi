package com.QLTB.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperTrangThai implements RowMapper<TrangThai> {
	@Override
	public TrangThai mapRow(ResultSet rs, int rowNum) throws SQLException {
		TrangThai tt = new TrangThai();
		tt.setMaTinhTrang(rs.getInt("MaTinhTrang"));
		tt.setTenTinhTrang(rs.getString("TenTinhTrang"));
		return tt;
	}
}


