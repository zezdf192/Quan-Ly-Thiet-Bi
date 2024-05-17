package com.QLTB.DAO;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.LoaiTB;
import com.QLTB.Entity.MapperLoaiTB;
import com.QLTB.Entity.MapperPhieuMuon;
import com.QLTB.Entity.MapperPhong;
import com.QLTB.Entity.MapperThietBi;
import com.QLTB.Entity.PhieuMuon;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.ThietBi;

@Repository
public class ThietBiDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
	@Autowired 
	LoaiTBDAO loaiTBDAO;
	
//public List<ThietBi> getThietBiBangMaPhong(String maPhong) {
//		
//	    List<ThietBi> list = new ArrayList<ThietBi>();
//	    String sql = "select * from THIETBI where MAPHONG = ?";
//	    
//	    try {
//	        list = _jdbcTemplate.query(sql, new Object[]{maPhong}, new MapperThietBi());
//	    } catch (DataAccessException e) {
//	        // Handle errors if necessary
//	        e.printStackTrace();
//	    }
//	   
//	    return list;
//	}
	
	public List<ThietBi> getAllThietBi(){
		List<ThietBi> list = new ArrayList<ThietBi>();
		String sql = "SELECT THIETBI.*, TRANGTHAI.TENTINHTRANG, LOAITB.TENLOAI FROM THIETBI"
				+ " JOIN TRANGTHAI ON THIETBI.TINHTRANGTB = TRANGTHAI.MATINHTRANG"
				+ " JOIN LOAITB ON THIETBI.LOAITBI = LOAITB.MALOAI";
		try {
	        list = _jdbcTemplate.query(sql, new MapperThietBi());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return list;
	}
	
	public List<ThietBi> getTBBangMaTB(String maTB) {
		List<ThietBi> list = new ArrayList<ThietBi>();
		String sql = "SELECT THIETBI.*, TRANGTHAI.TENTINHTRANG, LOAITB.TENLOAI FROM THIETBI "
				+ "JOIN TRANGTHAI ON THIETBI.TINHTRANGTB = TRANGTHAI.MATINHTRANG "
				+ "JOIN LOAITB ON THIETBI.LOAITBI = LOAITB.MALOAI WHERE MATBI LIKE ?";
		try {
		       
	        String inputPattern = "%" + maTB + "%";
	        list = _jdbcTemplate.query(sql, new Object[]{inputPattern}, new MapperThietBi());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return list;
	}
	
	public String themMoiTB(String maTB, String loaiTB, String maPhong) {
		String sql = "INSERT INTO THIETBI(MATBI, LOAITBI, MAPHONG,TINHTRANGTB) VALUES(?, ?, ?, 0)";
		try {
			_jdbcTemplate.update(sql, maTB, loaiTB, maPhong);
		} catch(DataAccessException e) {
			e.printStackTrace();
	        return "Lỗi hệ thống";
		}		
		return "Success";
		
	}
	
	public void suaTB(String maTB, String loaiTB, String maPhong, String tinhTrang) {
		String sql = "UPDATE THIETBI SET MATB=?, LOAITB=?, MAPHONG=?, TINHTRANGTB=? WHERE MATBI=?";
		try {
	        _jdbcTemplate.update(sql, maTB, loaiTB, maPhong, tinhTrang );
	    } catch (DataAccessException e) {
	       
	        e.printStackTrace();
	    }
	}
	
	public String xoaTB(String maTB) {
		String sql = "DELETE FROM THIETBI WHERE MATBI =?";
		try {
		    _jdbcTemplate.update(sql, maTB);
		} catch (DataAccessException e) {
		    e.printStackTrace();
		    return "Fail";
		}
		return "Success";
	}
	
	public List<ThietBi> tkTB(String input, int option) {
		List<ThietBi> list = new ArrayList<ThietBi>();
		String sql = "";
		if (option == 10) {
			return getTBBangMaTB(input);
		}else {
			sql = "SELECT THIETBI.*, TRANGTHAI.TENTINHTRANG, LOAITB.TENLOAI FROM THIETBI "
					+ "JOIN TRANGTHAI ON THIETBI.TINHTRANGTB = TRANGTHAI.MATINHTRANG "
					+ "JOIN LOAITB ON THIETBI.LOAITBI = LOAITB.MALOAI WHERE MATBI LIKE ? "
					+ "AND THIETBI.TINHTRANGTB = ?" ;
			try {
			       
		        String inputPattern = "%" + input + "%";
		        list = _jdbcTemplate.query(sql, new Object[]{inputPattern, option}, new MapperThietBi());
		    } catch (DataAccessException e) {
		        // Handle errors if necessary
		        e.printStackTrace();
		    }
			return list;
		}
		
	}
	
	
	public List<ThietBi> getThietBiBangMaPhong(String maPhong) {
		
	    List<ThietBi> list = new ArrayList<ThietBi>();
	    String sql = "select * from THIETBI join LOAITB on \r\n"
	    		+ "THIETBI.LOAITBI = LOAITB.MALOAI join TRANGTHAI on \r\n"
	    		+ "TRANGTHAI.MaTinhTrang = THIETBI.TINHTRANGTB\r\n"
	    		+ "where MAPHONG = ?";
	    		
	    
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{maPhong}, new MapperThietBi());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
	   
	    return list;
	}
	
	public boolean kiemTraTonTaiLoaiTB(String maLoai) {
		List<LoaiTB> list = new ArrayList<LoaiTB>();
		String sql = "SELECT * FROM LOAITB where MALOAI = ?";

		try {
			list = _jdbcTemplate.query(sql, new Object[] { maLoai }, new MapperLoaiTB());
		} catch (DataAccessException e) {
			// Xử lý lỗi nếu cần thiết
			e.printStackTrace();
		}
		if(list.size() > 0) return true;
		return false;
	}
	
	public boolean kiemTraMaTB(String maTB) {
		
		List<ThietBi> list = new ArrayList<ThietBi>();
		String sql =" SELECT THIETBI.*, TRANGTHAI.TenTinhTrang, LOAITB.TENLOAI "
	    		+ "FROM THIETBI "
	    		+ "JOIN TRANGTHAI ON THIETBI.TINHTRANGTB = TRANGTHAI.MaTinhTrang "
	    		+ "JOIN LOAITB ON THIETBI.LOAITBI = LOAITB.MALOAI "
	    		+ "WHERE THIETBI.MATBI = ?";

		try {
			list = _jdbcTemplate.query(sql, new Object[] { maTB }, new MapperThietBi());
		} catch (DataAccessException e) {
			// Xử lý lỗi nếu cần thiết
			e.printStackTrace();
		}
		
		if(list.size() > 0) return true;
		return false;
	}
	
	public String taoMoiTB(String maTB, String maLoai, String maPhong, int tinhTrangTb) {
		boolean checkMaTB = kiemTraMaTB(maTB); 
			if(checkMaTB) return "Mã thiết bị đã tồn tại";
		
		boolean check = kiemTraTonTaiLoaiTB(maLoai);
			if(!check) {
				loaiTBDAO.taoMoiLoaiTB(maLoai);
			}
		
		maPhong = java.net.URLDecoder.decode(maPhong, StandardCharsets.UTF_8);
			
		String sql = "INSERT INTO THIETBI (MATBI, LOAITBI, MAPHONG, TINHTRANGTB) VALUES (?, ?, ?, ?)";
		   
	    try {
	        _jdbcTemplate.update(sql, maTB, maLoai, maPhong, tinhTrangTb);
	    } catch (DataAccessException e) {
	        // Handle the exception if necessary
	        e.printStackTrace();
	        return "Lỗi hệ thống";
	    }
	    
	    return "Success";
	}
	
	public List<ThietBi> chiTietTB(String maTB) {
		
		 List<ThietBi> list = new ArrayList<ThietBi>();
		    String sql =  "SELECT THIETBI.*, TRANGTHAI.TenTinhTrang, LOAITB.TENLOAI "
		    		+ "FROM THIETBI "
		    		+ "JOIN TRANGTHAI ON THIETBI.TINHTRANGTB = TRANGTHAI.MaTinhTrang "
		    		+ "JOIN LOAITB ON THIETBI.LOAITBI = LOAITB.MALOAI "
		    		+ "WHERE THIETBI.MATBI = ?";
		    
		    try {
		        list = _jdbcTemplate.query(sql, new Object[]{maTB}, new MapperThietBi());
		    } catch (DataAccessException e) {
		        // Handle errors if necessary
		        e.printStackTrace();
		    }
		   
		 return list;
	}
	
	public boolean kiemTraTonTaiCuaTB(String maTB) {
		 List<ThietBi> list = new ArrayList<ThietBi>();
		    String sql = "SELECT * FROM THIETBI join CTPHIEUMUON "
		    		+ "on THIETBI.MATBI = CTPHIEUMUON.MaTB "
		    		+ "and THIETBI.MATBI = ?";
		    
		    try {
		        list = _jdbcTemplate.query(sql, new Object[]{maTB}, new MapperThietBi());
		    } catch (DataAccessException e) {
		        // Handle errors if necessary
		        e.printStackTrace();
		    }
		    if(list.size() > 0) return true;
		    return false;
	}
	
	public String xoaThietBi(String maTB) {
		boolean check = kiemTraTonTaiCuaTB(maTB);
		
		if(check) {
			return "Không được xóa thiết bị có phiếu mượn, vui lòng kiểm tra lại";
		}else {
			String sql = "DELETE FROM THIETBI WHERE MATBI = ?;";
			try {
			    _jdbcTemplate.update(sql, maTB);
			} catch (DataAccessException e) {
			    e.printStackTrace();
			}
			return "Success";
		}
	}
	
	public String thayDoiThietBi(String maTB, String maPhong, String loaiTB, String maTrangThai) {
	    String sql = "UPDATE THIETBI SET LOAITBI = ?, MAPHONG = ? , TINHTRANGTB = ? WHERE MATBI = ?;";
	    int number = 0; // Initialize number to a default value, for example, 0
	    try {
	        number = Integer.parseInt(maTrangThai);
	    } catch (NumberFormatException e) {
	       
	        System.err.println("Invalid format for maTrangThai, using default value for number: " + number);
	    }

	    try {
	        _jdbcTemplate.update(sql, loaiTB, maPhong, number, maTB);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return "Lỗi hệ thống";
	    }
	    
	    return "Thay đổi thông tin thiết bị thành công!";
	}
	
	public List<ThietBi> getThietBiCSVC() {
			
		    List<ThietBi> list = new ArrayList<ThietBi>();
		    String sql = "select * from THIETBI join LOAITB on \r\n"
		    		+ "THIETBI.LOAITBI = LOAITB.MALOAI join TRANGTHAI on \r\n"
		    		+ "TRANGTHAI.MaTinhTrang = THIETBI.TINHTRANGTB\r\n"
		    		+ "where TINHTRANGTB = 0  and maphong = 'CSVC'";
		    		
		    
		    try {
		        list = _jdbcTemplate.query(sql, new Object[]{}, new MapperThietBi());
		    } catch (DataAccessException e) {
		        // Handle errors if necessary
		        e.printStackTrace();
		    }
		   
		    return list;
		}
	
	public List<PhieuMuon> getMaPhongBangMaPhieuMuon(String maPM) {
		List<PhieuMuon> list =  new ArrayList<PhieuMuon>();
		String sql = "select * from PHIEUMUON where MAPM = ?";
		
		try {
	        list = _jdbcTemplate.query(sql, new Object[]{maPM}, new MapperPhieuMuon());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
	   
		
		return list;
	}
		
	public List<ThietBi> getThietBiChuaMuonMaPM(String maPM) {
		
		List <PhieuMuon> listPM = getMaPhongBangMaPhieuMuon(maPM);
		
		
	    List<ThietBi> list = new ArrayList<ThietBi>();
	    String sql = "select * from THIETBI join LOAITB on \r\n"
	    		+ "THIETBI.LOAITBI = LOAITB.MALOAI join TRANGTHAI on \r\n"
	    		+ "TRANGTHAI.MaTinhTrang = THIETBI.TINHTRANGTB\r\n"
	    		+ "where TINHTRANGTB = 0  and maphong = ?";
	    		
	    
	    try {
	        list = _jdbcTemplate.query(sql, new Object[]{listPM.get(0).getMaPhong()}, new MapperThietBi());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
	   
	    return list;
	}
}
