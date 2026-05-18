package com.utility;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
	
	public static String timeAndDate(int dateminus){
		
		return Instant.now().minus(dateminus,ChronoUnit.DAYS).toString();
		
	}

}
