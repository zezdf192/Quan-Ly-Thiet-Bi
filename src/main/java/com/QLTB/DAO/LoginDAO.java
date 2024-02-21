package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.MapperPhong;
import com.QLTB.Entity.MapperTaiKhoan;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.TaiKhoan;

@Repository
public class LoginDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
	
	public List<TaiKhoan> getTaiKhoan(String TK) {
	    List<TaiKhoan> list = new ArrayList<TaiKhoan>();
	    String sql = "SELECT * FROM TAIKHOAN WHERE TENTAIKHOAN = ?";
	    
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{TK}, new MapperTaiKhoan());
	    } catch (DataAccessException e) {
	        // Xử lý lỗi nếu cần thiết
	        e.printStackTrace();
	    }
	    
	    return list;
	}
	
	public boolean checkPassword(String TK, String password) {
		String sql = "SELECT * FROM TAIKHOAN WHERE TENTAIKHOAN = ? AND MATKHAU = ?";
		 List<TaiKhoan> list = new ArrayList<TaiKhoan>();
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{TK, password}, new MapperTaiKhoan());
	    } catch (DataAccessException e) {
	        // Xử lý lỗi nếu cần thiết
	        e.printStackTrace();
	    }
	    
	    if(list.size() == 0) return false;
	    return true;
	}
	
	public String loginMethod(String TK, String password) {
		
		List<TaiKhoan> list = getTaiKhoan(TK);
		
		if(list.size() == 0) {
			return "Tai khoan khong ton tai, vui long kiem tra lai";
		}else {
			boolean check  = checkPassword(TK, password);
			if(check) {
				return "Success";
			}else return "Mat khau khong chinh xac";
		}
		
	}

}
