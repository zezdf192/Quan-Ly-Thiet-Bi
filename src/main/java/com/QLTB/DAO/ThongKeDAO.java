package com.QLTB.DAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.QLTB.Entity.MapperPhieuMuon;
import com.QLTB.Entity.PhieuMuon;

@Repository
public class ThongKeDAO {

	@Autowired
	JdbcTemplate _jdbcTemplate;

	public List<PhieuMuon> timKiemPM(String input, String tungay, String denngay, int option) {
		List<PhieuMuon> listSV = new ArrayList<>();
		String sql = "";
		if (denngay.isEmpty() && tungay.isEmpty()) {
			switch (option) {
			case 0:
				sql = "SELECT * FROM PHIEUMUON WHERE MAPHG LIKE ?";
				break;
			case 1:
				sql = "SELECT * FROM PHIEUMUON WHERE SINHVIENMUON LIKE ?";
				break;
			case 2:
				sql = "SELECT * FROM PHIEUMUON WHERE NGUOILAPPHIEU LIKE ?";
				break;
			default:
				break;
			}

			try {

				String inputPattern = "%" + input + "%";
				listSV = _jdbcTemplate.query(sql, new Object[] { inputPattern }, new MapperPhieuMuon());
			} catch (DataAccessException e) {
				// Handle errors if necessary
				e.printStackTrace();
			}
		} else if (denngay.isEmpty()) {
			switch (option) {
			case 0:
				sql = "SELECT * FROM PHIEUMUON WHERE MAPHG LIKE ? AND THOIDIEMMUON>= '" + tungay + "'";
				break;
			case 1:
				sql = "SELECT * FROM PHIEUMUON WHERE SINHVIENMUON LIKE ? AND THOIDIEMMUON>= '" + tungay + "'";
				break;
			case 2:
				sql = "SELECT * FROM PHIEUMUON WHERE NGUOILAPPHIEU LIKE ? AND THOIDIEMMUON>= '" + tungay + "'";
				break;
			default:
				break;
			}

			try {

				String inputPattern = "%" + input + "%";
				listSV = _jdbcTemplate.query(sql, new Object[] { inputPattern }, new MapperPhieuMuon());
			} catch (DataAccessException e) {
				// Handle errors if necessary
				e.printStackTrace();
			}
		} else if (tungay.isEmpty()) {
			switch (option) {
			case 0:
				sql = "SELECT * FROM PHIEUMUON WHERE MAPHG LIKE ? AND THOIDIEMMUON<DATEADD(DAY,1,'" + denngay + "')";
				break;
			case 1:
				sql = "SELECT * FROM PHIEUMUON WHERE SINHVIENMUON LIKE ? AND THOIDIEMMUON<DATEADD(DAY,1,'" + denngay
						+ "')";
				break;
			case 2:
				sql = "SELECT * FROM PHIEUMUON WHERE NGUOILAPPHIEU LIKE ? AND THOIDIEMMUON<DATEADD(DAY,1,'" + denngay
						+ "')";
				break;
			default:
				break;
			}

			try {

				String inputPattern = "%" + input + "%";
				listSV = _jdbcTemplate.query(sql, new Object[] { inputPattern }, new MapperPhieuMuon());
			} catch (DataAccessException e) {
				// Handle errors if necessary
				e.printStackTrace();
			}
		} else {

			switch (option) {
			case 0:
				sql = "SELECT * FROM PHIEUMUON WHERE MAPHG LIKE ? AND THOIDIEMMUON<DATEADD(DAY,1,'" + denngay
						+ "') AND THOIDIEMMUON>= '" + tungay + "'";
				break;
			case 1:
				sql = "SELECT * FROM PHIEUMUON WHERE SINHVIENMUON LIKE ? AND THOIDIEMMUON<DATEADD(DAY,1,'" + denngay
						+ "') AND THOIDIEMMUON>= '" + tungay + "'";
				break;
			case 2:
				sql = "SELECT * FROM PHIEUMUON WHERE NGUOILAPPHIEU LIKE ? AND THOIDIEMMUON<DATEADD(DAY,1,'" + denngay
						+ "') AND THOIDIEMMUON>= '" + tungay + "'";
				break;
			default:
				break;
			}

			try {

				String inputPattern = "%" + input + "%";
				listSV = _jdbcTemplate.query(sql, new Object[] { inputPattern }, new MapperPhieuMuon());
			} catch (DataAccessException e) {
				// Handle errors if necessary
				e.printStackTrace();
			}
		}
		return listSV;
	}

}
