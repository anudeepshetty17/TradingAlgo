/**
 * 
 */
package com.trading;

import org.springframework.beans.factory.annotation.Autowired;

import com.trading.service.TradingService;

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
	
	@Autowired
    private TradingService tradingService;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Test:main");
		
		

		double entryValue = 0.0;
		double entryAmount = 100;
		double currentValue = 10.23;
		double totalReturn =0.0;
		double totalPercent = 0.0;
		double low=0.0;
		double high =0.0;
		int buyPercent =5;
		int sellPercent = 10;
		

		/*
		 * if (currentValue > entryAmount) { if (percentage(currentValue, entryValue) >
		 * sellPercent) { double sellAmount = currentValue - entryValue; //trigger sell
		 * call here } } else { if (percentage(entryValue, currentValue) > buyPercent) {
		 * double buyAmount = entryValue - currentValue; //trigger buy call here } }
		 */	
	}
	

	public static double percentageOfChange(double oldValue, double newValue) {
		return ((newValue - oldValue)/100)*100;
	}


}
