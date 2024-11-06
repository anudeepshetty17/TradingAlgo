package com.trading;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;

public class TradingUtil {

	public TradingUtil() {

	}

	/**
	
	**/

	public static BigDecimal getQuantityByPriceAndAmount(BigDecimal currPricePerUnit, BigDecimal amount,
			boolean includeSpread) {

		return amount.divide(currPricePerUnit,9,RoundingMode.HALF_UP);
	}

	/**
	 * First determine the difference change = difference between two prices/values
	 * percentage=(change/orginal)X100
	 **/
	public static BigDecimal calculatePercentage(double old, double newv) {
		double change = old > newv ? old - newv : newv - old;
		double orginal = old > newv ? newv : old;
		return new BigDecimal((change / orginal) * 100);
	}

	public static boolean compareDate(Timestamp a, Timestamp b) {

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
