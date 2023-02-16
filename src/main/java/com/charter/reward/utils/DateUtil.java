package com.charter.reward.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static String getMonth(String date) throws Exception {
		String month = "";
		try {
			month = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy")).getMonth().toString();
		}catch(Exception ex) {
			
		}
		return month;
		
	}
	
}
