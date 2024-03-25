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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.QLTB.DAO.HomeDAO;
import com.QLTB.DAO.LoaiTBDAO;
import com.QLTB.DAO.PhongDAO;
import com.QLTB.DAO.ThietBiDAO;
import com.QLTB.DAO.TrangThaiDAO;
import com.QLTB.Entity.Phong;
import com.QLTB.Entity.ThietBi;
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
	
	@Autowired
	LoaiTBDAO loaiTBDAO;
	
	@Autowired
	ThietBiDAO thietBiDAO;
	
	private String getLastPathSegment(String url) {
	    String[] parts = url.split("/");
	    return parts[parts.length - 1];
	}
	
	@RequestMapping(value = "quan-tri/quan-ly-phong/tao-phongs", method = RequestMethod.POST)
	public ModelAndView homePage(@ModelAttribute("createRoomForm") Phong phong, ModelMap model, BindingResult errors,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {

	    // Bạn có thể trực tiếp truy cập các thuộc tính từ đối tượng Phong
	    String maPhong = phong.getMaPhong();

	    String log = phongDAO.taoMoiPhong(maPhong);

	    if (!log.equals("Success")) {
	    	redirectAttributes.addFlashAttribute("error", log);
	    }

	   
	    return new ModelAndView("redirect:/quan-tri/quan-ly-phong" );
	}

	


	
	@RequestMapping(value = "quan-tri/quan-ly-phong/sua-phong/*", method = RequestMethod.POST)
	   public String taoPhongXongPage(@RequestParam String selectOption, HttpServletRequest request) {
		
		
		String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	     
	     phongDAO.thayDoiPhong(selectOption, lastElement);
	     
	     
			
//		 ModelAndView mav = new ModelAndView("admin/quan-ly-phong/quan-ly-phong");
//    	 mav.addObject("phongs", homeDAO.getDataPhong());
	   
    	  return "redirect:/quan-tri/quan-ly-phong";
	   }
	
	
	@RequestMapping(value = "/quan-tri/quan-ly-phong/xoa-phong/*", method = RequestMethod.POST)
	   public ModelAndView xoaPhongPage( RedirectAttributes redirectAttributes, HttpServletRequest request) {
		 String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	     
	     String log = phongDAO.xoaPhong(lastElement);
	    
	     if(!log.equals("Xóa phòng thành công" ) ) {
	    	redirectAttributes.addFlashAttribute("error", log);
	    	
	    }
	    return new ModelAndView("redirect:/quan-tri/quan-ly-phong/" );
	   }
	
	

	@RequestMapping(value = "quan-tri/quan-ly-phong/tim-kiem-phong", method = RequestMethod.GET)
	   public ModelAndView timKiemPhongPage(@RequestParam String selectOption, String inputText, HttpServletRequest request) {
		
		int typeFind = Integer.valueOf(selectOption);
			
		 ModelAndView mav = new ModelAndView("admin/quan-ly-phong/quan-ly-phong");
    	 mav.addObject("phongs", phongDAO.timKiemPhong(inputText, typeFind));
    	 
	     return mav;
	   }
	
	@RequestMapping(value = "/quan-tri/quan-ly-phong/chi-tiet-phong/*", method = RequestMethod.GET)
	   public ModelAndView chiTietPhongPage(HttpServletRequest request) {
		 String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	     
	    
	     
		
	      ModelAndView mav = new ModelAndView("admin/quan-ly-phong/chi_tiet_phong");
	      //mav.addObject("maPhong", list.get(0).getMaPhong());
	      mav.addObject("maPhong", lastElement); 
	      mav.addObject("thietbi", phongDAO.layThietBiTrongPhong(lastElement));
	      mav.addObject("loaiTB", loaiTBDAO.getLoaiThietBi());
	 
//	      if(list.size() > 0) {
//	    	mav.addObject("optionActive", list.get(0).getTrangThai());
//	      }else {
//	    	  mav.addObject("optionActive", 0);
//	      }
	      
	      return mav;
	   }
	

	
	@RequestMapping(value = "/quan-tri/quan-ly-phong/chi-tiet-phong/them-moi/*", method = RequestMethod.POST)
	public ModelAndView themMoiThietBiPhong(@ModelAttribute("createTBRoom") ThietBi thietbi, BindingResult errors,
            RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {

	    // You can access email and password directly from the Account object
	    String maTB = thietbi.getMaTB();
	    String loaiTB = thietbi.getLoaiTB();
	    int tinhTrangTB = thietbi.getTinhTrangTB();
	    boolean thuaDauPhay = true;
	    if (loaiTB.length() > 5) {
            String cutString = loaiTB.substring(0, 5);
            if(cutString.equals("other")) {
            	loaiTB = loaiTB.substring(6);
            	thuaDauPhay = false;
            }
        } 
	    if(thuaDauPhay) {
	    	loaiTB = loaiTB.substring(0, loaiTB.length() - 1);
	    }
	   
	    String url = request.getRequestURL().toString();
		  
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	   
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	     
	     String check = thietBiDAO.taoMoiTB(maTB, loaiTB, lastElement, tinhTrangTB);
	     
	      ModelAndView mav = new ModelAndView("admin/quan-ly-phong/chi_tiet_phong");
	      //mav.addObject("maPhong", list.get(0).getMaPhong());
	      mav.addObject("maPhong", lastElement); 
	      mav.addObject("thietbi", phongDAO.layThietBiTrongPhong(lastElement));
	      mav.addObject("loaiTB", loaiTBDAO.getLoaiThietBi());
	      if (!check.equals("Success")) {
	          redirectAttributes.addFlashAttribute("error", check);
	      }
	      
	      return new ModelAndView("redirect:/quan-tri/quan-ly-phong/chi-tiet-phong/" + lastElement);
	}
	

}
 