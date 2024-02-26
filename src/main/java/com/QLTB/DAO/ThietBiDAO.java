package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.MapperPhong;
import com.QLTB.Entity.MapperThietBi;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.ThietBi;

@Repository
public class ThietBiDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
	
	
	
	public List<ThietBi> getThietBiBangMaPhong(String maPhong) {
		
	    List<ThietBi> list = new ArrayList<ThietBi>();
	    String sql = "select * from THIETBI where MAPHONG = ?";
	    
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{maPhong}, new MapperThietBi());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
	   
	    return list;
	}

}
