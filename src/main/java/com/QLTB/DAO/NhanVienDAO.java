package com.QLTB.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.MapperNhanVien;
import com.QLTB.Entity.MapperPhong;
import com.QLTB.Entity.MapperTaiKhoan;
import com.QLTB.Entity.NhanVien;
import com.QLTB.Entity.TaiKhoan;

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
	
	public String themMoiNv(String maNv, String tenNv, String cmnd, String email, String sdt) {
		String sql = "INSERT INTO NHANVIEN (MANV, TENNV, CMND, EMAIL, SDT, DANGLAMVIEC) "
				+ "VALUES (?, ?, ?, ?, ?, 1)";
		try {
			_jdbcTemplate.update(sql, maNv, tenNv, cmnd, email, sdt);
		} catch(DataAccessException e) {
			e.printStackTrace();
	        return "Lỗi hệ thống";
		}
		List<NhanVien> list = getNvBangMaNV(maNv);
		if (list.size()>0) {
			return "Mã nhân viên đã tồn tại";
		}
		return "Success";
	}
	
	public String themTaiKhoan(String userName, String password, String maNV) {
		String sql = "INSERT INTO TAIKHOAN (TENTAIKHOAN, MATKHAU, LOAITAIKHOAN) "
				+ "VALUES (?, ?, ?)"
				+ "DECLARE @TENTAIKHOAN varchar(20)"
				+ "SET @TENTAIKHOAN = (SELECT TENTAIKHOAN FROM TAIKHOAN WHERE TENTAIKHOAN = ?)"
				+ "UPDATE NHANVIEN "
				+ "SET TAIKHOAN = @TENTAIKHOAN "
				+ "WHERE MANV = ?";
		try {
			_jdbcTemplate.update(sql, userName, password, "nhanvien", userName, maNV);
		} catch(DataAccessException e) {
			e.printStackTrace();
	        return "Lỗi hệ thống!";
		}
		List<TaiKhoan> list = getListTaiKhoan(userName);
		if (list.size()>0) {
			return "Tài khoản đã tồn tại!";
		}
		return "Success";
	}
	
	public void capNhatNv(String maNv, String tenNv, String cmnd, String email, String sdt, int lamviec) {
		String sql = "UPDATE NHANVIEN SET TENNV=?, CMND=?, EMAIL=?, SDT=?, DANGLAMVIEC=? WHERE MANV=?";
		try {
	        _jdbcTemplate.update(sql, tenNv, cmnd, email, sdt, lamviec, maNv);
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
		    return "Fail";
		}
		return "Success";
	}
	
	public List<NhanVien> timKiemNv(String input, int option) {
		List<NhanVien> listNV = new ArrayList<>();
		String sql = "";
		switch (option) {
		case 0:
			sql = "SELECT * FROM NHANVIEN WHERE MANV LIKE ?";
			break;
		case 1:
			sql = "SELECT * FROM NHANVIEN WHERE TENNV LIKE ?";
			break;
		case 2:
			sql = "SELECT * FROM NHANVIEN WHERE DANGLAMVIEC=1 and TENNHANVIEN like ?";
			break;
		case 3:
			sql = "SELECT * FROM NHANVIEN WHERE DANGLAMVIEC=0 and TENNHANVIEN like ?";
			break;
		default:
			break;
		}
		
		
		try {
		       
	        String inputPattern = "%" + input + "%";
	        listNV = _jdbcTemplate.query(sql, new Object[]{inputPattern}, new MapperNhanVien());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return listNV;
	}
	
	public List<TaiKhoan> getListTaiKhoan(String username) {
		List<TaiKhoan> listTK = new ArrayList<>();
		String sql = "SELECT * FROM TAIKHOAN WHERE TENTAIKHOAN=?";
		try {
			listTK = _jdbcTemplate.query(sql, new MapperTaiKhoan());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return listTK;
	}
}
