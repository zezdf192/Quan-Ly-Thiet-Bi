package com.QLTB.controller;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.QLTB.DAO.CTPhieuMuonDAO;
import com.QLTB.DAO.HomeDAO;
import com.QLTB.DAO.LoaiTBDAO;
import com.QLTB.DAO.NhanVienDAO;
import com.QLTB.DAO.PhieuMuonDAO;
import com.QLTB.DAO.PhongDAO;
import com.QLTB.DAO.ThietBiDAO;
import com.QLTB.Entity.NhanVien;
import com.QLTB.Entity.PhieuMuon;
import com.QLTB.Entity.Phong;

@Controller
public class QuanLyPhieuMuonController {
	@Autowired
	PhieuMuonDAO pmDAO;
	
	@Autowired
	HomeDAO homeDAO;
	
	@Autowired
	PhongDAO phongDAO;
	
	@Autowired
	LoaiTBDAO loaiTBDAO;
	
	@Autowired
	PhieuMuonDAO phieuMuonDAO;
	
	@Autowired
	CTPhieuMuonDAO ctpmDAO;
	
	@Autowired
	ThietBiDAO tbDAO;
	
	
	private String getLastPathSegment(String url) {
		String[] parts = url.split("/");
		return parts[parts.length - 1];
	}
	
	
//	@RequestMapping(value = "quan-tri/quan-ly-phieu-muon/tao-phieu-muon", method = RequestMethod.POST)
//	public String taoPhieuMuon(@ModelAttribute("nhanvienmoi") PhieuMuon pm, BindingResult errors) {
//		String maPhong = pm.getMaPhong();
//		Timestamp hanTra = pm.getHanTra();
//		Timestamp thoiDiemTra = null;
//		String nvLap = pm.getMaNVLap();
//		String svMuon = pm.getMaSVMuon();
//
//		String log = pmDAO.insertPhieuMuon(maPhong, hanTra, thoiDiemTra, nvLap, svMuon);
//		if (log == "Success") {
//			return "redirect:/quan-tri/quan-ly-phieu-muon";
//		} else {
//			errors.rejectValue("maNV", "invalid.maNV", "Lỗi hệ thống");
//			return "redirect:/quan-tri/quan-ly-phieu-muon";
//		}
//	}
	
	@RequestMapping(value = "quan-tri/quan-ly-phieu-muon/xoa-phieu-muon/*", method = RequestMethod.POST)
	public String xoaNhanVien(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		String[] parts = url.split("/");

		String lastElement = parts[parts.length - 1];
		lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
		String mapm= String.valueOf(lastElement);
		
		String log = pmDAO.deletePhieuMuon(lastElement);
		
		if (log=="Success") {
			return "redirect:/quan-tri/quan-ly-phieu-muon";
		} else { 
			System.out.println(log);
			return "redirect:/quan-tri/quan-ly-phieu-muon";
		}
	}
	
	

	
	@RequestMapping(value = "/quan-tri/quan-ly-phieu-muon/lap-phieu-muon", method = RequestMethod.GET)
	public ModelAndView lapPhieuMuon() {
	
		 ModelAndView mav = new ModelAndView("admin/quan-ly-phieu-muon/lap-phieu-muon");
		 mav.addObject("phongs", phongDAO.layPhongSanSang());
		 
		 return mav;
	}
	
