package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.QLTB.Entity.MapperPhong;
import com.QLTB.Entity.Phong;


@Repository
//@Service
public class HomeDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
	
	public List<Phong> getDataPhong() {
		List<Phong> list = new ArrayList<Phong>();
		String sql = "select * from PHONG";
		list = _jdbcTemplate.query(sql, new MapperPhong());
		return list;
	}
		
}
