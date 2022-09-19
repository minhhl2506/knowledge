package com.example.knowledge.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Date getDateAfterSecond(Date date, Integer seconds) {
		Calendar cal = Calendar.getInstance();
		
	    cal.setTime(date);
	    
	    cal.add(Calendar.SECOND, seconds);
	    
	    return cal.getTime();
	}
	
}
