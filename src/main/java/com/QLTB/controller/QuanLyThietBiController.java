package com.QLTB.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.QLTB.DAO.HomeDAO;
import com.QLTB.DAO.LoaiTBDAO;
import com.QLTB.DAO.LoginDAO;
import com.QLTB.DAO.PhongDAO;
import com.QLTB.DAO.ThietBiDAO;
import com.QLTB.DAO.TrangThaiDAO;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.ThietBi;


@Controller
public class QuanLyThietBiController {
	@Autowired
	PhongDAO phongDAO;
	
	@Autowired
	HomeDAO homeDAO;
	
	@Autowired
	TrangThaiDAO trangThaiDAO;
	
	@Autowired
	LoaiTBDAO loaiTBDAO;
	
	@Autowired
	ThietBiDAO thietBiDAO;
	
	
	private String getLastPathSegment(String url) {
	    String[] parts = url.split("/");
	    return parts[parts.length - 1];
	}
	
	@RequestMapping(value = "quan-tri/quan-ly-thietbi/tao-thietbi", method = RequestMethod.POST)
	public ModelAndView homePage(@ModelAttribute("thietbimoi") ThietBi thietBi,  ModelMap model, BindingResult errors,
	        HttpSession ss) throws IOException {

	    // You can access email and password directly from the Account object
	    String maTB = thietBi.getMaTB();
	    String loaiTB = thietBi.getLoaiTB();
	    String maPhong = thietBi.getMaPhong();
	   
	    String log = thietBiDAO.themMoiTB(maTB, loaiTB, maPhong);
	   
	    if(log.equals("Success")) {
	    	 ModelAndView mav = new ModelAndView("admin/quan-ly-thietbi/quan-ly-thietbi");
	    	 mav.addObject("thietbis", thietBiDAO.getAllThietBi());
	 	    
	 	    return mav;
	    }

	    errors.rejectValue("maTB", "thietbimoi", log);
	    ModelAndView mav = new ModelAndView("admin/quan-ly-thietbi/tao-thietbi");
	  

	    return mav;
	}
	
	@RequestMapping(value = "/quan-tri/quan-ly-thietbi/sua-thietbi/*", method = RequestMethod.GET)
	   public ModelAndView taoThietBiPage(HttpServletRequest request) {
		 String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	   
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	    
	     List<ThietBi> list = new ArrayList<ThietBi>();
	     list = thietBiDAO.getTBBangMaTB(lastElement);
	     
	    
		
	      ModelAndView mav = new ModelAndView("admin/quan-ly-thietbi/sua-thietbi");
	      mav.addObject("trangThai", trangThaiDAO.getTrangThai());
	      mav.addObject("maTB", lastElement);
	      if(list.size() > 0) {
	    	mav.addObject("optionActive", list.get(0).getTinhTrangTB());
	      }else {
	    	  mav.addObject("optionActive", 0);
	      }
	      
	      return mav;
	   }
	
	@RequestMapping(value="quan-tri/quan-ly-thiet-bi/tim-kiem-thiet-bi", method = RequestMethod.GET)
	public ModelAndView timKiemTB(@RequestParam String selectOption, String inputText) {
		int typeFind = Integer.valueOf(selectOption);
		
		ModelAndView mav = new ModelAndView("admin/quan-ly-thiet-bi/quan-ly-thiet-bi");
		mav.addObject("thietbis", thietBiDAO.tkTB(inputText, typeFind));
		return mav;	
	}
	
	@RequestMapping(value = "quan-tri/quan-ly-phong/chi-tiet-thiet-bi/*", method = RequestMethod.GET)
	 public ModelAndView chiTietThietBi(HttpServletRequest request) {
		
			
		String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	    
	     List<ThietBi> list = new ArrayList<ThietBi>();
	     list = thietBiDAO.chiTietTB(lastElement);
	  
	   
	      ModelAndView mav = new ModelAndView("admin/quan-ly-tb/chi-tiet-tb");  
	     
	      mav.addObject("maThietBi", lastElement); 
	      mav.addObject("trangThai", trangThaiDAO.getTrangThai());
	      mav.addObject("loaiTB", loaiTBDAO.getLoaiThietBi());
	      mav.addObject("optionActive", list.get(0).getTinhTrangTB());
	      mav.addObject("tenTrangThai", list.get(0).getTenTinhTrang());
	      mav.addObject("tenTB", list.get(0).getTenTB());
	      mav.addObject("tenLoaiTB", list.get(0).getLoaiTB());
	      mav.addObject("maPhong", list.get(0).getMaPhong());
	      mav.addObject("phongs", homeDAO.getDataPhong());
	      
	      
	      return mav;
	   }
	

	@RequestMapping(value = "/quan-tri/quan-ly-thiet-bi/xoa-thiet-bi/*", method = RequestMethod.GET)
	   public ModelAndView xoaPhongPage( RedirectAttributes redirectAttributes, HttpServletRequest request) {
		 String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	     
	     String log = thietBiDAO.xoaThietBi(lastElement);
	     ModelAndView mav = new ModelAndView("admin/quan-ly-tb/chi-tiet-tb" + lastElement);
	     
	     if (!log.equals("Success")) {
	          redirectAttributes.addFlashAttribute("error", log);
	      }else {
	 	     return new ModelAndView("redirect:/quan-tri/quan-ly-phong/");
	      }

	     return mav;
	   }
	
	
	
	@RequestMapping(value = "/quan-tri/quan-ly-phong/chi-tiet-thiet-bi/sua-thiet-bi/*", method = RequestMethod.POST)
	public ModelAndView taoPhongXongPage(@RequestParam String maPhong, String loaiTB, String tinhTrangTB, HttpServletRequest request) {

	  
	    String url = request.getRequestURL().toString();
		  
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	   
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	     
	     //String check = thietBiDAO.taoMoiTB(maTB, loaiTB, lastElement, tinhTrangTB);
	   
	     String log = thietBiDAO.thayDoiThietBi(lastElement, maPhong, loaiTB, tinhTrangTB);
	     
	     List<ThietBi> list = new ArrayList<ThietBi>();
	     list = thietBiDAO.chiTietTB(lastElement);
	     
	     ModelAndView mav = new ModelAndView("admin/quan-ly-tb/chi-tiet-tb");  
	     
	      mav.addObject("maThietBi", lastElement); 
	      mav.addObject("trangThai", trangThaiDAO.getTrangThai());
	      mav.addObject("loaiTB", loaiTBDAO.getLoaiThietBi());
	      mav.addObject("optionActive", list.get(0).getTinhTrangTB());
	      mav.addObject("tenTrangThai", list.get(0).getTenTinhTrang());
	      mav.addObject("tenTB", list.get(0).getTenTB());
	      mav.addObject("tenLoaiTB", list.get(0).getLoaiTB());
	      mav.addObject("maPhong", maPhong);
	      mav.addObject("phongs", homeDAO.getDataPhong());
	      
	      return new ModelAndView("redirect:/quan-tri/quan-ly-phong/chi-tiet-thiet-bi/" + lastElement);
	}
}
