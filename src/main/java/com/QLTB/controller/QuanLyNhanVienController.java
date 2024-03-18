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
import org.springframework.web.servlet.view.RedirectView;

import com.QLTB.DAO.NhanVienDAO;
import com.QLTB.Entity.NhanVien;
import com.QLTB.Entity.TaiKhoan;

@Controller
public class QuanLyNhanVienController {
	@Autowired
	NhanVienDAO NvDAO;

	private String getLastPathSegment(String url) {
		String[] parts = url.split("/");
		return parts[parts.length - 1];
	}

	@RequestMapping(value = "quan-tri/quan-ly-nhanvien/tao-nhanvien", method = RequestMethod.POST)
	public String taoMoiNhanVien(@ModelAttribute("nhanvienmoi") NhanVien nv, BindingResult errors) {
		String maNV = nv.getMaNV();
		String tenNV = nv.getTenNV();
		String cmnd = nv.getCMND();
		String email = nv.getEmail();
		String sdt = nv.getSdt();

		String log = NvDAO.themMoiNv(maNV, tenNV, cmnd, email, sdt);
		if (log == "Success") {
			return "redirect:/quan-tri/quan-ly-nhanvien";
		} else {
			errors.rejectValue("maNV", "invalid.maNV", "Lỗi hệ thống");
			return "redirect:/quan-tri/quan-ly-nhanvien";
		}
	}
	
	@RequestMapping(value="quan-tri/quan-ly-nhanvien/xem-nhanvien/*", method = RequestMethod.GET)
	public String xemThongTinNhanVien(ModelMap model, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String[] parts = url.split("/");

		String lastElement = parts[parts.length - 1];
		lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
		
		List<NhanVien> list = NvDAO.getNvBangMaNV(lastElement);
		if (list.size()>0) {
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
		String[] parts = url.split("/");

		String lastElement = parts[parts.length - 1];
		lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
		
		
		String log = NvDAO.xoaNv(lastElement);
		
		if (log=="Success") {
			return "redirect:/quan-tri/quan-ly-nhanvien";
		} else {
			System.out.println(log);
			return "redirect:/quan-tri/quan-ly-nhanvien";
		}
		
	}
	
	@RequestMapping(value="quan-tri/quan-ly-nhanvien/sua-tt-nhanvien/*", method = RequestMethod.POST)
	public String suaNhanVien(HttpServletRequest request, ModelMap model, @ModelAttribute("nhanvien") NhanVien nv) {
		String url = request.getRequestURL().toString();
		String[] parts = url.split("/");

		String lastElement = parts[parts.length - 1];
		lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
		
		List<NhanVien> list = NvDAO.getNvBangMaNV(lastElement);
		if (list.size()>0) {
			NhanVien NV = list.get(0);
			model.addAttribute("nhanvien", NV);
			String maNV = nv.getMaNV();
			System.out.println(maNV);
			String tenNV = nv.getTenNV();
			System.out.println(tenNV);
			String cmnd = nv.getCMND();
			System.out.println(cmnd);
			String email = nv.getEmail();
			String sdt = nv.getSdt();
			
			int danglamviec = nv.getDangLamViec();
			System.out.println(danglamviec);
			
			NvDAO.capNhatNv(maNV, tenNV, cmnd, email, sdt, danglamviec);
			
			return "redirect:/quan-tri/quan-ly-nhanvien";
		} else {
			return "redirect:/quan-tri/quan-ly-nhanvien";
		}
	}
	
	@RequestMapping(value="quan-tri/quan-ly-nhanvien/tao-taikhoan/*", method = RequestMethod.POST)
	public String taoTaiKhoan(@ModelAttribute("taikhoan") TaiKhoan tk, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String[] parts = url.split("/");

		String lastElement = parts[parts.length - 1];
		lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
		
		String userName = tk.getTenTaiKhoan();
		String password = tk.getMatKhau();
		
		System.out.println(userName);
		System.out.println(password);
		System.out.println(lastElement);
		
		String log = NvDAO.themTaiKhoan(userName, password, lastElement);
		if (log == "Success") {
			return "redirect:/quan-tri/quan-ly-nhanvien";
		} else {
			return "redirect:/quan-tri/quan-ly-nhanvien";
		}
	}
	
	@RequestMapping(value="quan-tri/quan-ly-nhanvien/tim-kiem-nhanvien", method = RequestMethod.GET)
	public ModelAndView timKiemNv(@RequestParam String selectOption, String inputText) {
		int typeFind = Integer.valueOf(selectOption);
		
		ModelAndView mav = new ModelAndView("admin/quan-ly-nhanvien/quan-ly-nhanvien");
		mav.addObject("nhanviens", NvDAO.timKiemNv(inputText, typeFind));
		return mav;	
	}
}
