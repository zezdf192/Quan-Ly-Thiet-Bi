package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.MapperPhong;
import com.QLTB.Entity.MapperSinhVien;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.SinhVien;

@Repository
public class SinhVienDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
public List<SinhVien> getSinhVien(String maSV) {
		
	    List<SinhVien> list = new ArrayList<SinhVien>();
	    String sql = "select * from SINHVIEN where MASV = ?";
	    
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{maSV}, new MapperSinhVien());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
	    
	    return list;
	}
}
