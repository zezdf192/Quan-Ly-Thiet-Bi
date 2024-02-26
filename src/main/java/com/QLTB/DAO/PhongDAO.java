package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.MapperPhong;
import com.QLTB.Entity.MapperTrangThai;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.TaiKhoan;
import com.QLTB.Entity.ThietBi;
import com.QLTB.Entity.TrangThai;

@Repository
public class PhongDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
	@Autowired
	ThietBiDAO thietBiDAO;
	
	public List<Phong> getPhong(String maPhong) {
		
	    List<Phong> list = new ArrayList<Phong>();
	    String sql = "select MAPHONG, TenTinhTrang, trangthai from PHONG join TRANGTHAI on phong.TRANGTHAI = TRANGTHAI.MaTinhTrang "
	            + "where PHONG.MAPHONG = ?";
	    
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{maPhong}, new MapperPhong());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
	    
	    return list;
	}


	
	public String themMoiPhong(String maPhong) {
	    String sql = "INSERT INTO PHONG (MAPHONG, TRANGTHAI) VALUES (?, 0)";
	   
	    try {
	        _jdbcTemplate.update(sql, maPhong);
	    } catch (DataAccessException e) {
	        // Handle the exception if necessary
	        e.printStackTrace();
	        return "Lỗi hệ thống";
	    }
	    
	    return "Success";
	}

	
	public String taoMoiPhong(String maPhong) {
		List<Phong> list = getPhong(maPhong);
		
		if(list.size() > 0) {
			return "Ma phong da ton tai";
		}else {
			String log = themMoiPhong(maPhong);
			return log;
		}
	}
	
	public int layMaTinhTrang(String tenTinhTrang) {
		List<TrangThai> list = new ArrayList<TrangThai>();
	    String sql = "SELECT * FROM TRANGTHAI WHERE TRANGTHAI = ?";
	    
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{tenTinhTrang}, new MapperTrangThai());
	    } catch (DataAccessException e) {
	        // Xử lý lỗi nếu cần thiết
	        e.printStackTrace();
	    }
	    System.out.println(tenTinhTrang);
	    System.out.println(list.get(0).getMaTinhTrang());
	    return list.get(0).getMaTinhTrang();
	}
	
	public void thayDoiPhong(String maTinhTrang, String maPhong) {
	    String sql = "UPDATE Phong SET TRANGTHAI = ? WHERE MAPHONG = ?;";

	    try {
	        _jdbcTemplate.update(sql, maTinhTrang, maPhong);
	    } catch (DataAccessException e) {
	       
	        e.printStackTrace();
	    }
	}
	
	public int kiemTraThietBiCuaPhong(String maPhong) {
		 List<ThietBi> list = new ArrayList<ThietBi>();
		list = thietBiDAO.getThietBiBangMaPhong(maPhong);
		
		return list.size();
	}
	
	public String xoaPhong(String maPhong) {
		int length = kiemTraThietBiCuaPhong(maPhong);
		
		if(length > 0) {
			return "Không được xóa phòng đã có thiết bị, vui lòng kiểm tra lại";
		}else {
			String sql = "DELETE FROM Phong WHERE MAPHONG = ?;";
			try {
			    _jdbcTemplate.update(sql, maPhong);
			} catch (DataAccessException e) {
			    e.printStackTrace();
			}
			return "Xóa phòng thành công";
		}
		

	}
	
	public List<Phong> timKiemTatCaPhong(String inputFind) {
	    List<Phong> list = new ArrayList<Phong>();
	    String sql = "SELECT MAPHONG, TenTinhTrang, trangthai FROM phong INNER JOIN TRANGTHAI "
	            + "ON phong.TRANGTHAI = TRANGTHAI.MaTinhTrang WHERE MAPHONG LIKE ?";
	    
	    try {
	       
	        String inputPattern = "%" + inputFind + "%";
	        list = _jdbcTemplate.query(sql, new Object[]{inputPattern}, new MapperPhong());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
	    
	    return list;
	}
	
	public List<Phong> timKiemPhong(String inputFind, int typeFind) {
		if(typeFind == 10) {
			return timKiemTatCaPhong(inputFind);
		}
	    List<Phong> list = new ArrayList<Phong>();
	    String sql = "SELECT MAPHONG, TenTinhTrang, trangthai FROM phong INNER JOIN TRANGTHAI "
	            + "ON phong.TRANGTHAI = TRANGTHAI.MaTinhTrang WHERE MAPHONG LIKE ? AND trangthai = ?";
	    
	    try {
	       
	        String inputPattern = "%" + inputFind + "%";
	        list = _jdbcTemplate.query(sql, new Object[]{inputPattern, typeFind}, new MapperPhong());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
	    
	    return list;
	}


}
