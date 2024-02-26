package com.QLTB.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperPhong implements RowMapper<Phong> {

	@Override
	public Phong mapRow(ResultSet rs, int rowNum) throws SQLException {
		Phong phong = new Phong();
		phong.setMaPhong(rs.getString("MAPHONG"));
		phong.setTrangThai(rs.getInt("trangthai"));
		phong.setTenTinhTrang(rs.getString("TenTinhTrang"));
		return phong;
	}

}
