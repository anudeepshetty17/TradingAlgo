package com.trading;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TradingUtil {

	public TradingUtil() {

	}

	
	public static BigDecimal getQuantityByPriceAndAmount(BigDecimal currPricePerUnit, BigDecimal amount,
			boolean includeSpread) {

		return amount.divide(currPricePerUnit, 9, RoundingMode.HALF_UP);
	}

	/**
	 * First determine the difference 1)change = difference between two prices/values
	 * 2) percentage=(change/orginal)X100
	 **/
	public static BigDecimal calculatePercentage(BigDecimal newv, BigDecimal old) {
		BigDecimal d = new BigDecimal(0);
		if (newv.compareTo(old) > 0)
			d = ((newv.subtract(old)).divide(newv, 9, RoundingMode.HALF_UP));
		else
			d = (old.subtract(newv)).divide(old, 9, RoundingMode.HALF_UP);

		d = d.multiply(new BigDecimal(100));
		return d;
	}

	public static Map<String, BigDecimal> calculateProfile(BigDecimal qty, BigDecimal avg, BigDecimal totalValue){
		Map<String, BigDecimal> pdata = new HashMap<String, BigDecimal>();
		
		BigDecimal invested = qty.multiply(avg);
		pdata.put("percentage", calculatePercentage(invested, totalValue));
		pdata.put("average", BigDecimal.ZERO);
		pdata.put("invested", invested);
		pdata.put("quantity", qty);
		pdata.put("totalValue", totalValue);		
		return pdata;
	}
	
	public static int compareDate(Timestamp a, Timestamp b) {

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

		return comparisonResult;

	}

}
