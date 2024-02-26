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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.QLTB.DAO.HomeDAO;
import com.QLTB.DAO.PhongDAO;
import com.QLTB.DAO.TrangThaiDAO;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.TrangThai;
import com.QLTB.Models.Account;

@Controller
public class QuanLyPhongController {
	@Autowired
	PhongDAO phongDAO;
	
	@Autowired
	HomeDAO homeDAO;
	
	@Autowired
	TrangThaiDAO trangThaoDAO;
	
	private String getLastPathSegment(String url) {
	    String[] parts = url.split("/");
	    return parts[parts.length - 1];
	}
	
	@RequestMapping(value = "quan-tri/quan-ly-phong/tao-phongs", method = RequestMethod.POST)
	public ModelAndView homePage(@ModelAttribute("createRoomForm") Phong phong, ModelMap model, BindingResult errors,
	        HttpSession ss) throws IOException {

	    // You can access email and password directly from the Account object
	    String maPhong = phong.getMaPhong();
	  
	   
	   
	    String log = phongDAO.taoMoiPhong(maPhong);
	   
	    if(log.equals("Success")) {
	    	 ModelAndView mav = new ModelAndView("admin/quan-ly-phong/quan-ly-phong");
	    	 mav.addObject("phongs", homeDAO.getDataPhong());
	 	    
	 	    return mav;
	    }

	    errors.rejectValue("maPhong", "createRoomForm", log);
	    ModelAndView mav = new ModelAndView("admin/quan-ly-phong/tao-phong");
	  

	    return mav;
	}
	
	@RequestMapping(value = "/quan-tri/quan-ly-phong/sua-phong/*", method = RequestMethod.GET)
	   public ModelAndView taoPhongPage(HttpServletRequest request) {
		 String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	     
	     List<Phong> list = new ArrayList<Phong>();
	     list = phongDAO.getPhong(lastElement);
	     
	    
		
	      ModelAndView mav = new ModelAndView("admin/quan-ly-phong/sua-phong");
	      mav.addObject("trangThai", trangThaoDAO.getTrangThai());
	      mav.addObject("maPhong", lastElement);
	      if(list.size() > 0) {
	    	mav.addObject("optionActive", list.get(0).getTrangThai());
	      }else {
	    	  mav.addObject("optionActive", 0);
	      }
	      
	      return mav;
	   }
	

	
	@RequestMapping(value = "quan-tri/quan-ly-phong/cap-nhat-phong/*", method = RequestMethod.POST)
	   public ModelAndView taoPhongXongPage(@RequestParam String selectOption, HttpServletRequest request) {
		
		
		String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	     
	     phongDAO.thayDoiPhong(selectOption, lastElement);
			
		 ModelAndView mav = new ModelAndView("admin/quan-ly-phong/quan-ly-phong");
    	 mav.addObject("phongs", homeDAO.getDataPhong());
    	 
	     return mav;
	   }
	
	
	@RequestMapping(value = "/quan-tri/quan-ly-phong/xoa-phong/*", method = RequestMethod.GET)
	   public ModelAndView xoaPhongPage(HttpServletRequest request) {
		 String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	     
	     String log = phongDAO.xoaPhong(lastElement);
	    
	      ModelAndView mav = new ModelAndView("admin/quan-ly-phong/xoa-phong");
	      mav.addObject("infor", log);
	     
	      return mav;
	   }
	

	@RequestMapping(value = "quan-tri/quan-ly-phong/tim-kiem-phong", method = RequestMethod.GET)
	   public ModelAndView timKiemPhongPage(@RequestParam String selectOption, String inputText, HttpServletRequest request) {
		
		int typeFind = Integer.valueOf(selectOption);
			
		 ModelAndView mav = new ModelAndView("admin/quan-ly-phong/quan-ly-phong");
    	 mav.addObject("phongs", phongDAO.timKiemPhong(inputText, typeFind));
    	 
	     return mav;
	   }
}
