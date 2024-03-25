package com.QLTB.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperCTPhieuMuon  implements RowMapper<CTPhieuMuon>{
	@Override
	public CTPhieuMuon mapRow(ResultSet rs, int rowNum) throws SQLException {
		CTPhieuMuon pm = new CTPhieuMuon();
		pm.setMaPhieuMuon(rs.getString("MAPHIEUMUON"));
		pm.setLoaiTB(rs.getString("LOAITBI"));
		pm.setMaPhong(rs.getString("MAPHONG"));
		pm.setMaTB(rs.getString("MaTBi"));	
		pm.setTenLoaiTB(rs.getString("TENLOAI"));
		pm.setTrangThai(rs.getInt("TRANGTHAI"));
		
		return pm;
		
	}
}
