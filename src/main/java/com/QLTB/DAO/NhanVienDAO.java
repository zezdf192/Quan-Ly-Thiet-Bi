package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.MapperNhanVien;
import com.QLTB.Entity.MapperPhong;
import com.QLTB.Entity.NhanVien;

@Repository
public class NhanVienDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
	public List<NhanVien> getAllNhanVien() {
		List<NhanVien> listNV = new ArrayList<>();
		String sql = "select * from NHANVIEN";
		try {
	        listNV = _jdbcTemplate.query(sql, new MapperNhanVien());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return listNV;
	}
	
	public List<NhanVien> getNvBangMaNV(String maNV) {
		List<NhanVien> listNV = new ArrayList<>();
		String sql = "select * from NHANVIEN where MANV=?";
		
		try {
	        listNV = _jdbcTemplate.query(sql, new Object[]{maNV}, new MapperNhanVien());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return listNV;
	}
	
	public String themMoiNv(String maNv, String tenNv, String cmnd, String email, String sdt, String taikhoan) {
		String sql = "INSERT INTO NHANVIEN (MANV, TENNV, CMND, EMAIL, SDT, TAIKHOAN, DANGLAMVIEC) "
				+ "VALUES (?, ?, ?, ?, ?, ?, 1)";
		try {
			_jdbcTemplate.update(sql, maNv, tenNv, cmnd, email, sdt, taikhoan);
		} catch(DataAccessException e) {
			e.printStackTrace();
	        return "Lỗi hệ thống";
		}
		List<NhanVien> list = getNvBangMaNV(maNv);
		if (list.size()>0) {
			return "Ma NV da ton tai";
		}
		
		return "Success";
	}
	
	public void capNhatNv(String maNv, String tenNv, String email, String sdt, String taikhoan, byte lamviec) {
		String sql = "UPDATE PHONG "
				+ "SET TENNV=?, EMAIL=?, SDT=?, TAIKHOAN=?, LAMVIEC=? WHERE MANV=?";
		try {
	        _jdbcTemplate.update(sql, tenNv, email, sdt, taikhoan, lamviec, maNv);
	    } catch (DataAccessException e) {
	       
	        e.printStackTrace();
	    }
	}
	
	public String xoaNv(String maNv) {
		String sql = "DELETE FROM NHANVIEN WHERE MANV=?";
		try {
		    _jdbcTemplate.update(sql, maNv);
		} catch (DataAccessException e) {
		    e.printStackTrace();
		}
		return "Xóa nhân viên thành công";
	}
	
	public List<NhanVien> timKiemNv(String input) {
		List<NhanVien> listNV = new ArrayList<>();
		String sql = "SELECT * FROM NHANVIEN WHERE MANV LIKE ?";
		
		try {
		       
	        String inputPattern = "%" + input + "%";
	        listNV = _jdbcTemplate.query(sql, new Object[]{inputPattern}, new MapperNhanVien());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return listNV;
	}
}
