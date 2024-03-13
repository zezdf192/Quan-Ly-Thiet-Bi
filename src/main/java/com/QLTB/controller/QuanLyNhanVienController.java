package com.QLTB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.QLTB.DAO.NhanVienDAO;
import com.QLTB.Entity.NhanVien;

@Controller
public class QuanLyNhanVienController {
	@Autowired
	NhanVienDAO NvDAO;
	
	private String getLastPathSegment(String url) {
	    String[] parts = url.split("/");
	    return parts[parts.length - 1];
	}
	
	@RequestMapping(value="quan-tri/quan-ly-nhanvien/tao-nhanvien", method=RequestMethod.POST)
	public ModelAndView taoMoiNhanVien(@ModelAttribute("nhanvienmoi") NhanVien nv, BindingResult errors) {
		String maNV = nv.getMaNV();
		String tenNV = nv.getTenNV();
		String cmnd = nv.getCMND();
		String email = nv.getEmail();
		String sdt = nv.getSdt();
		String taiKhoan = nv.getTaiKhoan();
//		int lamViec = nv.getDangLamViec();
		
		String log = NvDAO.themMoiNv(maNV, tenNV, cmnd, email, sdt, taiKhoan);
		if(log == "Success") {
			ModelAndView mav = new ModelAndView("admin/quan-ly-phong/quan-ly-nhanvien");
	    	mav.addObject("nhanviens", NvDAO.getAllNhanVien());
	    	
	    	return mav;
		}
		
		errors.rejectValue("maNV", "nhanvien", log);
	    ModelAndView mav = new ModelAndView("admin/quan-ly-nhanvien/tao-nhanvien");
		return mav;
	}
}
