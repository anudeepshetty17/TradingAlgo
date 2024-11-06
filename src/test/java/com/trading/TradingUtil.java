package com.trading;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

public class TradingUtil {

	public TradingUtil() {
	
	}
	
	public static double getQuantityByPriceAndAmount(double currPricePerUnit, double amount,boolean includeSpread) {
			
		return  amount/currPricePerUnit;
	}
	
	public static BigDecimal calculatePercentage(double oldVal,double newVal) {
		return new BigDecimal(((newVal - oldVal)/100)*100);
	}
	
	public static boolean compareDate(Timestamp a,Timestamp b) {
		

	        // Create a Calendar object and set it to the timestamp
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(a);

	        // Set the time fields to zero
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);

	        // Create a new Timestamp from the modified Calendar object
	        Timestamp zeroedTimestampA = new Timestamp(calendar.getTimeInMillis());

	        
	        calendar = Calendar.getInstance();
	        calendar.setTime(b);

	        // Set the time fields to zero
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);

	        // Create a new Timestamp from the modified Calendar object
	        Timestamp zeroedTimestampB = new Timestamp(calendar.getTimeInMillis());
	        
	        int comparisonResult = zeroedTimestampA.compareTo(zeroedTimestampB);

	        if (comparisonResult == 0)
	          return true;
	        
		return false;
		
	}

}
