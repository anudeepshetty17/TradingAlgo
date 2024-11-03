/**
 * 
 */
package com.trading;

/**
 * 
 */
public class TradingAlgorithm {

	/**
	 * 
	 */
	public TradingAlgorithm() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Test:main");
		
		
		

		double entryValue = 0.0;
		double entryAmount = 100;
		double currentValue = 10.23;
		double low=0.0;
		double high =0.0;
		int buyPercent =5;
		int sellPercent = 10;
		

		if (currentValue > entryAmount) {
			if (percentage(currentValue, entryValue) > sellPercent) {
				double sellAmount = currentValue - entryValue;
				//trigger sell call here
			}
		} else {
			if (percentage(entryValue, currentValue) > buyPercent) {
				double buyAmount = entryValue - currentValue;
				//trigger buy call here
			}
		}
	}
	

	public static double percentage(double oldValue, double newValue) {
		return ((newValue - oldValue)/100)*100;
	}


}
