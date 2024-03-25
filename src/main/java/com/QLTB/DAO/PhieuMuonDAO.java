package com.QLTB.DAO;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.MapperNhanVien;
import com.QLTB.Entity.MapperPhieuMuon;
import com.QLTB.Entity.MapperThietBi;
import com.QLTB.Entity.NhanVien;
import com.QLTB.Entity.PhieuMuon;
import com.QLTB.Entity.SinhVien;

@Repository
public class PhieuMuonDAO {
	@Autowired
	JdbcTemplate _jdbcTemplate;
	
	@Autowired
	PhongDAO phongDAO;
	
	@Autowired
	SinhVienDAO svDAO;
	
	public  String generateUniqueString() {
        Set<String> usedStrings = new HashSet<String>(); // Danh sách để lưu trữ các chuỗi đã sử dụng
        Random random = new Random();

        while (true) {
            StringBuilder stringBuilder = new StringBuilder();
            // Tạo chuỗi ngẫu nhiên bằng cách thêm ký tự ngẫu nhiên vào StringBuilder
            for (int i = 0; i < 6; i++) {
                char randomChar = (char) ('A' + random.nextInt(26)); // Tạo ký tự ngẫu nhiên từ 'A' đến 'Z'
                stringBuilder.append(randomChar);
            }
            String randomString = stringBuilder.toString();

            if (!usedStrings.contains(randomString)) { // Kiểm tra xem chuỗi đã được sử dụng chưa
                usedStrings.add(randomString); // Thêm chuỗi vào danh sách đã sử dụng
                return randomString; // Trả về chuỗi nếu nó là duy nhất
            }
        }
    }
	
//	Lấy thông tin list Phiếu Mượn
	public List<PhieuMuon> getAllPhieuMuon() {
		List<PhieuMuon> list = new ArrayList<>();
		String sql = "select * from PHIEUMUON";
		try {
	        list = _jdbcTemplate.query(sql, new MapperPhieuMuon());
	    } catch (DataAccessException e) {
	        // Handle errors if necessary
	        e.printStackTrace();
	    }
		return list;
	}
	
//	Lấy thông tin phiếu mượn thông qua id
	public List<PhieuMuon> showPhieuMuon(int id) {
		List<PhieuMuon> list = new ArrayList<>();
		String sql = "select * from PHIEUMUON where MAPM=?";
		
		try {
	        list = _jdbcTemplate.query(sql, new Object[]{id}, new MapperPhieuMuon());
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	    }
		return list;
	}
	
	
//	Xóa phiếu mượn
	public String deletePhieuMuon(String id) {
		String sql = "DELETE FROM PHIEUMUON WHERE MAPM=?";
		try {
			_jdbcTemplate.update(sql, id);
		} catch (DataAccessException e) {
		    e.printStackTrace();
		    return "Fail";
		}
		return "Success";
	}
	
	
//	Cập nhật thông tin phiếu mượn thông qua id
	public void updatePhieuMuon(String maPhong,Timestamp tdmuon,Timestamp hantra,Timestamp tdtra,String svMuon,String nvxuat,int id) {
		String sql = "UPDATE PHIEUMUON SET MAPHG=?, THOIDIEMMUON=?, HANTRA=?, THOIDIEMTRA=?, SINHVIENMUON=?, NGUOILAPPHIEU=? WHERE MAPM=?";
				try {
			        _jdbcTemplate.update(sql, maPhong,tdmuon,hantra,tdtra,svMuon,nvxuat,id);
			    } catch (DataAccessException e) {
			       
			        e.printStackTrace();
			    }
	}
	//---------------------------------------------------------------------
	public void insertChiTietPhieuMuon(String maPM, List<String> tb) {
	    String sql = "INSERT INTO CTPHIEUMUON (MAPHIEUMUON, MATB, TRANGTHAI) VALUES ";
	    for(int i = 0; i < tb.size(); i++) {
	        sql += "('" + maPM + "','" + tb.get(i) + "', 3)";
	        if (i < tb.size() - 1) {
	            sql += ", "; // Thêm dấu phẩy để ngăn cách giữa các bộ giá trị
	        }
	    }
	    try {
	        _jdbcTemplate.update(sql);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	    }
	}
	
	public void thayDoiTrangThaiTB(String trangThai, List<String> tb) {
	    // Kiểm tra nếu danh sách thiết bị rỗng, không cần thực hiện câu lệnh SQL
	    if (tb.isEmpty()) {
	        return;
	    }
	    
	    String sql = "UPDATE THIETBI SET TINHTRANGTB = ? WHERE MATBI IN (";
	    for (int i = 0; i < tb.size(); i++) {
	        sql += "'" + tb.get(i) + "'";

	        if (i < tb.size() - 1) {
	            sql += ", ";
	        }
	    }
	    sql += ")";
	    
	    try {
	        _jdbcTemplate.update(sql, trangThai);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	    }
	}

	
	
	public String insertPhieuMuon(String maPhong, LocalDateTime thoiDiemTra, String nvLap, String svMuon, List<String> tb) {
		List<SinhVien> sv =  svDAO.getSinhVien(svMuon);
		if(sv.size() == 0) {
			return "Mã sinh viên không tồn tại, vui lòng kiểm tra lại";
		}
		 String uniqueString = generateUniqueString();
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    String sql = "INSERT INTO PHIEUMUON (MAPM, MAPHG, THOIDIEMMUON, THOIDIEMTRA, NGUOILAPPHIEU, SINHVIENMUON) VALUES (?,?, ?, ?, ?, ?)";
	    try {
	        _jdbcTemplate.update(sql, uniqueString, maPhong, currentDateTime, thoiDiemTra, nvLap, svMuon);
	        insertChiTietPhieuMuon(uniqueString, tb);
	        phongDAO.thayDoiPhong("1", maPhong);
	        thayDoiTrangThaiTB("1", tb);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return "Lỗi hệ thống";
	    }
	    return "Success";
	}
	
	public void thayDoiCTPhieuMuon(String maPM, List<String> tb) {
		  if (tb.isEmpty()) {
		        return;
		    }
		    
		    String sql = "UPDATE CTPHIEUMUON SET TRANGTHAI = 4 WHERE MAPHIEUMUON = ? AND MaTB IN (";
		    for (int i = 0; i < tb.size(); i++) {
		        sql += "'" + tb.get(i) + "'";

		        if (i < tb.size() - 1) {
		            sql += ", ";
		        }
		    }
		    sql += ")";
		    
		    try {
		        _jdbcTemplate.update(sql, maPM );
		    } catch (DataAccessException e) {
		        e.printStackTrace();
		    }
	}
	
	public String traPhieuMuon(String maPM, String maPhong,  List<String> tb) {
		
		 //String uniqueString = generateUniqueString();
	   // LocalDateTime currentDateTime = LocalDateTime.now();
	    //String sql = "INSERT INTO PHIEUMUON (MAPM, MAPHG, THOIDIEMMUON, THOIDIEMTRA, NGUOILAPPHIEU, SINHVIENMUON) VALUES (?,?, ?, ?, ?, ?)";
	    try {
	        //_jdbcTemplate.update(sql, uniqueString, maPhong, currentDateTime, thoiDiemTra, nvLap, svMuon);
	    	thayDoiCTPhieuMuon(maPM, tb);
	        phongDAO.thayDoiPhong("0", maPhong);
	        thayDoiTrangThaiTB("0", tb);
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return "Lỗi hệ thống";
	    }
	    return "Success";
	}

}
