package com.QLTB.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperTaiKhoan implements RowMapper<TaiKhoan>{
	@Override
	public TaiKhoan mapRow(ResultSet rs, int rowNum) throws SQLException {
		TaiKhoan tk = new TaiKhoan();
		tk.setTenTaiKhoan(rs.getString("TENTAIKHOAN"));
		tk.setMatKhau(rs.getString("MATKHAU"));
		tk.setLoaiTaiKhoan(rs.getString("LOAITAIKHOAN"));
		return tk;
	}
}
