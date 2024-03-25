package com.QLTB.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.QLTB.DAO.NhanVienDAO;
import com.QLTB.Entity.NhanVien;
import com.QLTB.Entity.TaiKhoan;
import com.QLTB.Validate.ValidateNhanVien;

@Controller
public class QuanLyNhanVienController {
	@Autowired
	NhanVienDAO NvDAO;
	ValidateNhanVien validNV;

	private String getLastPathSegment(String url) {
		String[] parts = url.split("/");
		return parts[parts.length - 1];
	}

	@RequestMapping(value = "quan-tri/quan-ly-nhanvien/tao-nhanvien", method = RequestMethod.POST)
	public String taoMoiNhanVien(@ModelAttribute("nhanvienmoi") NhanVien nv, BindingResult errors, ModelMap model, HttpServletRequest request) {
		String maNV = nv.getMaNV();
		String tenNV = nv.getTenNV();
		String cmnd = nv.getCMND();
		String email = nv.getEmail();
		String sdt = nv.getSdt();

//		if (maNV.isEmpty() || tenNV.isEmpty() || cmnd.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
//			request.getSession().setAttribute("errorMessage", "Số điện thoại không hợp lệ!");
//		}
		if (!validNV.checkCmnd(cmnd)) {
			request.getSession().setAttribute("errorMessage", "Số chứng minh nhân phải có 12 chữ số!");
		} else if (!validNV.checkSdt(sdt)) {
			request.getSession().setAttribute("errorMessage", "Số điện thoại không hợp lệ!");
		} else {
			String log = NvDAO.themMoiNv(maNV, tenNV, cmnd, email, sdt);
			System.out.println(log);
			if (log == "Success") {
				request.getSession().setAttribute("message", "Thêm mới thành công!");
				return "redirect:/quan-tri/quan-ly-nhanvien";
			} else {
				request.getSession().setAttribute("errorMessage", log);
				return "redirect:/quan-tri/quan-ly-nhanvien";
			}
		}

		return "redirect:/quan-tri/quan-ly-nhanvien";
	}

	@RequestMapping(value = "quan-tri/quan-ly-nhanvien/xem-nhanvien/*", method = RequestMethod.GET)
	public String xemThongTinNhanVien(ModelMap model, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String[] parts = url.split("/");

		String lastElement = parts[parts.length - 1];
		lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);

		List<NhanVien> list = NvDAO.getNvBangMaNV(lastElement);
		if (list.size() > 0) {
			NhanVien NV = list.get(0);
			model.addAttribute("nhanvien", NV);
			return "";
		} else {
			return "";
		}
	}

	@RequestMapping(value = "quan-tri/quan-ly-nhanvien/xoa-nhanvien/*", method = RequestMethod.POST)
	public String xoaNhanVien(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		System.out.println(url);
		String[] parts = url.split("/");

		String lastElement = parts[parts.length - 1];
		lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
		System.out.println(lastElement);
		String[] elements = lastElement.split(";");
		String maNv;
		String taiKhoan;
		if (elements.length > 1) {
			maNv = elements[0];
			taiKhoan = elements[1];
		} else {
			maNv = elements[0];
			taiKhoan = "";
		}
		
		
		String log = NvDAO.xoaNv(maNv, taiKhoan);

		if (log == "Xóa thành công!") {
			request.getSession().setAttribute("message", log);
			return "redirect:/quan-tri/quan-ly-nhanvien";
		} else {
			System.out.println(log);
			return "redirect:/quan-tri/quan-ly-nhanvien";
		}

	}

	@RequestMapping(value = "quan-tri/quan-ly-nhanvien/sua-tt-nhanvien/*", method = RequestMethod.POST)
	public String suaNhanVien(HttpServletRequest request, ModelMap model, @ModelAttribute("nhanvien") NhanVien nv) {
		String url = request.getRequestURL().toString();
		String[] parts = url.split("/");

		String lastElement = parts[parts.length - 1];
		lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);

		List<NhanVien> list = NvDAO.getNvBangMaNV(lastElement);
		if (list.size() > 0) {
			NhanVien NV = list.get(0);
			model.addAttribute("nhanvien", NV);
			String maNV = nv.getMaNV();
			String tenNV = nv.getTenNV();
			String cmnd = nv.getCMND();
			String email = nv.getEmail();
			String sdt = nv.getSdt();

			int danglamviec = nv.getDangLamViec();

			if (!validNV.checkSdt(sdt)) {
//				model.addAttribute("errorMessage", "Số điện thoại không hợp lệ!");
				request.getSession().setAttribute("errorMessage", "Số điện thoại không hợp lệ!");
				return "redirect:/quan-tri/quan-ly-nhanvien";
			}
			String log = NvDAO.capNhatNv(maNV, tenNV, cmnd, email, sdt, danglamviec);
			
			if (log.equals("Cập nhật thành công!")) {
				request.getSession().setAttribute("message", log);
				return "redirect:/quan-tri/quan-ly-nhanvien";
			}
			return "redirect:/quan-tri/quan-ly-nhanvien";
		} else {
			return "redirect:/quan-tri/quan-ly-nhanvien";
		}
	}

	@RequestMapping(value = "quan-tri/quan-ly-nhanvien/tao-taikhoan/*", method = RequestMethod.POST)
	public String taoTaiKhoan(@ModelAttribute("taikhoan") TaiKhoan tk, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String[] parts = url.split("/");

		String lastElement = parts[parts.length - 1];
		lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);

		String userName = tk.getTenTaiKhoan();
		String password = tk.getMatKhau();

		String log = NvDAO.themTaiKhoan(userName, password, lastElement);
		System.out.println(log);
		if (log == "Thêm tài khoản thành công!") {
			request.getSession().setAttribute("message", log);
			return "redirect:/quan-tri/quan-ly-nhanvien";
		} else {
			request.getSession().setAttribute("errorMessage", log);
			return "redirect:/quan-tri/quan-ly-nhanvien";
		}
	}

	@RequestMapping(value = "quan-tri/quan-ly-nhanvien/tim-kiem-nhanvien", method = RequestMethod.GET)
	public ModelAndView timKiemNv(@RequestParam String selectOption, String inputText) {
		int typeFind = Integer.valueOf(selectOption);

		ModelAndView mav = new ModelAndView("admin/quan-ly-nhanvien/quan-ly-nhanvien");
		mav.addObject("nhanviens", NvDAO.timKiemNv(inputText, typeFind));
		return mav;
	}
}
