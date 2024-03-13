package com.QLTB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.QLTB.DAO.HomeDAO;
import com.QLTB.DAO.NhanVienDAO;
import com.QLTB.Entity.Phong;

@Controller
public class HomeController {
	@Autowired
	HomeDAO homeDAO;
	
	@Autowired
	NhanVienDAO NvDAO;
	
	@RequestMapping(value = "/quan-tri/quan-ly-phong", method = RequestMethod.GET)
	   public ModelAndView homePage() {
	      ModelAndView mav = new ModelAndView("admin/quan-ly-phong/quan-ly-phong");
	      mav.addObject("phongs", homeDAO.getDataPhong());
	      
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
	public ModelAndView nhanvienPage() {
		ModelAndView mav = new ModelAndView("admin/quan-ly-nhanvien/quan-ly-nhanvien");
		mav.addObject("nhanviens", NvDAO.getAllNhanVien());
		return mav;
	}
}
