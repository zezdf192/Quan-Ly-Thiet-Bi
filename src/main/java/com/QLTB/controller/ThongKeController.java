package com.QLTB.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.QLTB.DAO.PhieuMuonDAO;
import com.QLTB.DAO.ThongKeDAO;

@Controller
public class ThongKeController {
	
	@Autowired
	ThongKeDAO  tkDAO;
	
	@RequestMapping(value="quan-tri/thong-ke/tim-kiem", method = RequestMethod.GET)
	public ModelAndView timKiemSV(@RequestParam String selectOption, String inputText,String tungaypm,String denngaypm) {
		int typeFind = Integer.valueOf(selectOption);
		ModelAndView mav = new ModelAndView("admin/thong-ke/thong-ke");
		mav.addObject("listphieumuon", tkDAO.timKiemPM(inputText, tungaypm, denngaypm, typeFind));
		return mav;	
		
	}

}
