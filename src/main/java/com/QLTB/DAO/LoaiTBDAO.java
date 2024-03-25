package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.LoaiTB;
import com.QLTB.Entity.MapperLoaiTB;
import com.QLTB.Entity.MapperTaiKhoan;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.TaiKhoan;

@Repository
public class LoaiTBDAO {

	@Autowired
	JdbcTemplate _jdbcTemplate;

	public List<LoaiTB> getLoaiThietBi() {
		List<LoaiTB> list = new ArrayList<LoaiTB>();
		String sql = "SELECT * FROM LOAITB ";

		try {
			list = _jdbcTemplate.query(sql, new Object[] {}, new MapperLoaiTB());
		} catch (DataAccessException e) {
			// Xử lý lỗi nếu cần thiết
			e.printStackTrace();
		}

		return list;
	}

	
	
	public String taoMoiLoaiTB(String maLoai) {
	
		
		String sql = "INSERT INTO LOAITB (MALOAI, TENLOAI) VALUES (?, ?)";
		   
	    try {
	        _jdbcTemplate.update(sql, maLoai, maLoai);
	    } catch (DataAccessException e) {
	        // Handle the exception if necessary
	        e.printStackTrace();
	        return "Lỗi hệ thống";
	    }
	    
	    return "Success";
	}

}
