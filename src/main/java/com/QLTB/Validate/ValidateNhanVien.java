package com.QLTB.Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateNhanVien {
	public static boolean checkMail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean checkSdt(String sdt) {
		String PHONE_NUMBER_PATTERN = "(0\\d{9})|(\\+84\\d{9})";
		Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
		Matcher matcher = pattern.matcher(sdt);
        return matcher.matches();
	}
	
	public static boolean checkCmnd(String cmnd) {
		String CMT_PATTERN = "\\d{12}"; // Định dạng: 12 chữ số
	    Pattern pattern = Pattern.compile(CMT_PATTERN);
	    Matcher matcher = pattern.matcher(cmnd);
	    return matcher.matches();
	}
}
