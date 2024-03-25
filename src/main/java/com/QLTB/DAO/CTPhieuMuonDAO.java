package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.CTPhieuMuon;
import com.QLTB.Entity.MapperCTPhieuMuon;
import com.QLTB.Entity.MapperPhieuMuon;
import com.QLTB.Entity.MapperSinhVien;
import com.QLTB.Entity.MapperTrangThai;
import com.QLTB.Entity.PhieuMuon;
import com.QLTB.Entity.SinhVien;

@Repository
public class CTPhieuMuonDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
	
	public List<CTPhieuMuon> getAllCTPhieuMuonChuaTra(String maPM) {
		List<CTPhieuMuon> list = new ArrayList<CTPhieuMuon>();
		String sql = "SELECT  CTPHIEUMUON.*, \r\n"
				+ "    THIETBI.*,\r\n"
				+ "	LOAITB.TENLOAI\r\n"
				+ "FROM \r\n"
				+ "    CTPHIEUMUON \r\n"
				+ "JOIN \r\n"
				+ "    THIETBI ON THIETBI.MATBI = CTPHIEUMUON.MaTB\r\n"
				+ "Join LOAITB ON LOAITB.MALOAI = THIETBI.LOAITBI \r\n"
				+ "WHERE \r\n"
				+ "    CTPHIEUMUON.MAPHIEUMUON = ? and CTPHIEUMUON.TRANGTHAI =  3";
		try {
	        list = _jdbcTemplate.query(sql, new Object[]{maPM}, new MapperCTPhieuMuon());
	     
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return list;
	}
	
	public List<CTPhieuMuon> getAllCTPhieuMuonDaTra(String maPM) {
		List<CTPhieuMuon> list = new ArrayList<CTPhieuMuon>();
		String sql = "SELECT  CTPHIEUMUON.*, \r\n"
				+ "    THIETBI.*,\r\n"
				+ "	LOAITB.TENLOAI\r\n"
				+ "FROM \r\n"
				+ "    CTPHIEUMUON \r\n"
				+ "JOIN \r\n"
				+ "    THIETBI ON THIETBI.MATBI = CTPHIEUMUON.MaTB\r\n"
				+ "Join LOAITB ON LOAITB.MALOAI = THIETBI.LOAITBI \r\n"
				+ "WHERE \r\n"
				+ "    CTPHIEUMUON.MAPHIEUMUON = ? and CTPHIEUMUON.TRANGTHAI =  4";
		try {
	        list = _jdbcTemplate.query(sql, new Object[]{maPM}, new MapperCTPhieuMuon());
	     
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return list;
	}
	
	public PhieuMuon getPMByMaPM(String maPM) {
		List<PhieuMuon> list = new ArrayList<PhieuMuon>();
		String sql = "select PHIEUMUON.* from PHIEUMUON \r\n"
				+ "where  PHIEUMUON.MAPM = ?";
		try {
	        list = _jdbcTemplate.query(sql, new Object[]{maPM}, new MapperPhieuMuon());
	     
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		
		if(list.size() > 0) return list.get(0);
		return null;
	}
}
