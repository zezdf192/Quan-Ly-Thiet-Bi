package com.QLTB.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.QLTB.DAO.HomeDAO;
import com.QLTB.DAO.NhanVienDAO;
import com.QLTB.DAO.PhieuMuonDAO;
import com.QLTB.DAO.PhongDAO;
import com.QLTB.DAO.ThietBiDAO;
import com.QLTB.DAO.TrangThaiDAO;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.TrangThai;

@Controller
public class HomeController {
	@Autowired
	HomeDAO homeDAO;

	@Autowired
	NhanVienDAO NvDAO;
	
	@Autowired
	ThietBiDAO TBDAO;
	
	@Autowired
	TrangThaiDAO trangThaiDAO;
	
	@Autowired
	PhieuMuonDAO pmDAO;
	
	@Autowired
	PhongDAO phongDAO;

	@RequestMapping(value = "/quan-tri/quan-ly-phong", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("admin/quan-ly-phong/quan-ly-phong");
		mav.addObject("phongs", homeDAO.getDataPhong());
		
		List<TrangThai> array = trangThaiDAO.getTrangThai();

        // Lấy 3 phần tử đầu tiên của danh sách
        List<TrangThai> result = array.subList(0, Math.min(3, array.size()));
	      
		mav.addObject("trangThai", result );
		return mav;
	}

	@RequestMapping(value = "/quan-tri/quan-ly-phong/tao-phong", method = RequestMethod.GET)
	public ModelAndView taoPhongPage() {
		ModelAndView mav = new ModelAndView("admin/quan-ly-phong/tao-phong");
		return mav;
	}

	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public ModelAndView springMVCPage() {
		ModelAndView mav = new ModelAndView("backup");
		return mav;
	}

	@RequestMapping(value = "/quan-tri/test", method = RequestMethod.GET)
	public ModelAndView testPage() {
		ModelAndView mav = new ModelAndView("admin/test");

		return mav;
	}

	@RequestMapping(value="/quan-tri/quan-ly-nhanvien", method=RequestMethod.GET)
	public ModelAndView nhanvienPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/quan-ly-nhanvien/quan-ly-nhanvien");
		mav.addObject("nhanviens", NvDAO.getAllNhanVien());
		if (request.getSession().getAttribute("errorMessage") != null) {
			mav.addObject("errorMessage", request.getSession().getAttribute("errorMessage"));
		    // Xóa thông tin lỗi từ session sau khi sử dụng
			System.out.println("thất bại");
		    request.getSession().removeAttribute("errorMessage");
		    System.out.println(request.getSession().getAttribute("errorMessage"));
		} else if (request.getSession().getAttribute("message") != null) {
			 mav.addObject("message", request.getSession().getAttribute("message"));
			    // Xóa thông tin lỗi từ session sau khi sử dụng
			 System.out.println(request.getSession().getAttribute("message"));
			 request.getSession().removeAttribute("message");
			 System.out.println(request.getSession().getAttribute("message"));
		}
		
	   
		return mav;
	}
	
	@RequestMapping(value="/quan-tri/quan-ly-thiet-bi", method=RequestMethod.GET)
	public ModelAndView thietbiPage() {
		ModelAndView mav = new ModelAndView("admin/quan-ly-thiet-bi/quan-ly-thiet-bi");
		mav.addObject("thietbis", TBDAO.getAllThietBi());
		return mav;
	}
	
	@RequestMapping(value="/quan-tri/quan-ly-phieu-muon", method=RequestMethod.GET)
	public ModelAndView phieumuonPage() {
		ModelAndView mapm = new ModelAndView("admin/quan-ly-phieu-muon/quan-ly-phieu-muon");
		mapm.addObject("phieumuons", pmDAO.getAllPhieuMuon());
		mapm.addObject("listphong", homeDAO.getDataPhong());
		return mapm;
	}
}
