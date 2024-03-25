package com.QLTB.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperLoaiTB implements RowMapper<LoaiTB> {
	@Override
	public LoaiTB mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoaiTB loaiTB = new LoaiTB();
		loaiTB.setMaLoai(rs.getString("MALOAI"));
		loaiTB.setTenLoai(rs.getString("TENLOAI"));
		
		return loaiTB;
	}
}
