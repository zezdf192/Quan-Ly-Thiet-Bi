package com.QLTB.controller;

import java.io.IOException;
import java.util.Arrays;
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
import org.springframework.web.servlet.ModelAndView;

import com.QLTB.DAO.HomeDAO;
import com.QLTB.DAO.LoginDAO;
import com.QLTB.Models.Account;

import sun.awt.SunHints.Value;

@Controller

public class LoginController {
	@Autowired
	LoginDAO loginDAO;
	@Autowired
	HomeDAO homeDAO;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	   public ModelAndView loginPage() {
	      ModelAndView mav = new ModelAndView("login");  
	      return mav;
	   }
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView homePage(@ModelAttribute("loginForm") Account acc, ModelMap model, BindingResult errors,
	        HttpSession ss) throws IOException {

	    // You can access email and password directly from the Account object
	    String email = acc.getEmail();
	    String password = acc.getPassword();
	   
	    // Your validation and login logic here
	    String log = loginDAO.loginMethod(email, password);
	   
	    List<String> values = Arrays.asList(log.split("%"));
	   
	   
	    if(values.get(0).equals("Success")) {
	    	ModelAndView mav = null;
	    	if(values.get(1).equals("admin")) {
	    		mav = new ModelAndView("redirect:	quan-tri/quan-ly-phong");
	    		mav.addObject("phongs", homeDAO.getDataPhong());
	    	}else if(values.get(1).equals("nhanvien")) {
	    		mav = new ModelAndView("nhanvien/nhanvien");
	    	}else {
	    		mav = new ModelAndView("user/user");
	    	}
	    	 
	    	
	    	 
	 	    return mav;
	    }

	    errors.rejectValue("email", "loginForm", log);	    
	    ModelAndView mav = new ModelAndView("login");
   
	    return mav;
	}

	
	
	
}
