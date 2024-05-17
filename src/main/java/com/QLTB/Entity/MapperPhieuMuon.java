package com.QLTB.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

public class MapperPhieuMuon implements RowMapper<PhieuMuon>{

	@Override
	public PhieuMuon mapRow(ResultSet rs, int rowNum) throws SQLException {
		PhieuMuon pm = new PhieuMuon();
		pm.setMaPhieuMuon(rs.getString("MAPM"));
		pm.setMaPhong(rs.getString("MAPHG"));
		 LocalDateTime thoiDiemMuon = rs.getTimestamp("THOIDIEMMUON").toLocalDateTime();
	        pm.setThoiDiemMuon(thoiDiemMuon);
	        LocalDateTime hanTra = rs.getTimestamp("HANTRA").toLocalDateTime();
	        pm.setHanTra(hanTra);
	        // Sử dụng Timestamp cho datetime
//	        LocalDateTime thoiDiemTra = rs.getTimestamp("THOIDIEMTRA").toLocalDateTime();
//	        pm.setThoiDiemTra(thoiDiemTra);
		pm.setMaSVMuon(rs.getString("SINHVIENMUON"));
		pm.setMaNVLap(rs.getString("NGUOILAPPHIEU"));
		return pm;
	}
	
}
