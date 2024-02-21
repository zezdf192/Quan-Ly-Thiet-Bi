package com.QLTB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.QLTB.DAO.HomeDAO;
import com.QLTB.Entity.Phong;

@Controller
public class HomeController {
	@Autowired
	
	HomeDAO homeDAO;
	
	@RequestMapping(value = "/trang-chu/quan-ly-phong", method = RequestMethod.GET)
	   public ModelAndView homePage() {
	      ModelAndView mav = new ModelAndView("user/quan-ly-phong");
	      mav.addObject("phongs", homeDAO.getDataPhong());
	      
	      return mav;
	   }
	
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	   public ModelAndView springMVCPage() {
	      ModelAndView mav = new ModelAndView("backup");
	      return mav;
	   }
	
	@RequestMapping(value = "/trang-chu/test", method = RequestMethod.GET)
	   public ModelAndView testPage() {
	      ModelAndView mav = new ModelAndView("user/test");
	      
	      
	      return mav;
	   }
}
