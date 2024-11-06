package com.trading;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.trading.model.Portfolio;
import com.trading.model.PriceHistory;
import com.trading.repository.PortfolioRepository;
import com.trading.repository.PriceHisotryRepository;
import com.trading.service.TradingService;
import com.trading.service.impl.TradingServiceImpl;

//@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TradingServiceTest {
	
	 @TestConfiguration
	    static class TradingServiceImplTestContextConfiguration {
	 
	        @Bean
	        public TradingService tradingService() {
	            return new TradingServiceImpl();
	        }
	    }

   /* @Autowired
	private TradingService tradingService;*/
    
    @Autowired
    private PriceHisotryRepository priceHistoryRepository;
    
    @Autowired
    private PortfolioRepository portfolioRepository;
    
    @Autowired
    TransactionTemplate txTemplate;
    
    
    private <T> T doInTransaction(Supplier<T> operation) {
        return txTemplate.execute(status -> operation.get());
    }
    private void doInTransaction(Runnable operation) {
        txTemplate.execute(status -> {
            operation.run();
            return null;
        });
    }
	@Test
    @Transactional
    @Commit
	void testMyServiceReport_Success() {	
		List<PriceHistory> history = this.priceHistoryRepository.findByCoinNameOrderByStartTimeAsc("BTC");
		
		Portfolio portfolio = this.portfolioRepository.findByAssetName("BTC");
		String entryDate = "2022-11-05 21:05:00"; // Start Date when you want to make an entry
		Timestamp entryDateTime = Timestamp.valueOf(entryDate);

		if(portfolio == null) {
			portfolio = commenceTrade(entryDateTime, 10000, "BTC");
		}	
		
		portfolio = computeProfitLoss(history,portfolio,entryDateTime);
	}
	
	public Portfolio computeProfitLoss(List<PriceHistory> priceHistory,Portfolio portfolio,Timestamp entryDateTime) {
		double entryValue = 0.0;
		double entryAmount = 100;
		double currentValue = 10.23;
		double totalReturn =0.0;
		double totalPercent = 0.0;
		double low=0.0;
		double high =0.0;
		BigDecimal  sellPercent = new BigDecimal(10);
		BigDecimal  buyPercent = new BigDecimal(5);
		BigDecimal currentValueBasedOnQuantity;
		BigDecimal quntityTosellBuy;
		BigDecimal buyingPower =new BigDecimal(0); ;
		Calendar calendar = Calendar.getInstance();
		System.out.println(portfolio);
		Portfolio port = new Portfolio();
		for(PriceHistory hist:priceHistory) {
			
			if(TradingUtil.compareDate(hist.getStartTime(),entryDateTime) >0) {		
				currentValueBasedOnQuantity = portfolio.getQuantity().multiply(hist.getClose());

				if (currentValueBasedOnQuantity.compareTo(portfolio.getEquity())>0) {  //Sell
					if(TradingUtil.calculatePercentage(hist.getClose(), portfolio.getAvgCost()).compareTo(sellPercent) >0) {
						//Fix this When we buy something we need to update the quantity, equity etc back 
						buyingPower = buyingPower.add(currentValueBasedOnQuantity.subtract(portfolio.getEquity()));
						port = new Portfolio();
						quntityTosellBuy =  (currentValueBasedOnQuantity.subtract(portfolio.getEquity())).divide(hist.getClose(),9,RoundingMode.HALF_UP);
						port.setEquity(currentValueBasedOnQuantity.subtract(portfolio.getEquity()));
						port.setQuantity(portfolio.getQuantity().subtract(quntityTosellBuy));
						port.setLastUpdated(new Timestamp(calendar.getTimeInMillis()));
						port.setTotalReturn(buyingPower);
						port.setLastTrade("BUY");

						//portfolioRepository.save(portfolio);
						
						System.out.println(hist.getClose()+"--"+quntityTosellBuy+"::"+buyingPower+"--"+port);

					}
				
				} if (hist.getClose().compareTo(portfolio.getAvgCost())<0) {
					 //Trigger buy
					if(TradingUtil.calculatePercentage(portfolio.getAvgCost(), hist.getClose()).compareTo(buyPercent)>0) {
						//System.out.println("buy");
						//TODO

					}
				}	
				
			}			
			
		}
		
		return null;		
	}
	
	public void triggerSale(double amount, double currentPrice,char trade) {
		double quantity;
		if(trade == 'B') { //Buy 
			//TradingUtil.getQuantityByPriceAndAmount(currentPrice, amount, false);
		}else {  //Sale
			
		}
	}
	
	public Portfolio commenceTrade(Timestamp startDate,double entryAmount,String assetName) {
		List<PriceHistory> priceHistory = this.priceHistoryRepository.findByCoinNameOrderByStartTimeDesc(assetName);
		Calendar calendar = Calendar.getInstance();
		Portfolio  portfolio= new Portfolio(); // Create first Trade Based on Entry Value
		for(PriceHistory hist:priceHistory) {
			if(TradingUtil.compareDate(startDate, hist.getStartTime())==0) {
				
				portfolio.setId(1);
				portfolio.setAssetId(1);
				portfolio.setUserId(1);
				
				portfolio.setTotalReturn(new BigDecimal(0));
				portfolio.setQuantity(TradingUtil.getQuantityByPriceAndAmount(hist.getLow(), new BigDecimal(entryAmount), false));
				portfolio.setEquity(new BigDecimal(entryAmount));
				portfolio.setAvgCost(hist.getLow());
				portfolio.setTotalReturn(new BigDecimal(0));
				portfolio.setEntryPrice(hist.getLow());
				portfolio.setEntryValue(new BigDecimal(entryAmount));
				portfolio.setCreationDate(new Timestamp(calendar.getTimeInMillis()));
				portfolio.setLastUpdated(new Timestamp(calendar.getTimeInMillis()));
				portfolio.setAssetClass("CRYPTO");
				portfolio.setAssetName(assetName);
				portfolio.setLastTrade("BUY");
				break;
			}					
		}
		
		portfolioRepository.save(portfolio);
		return portfolio;
	}
}