	@RequestMapping(value = "/quan-tri/quan-ly-phieu-muon/lap-phieu-muon/*", method = RequestMethod.GET)
	public ModelAndView lapPhieuMuonTrongPhong(HttpServletRequest request) {
		
		String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	
		 ModelAndView mav = new ModelAndView("admin/quan-ly-phieu-muon/thiet-bi-trong-phong");
		 mav.addObject("maPhong", lastElement); 
	     mav.addObject("thietbi", phongDAO.layThietBiSanSangTrongPhong(lastElement));
	     mav.addObject("loaiTB", loaiTBDAO.getLoaiThietBi());
	     mav.addObject("csvc", tbDAO.getThietBiCSVC());
	     
	 
		 return mav;
	}
	
	
	@RequestMapping(value = "/quan-tri/quan-ly-phieu-muon/xac-nhan-lap", method = RequestMethod.POST)
	public ModelAndView xacNhanLapPhieuMuon(@RequestParam String maPhong , @RequestParam("studentID") String studentID,
            @RequestParam("dateTime") String dateString,
            @RequestParam("selectedElements") String selectedElements,  RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
//		String url = request.getRequestURL().toString();
	
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        long timestamp = dateTime.toEpochSecond(java.time.ZoneOffset.UTC) * 1000;

		String inputString = selectedElements;
		 List<String> outputList = new ArrayList<>();

	        // Sử dụng biểu thức chính quy để tìm và cắt chuỗi
	        Pattern pattern = Pattern.compile("\\b[A-Z]+\\d+");
	        Matcher matcher = pattern.matcher(inputString);
	        while (matcher.find()) {
	            outputList.add(matcher.group());
	        }
	        //String maPhong, Timestamp thoiDiemTra,String nvLap, String svMuon
	        // Chuyển đổi danh sách kết quả thành mảng
	        List<String> outputs = Arrays.asList(outputList.toArray(new String[0]));
	        String log = phieuMuonDAO.insertPhieuMuon(maPhong, dateTime, "NV1", studentID, outputs);
	        // In ra mảng kết quả
	        if(!log.equals("Success")) {
	        	System.out.println(log);
	        	 redirectAttributes.addFlashAttribute("error", log);
	        	 return new ModelAndView("redirect:/quan-tri/quan-ly-phieu-muon/lap-phieu-muon/" + maPhong);
	        }
	      
	 
		 return new ModelAndView("redirect:/quan-tri/quan-ly-phieu-muon" );
	}
	
	
	@RequestMapping(value = "/quan-tri/quan-ly-phieu-muon/chi-tiet-pm/*", method = RequestMethod.GET)
	public ModelAndView chiTietPM(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		String url = request.getRequestURL().toString();
		 
		 String[] parts = url.split("/");
	        
	     String lastElement = parts[parts.length - 1];
	     lastElement = java.net.URLDecoder.decode(lastElement, StandardCharsets.UTF_8);
	    
	     
		 ModelAndView mav = new ModelAndView("admin/quan-ly-phieu-muon/chi-tiet-phieu-muon");
//		 mav.addObject("maPhong", lastElement); 
	     mav.addObject("thietbi", ctpmDAO.getAllCTPhieuMuonChuaTra(lastElement));
	     if(ctpmDAO.getAllCTPhieuMuonChuaTra(lastElement).size() == 0) {
	    	 mav.addObject("soluongtbconlai", 0);
	     }
	     mav.addObject("thietbidatra", ctpmDAO.getAllCTPhieuMuonDaTra(lastElement));
	     mav.addObject("pm", ctpmDAO.getPMByMaPM(lastElement) );
	     mav.addObject("csvc", tbDAO.getThietBiCSVC());
	    
	     mav.addObject("tb", tbDAO.getThietBiChuaMuonMaPM(lastElement));
	  
	     
//	     mav.addObject("loaiTB", loaiTBDAO.getLoaiThietBi());
	 
		 return mav;
	}
	
	@RequestMapping(value = "/quan-tri/quan-ly-phieu-muon/xac-nhan-tra", method = RequestMethod.POST)
	public ModelAndView xacNhanTraPhieuMuon(@RequestParam String maPM , @RequestParam String maPhong,
            @RequestParam("selectedElements") String selectedElements,   @RequestParam("selectedElements1") String selectedElements1,
            RedirectAttributes redirectAttributes, HttpServletRequest request) {

		String inputString = selectedElements;
		String inputString1 = selectedElements1;
		
		
		 List<String> outputList = new ArrayList<>();
		 List<String> outputList1 = new ArrayList<>();

	        // Sử dụng biểu thức chính quy để tìm và cắt chuỗi
	        Pattern pattern = Pattern.compile("\\b[A-Z]+\\d+");
	        Matcher matcher = pattern.matcher(inputString);
	        Matcher matcher1 = pattern.matcher(inputString1);
	        while (matcher.find()) {
	            outputList.add(matcher.group());
	        }
	        while (matcher1.find()) {
	            outputList1.add(matcher1.group());
	        }
	      
	        
	        //String maPhong, Timestamp thoiDiemTra,String nvLap, String svMuon
	        // Chuyển đổi danh sách kết quả thành mảng
	        List<String> outputs = Arrays.asList(outputList.toArray(new String[0]));
	        List<String> outputs1 = Arrays.asList(outputList1.toArray(new String[0]));
	        
	        String log = phieuMuonDAO.traPhieuMuon(maPM, maPhong, outputs);
	        
	     
	        String logMuonThemTb = phieuMuonDAO.muonThemTB(outputs1, maPM);
	        
	        // In ra mảng kết quả
	        if(!log.equals("Success")) { 
	        	System.out.println(log);
	        	 redirectAttributes.addFlashAttribute("error", log);
	        	 return new ModelAndView("redirect:/quan-tri/quan-ly-phieu-muon/lap-phieu-muon/" + maPhong);
	        }
	 
		 return new ModelAndView("redirect:/quan-tri/quan-ly-phieu-muon" );
	}
	
	@RequestMapping(value = "quan-tri/quan-ly-phieu-muon/tim-kiem-phieu-muon", method = RequestMethod.GET)
	   public ModelAndView timKiemPMPage(@RequestParam String selectOption, String inputText, HttpServletRequest request) {
		
		int typeFind = Integer.valueOf(selectOption);
			
		 ModelAndView mav = new ModelAndView("admin/quan-ly-phieu-muon/quan-ly-phieu-muon");
		 mav.addObject("phieumuons", phieuMuonDAO.timKiemPM(inputText, typeFind));
 	 
	     return mav;
	   }
	
}
