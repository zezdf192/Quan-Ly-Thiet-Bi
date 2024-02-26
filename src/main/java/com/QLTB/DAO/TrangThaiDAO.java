package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.MapperPhong;
import com.QLTB.Entity.MapperTrangThai;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.TrangThai;

@Repository
public class TrangThaiDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
	
	public List<TrangThai> getTrangThai() {
	    List<TrangThai> list = new ArrayList<TrangThai>();
	    String sql = "SELECT * FROM TRANGTHAI";
	    list = _jdbcTemplate.query(sql, new MapperTrangThai());
	    
	    return list;
	}

}
