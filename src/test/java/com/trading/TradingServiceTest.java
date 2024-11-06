package com.trading;

import java.math.BigDecimal;
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
		List<PriceHistory> history1 = this.priceHistoryRepository.findByCoinNameOrderByStartTimeDesc("BTC");
		
		Portfolio portfolio = this.portfolioRepository.findByAssetName("BTC");
		String str = "2021-11-05 21:05:00";
		Timestamp timestamp = Timestamp.valueOf(str);

		if(portfolio == null) {
			commenceTrade(timestamp, 10000, "BTC");
		}	
	}
	
	public Portfolio generatePortfolio(String coinName,Integer userId)
	{
		Portfolio  porfolio= new Portfolio();
		porfolio.setId(1);
		porfolio.setAssetClass("CRYPYO");
		porfolio.setAssetName("BTC");
		porfolio.setQuantity(null);
		return null;
		
	}
	
	public Portfolio computeProfitLoss(List<PriceHistory> priceHistory) {
		double entryValue = 0.0;
		double entryAmount = 100;
		double currentValue = 10.23;
		double totalReturn =0.0;
		double totalPercent = 0.0;
		double low=0.0;
		double high =0.0;
		BigDecimal  sellPercent = new BigDecimal(10);
		BigDecimal  buyPercent = new BigDecimal(5);
		
		for(PriceHistory hist:priceHistory) {
						
			if (currentValue > entryAmount) {
				if (TradingUtil.calculatePercentage(currentValue, entryValue).compareTo(sellPercent) > 0) {
					double sellAmount = currentValue - entryValue;
					//trigger sell call here
				}
			} else {
				if (buyPercent.compareTo(TradingUtil.calculatePercentage(entryValue, currentValue)) > 0) {
					double buyAmount = entryValue - currentValue;
					//trigger buy call here
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
	
	public void commenceTrade(Timestamp startDate,double entryAmount,String assetName) {
		List<PriceHistory> priceHistory = this.priceHistoryRepository.findByCoinNameOrderByStartTimeDesc(assetName);
		Calendar calendar = Calendar.getInstance();
		Portfolio  portfolio= new Portfolio(); // Create first Trade Based on Entry Value
		for(PriceHistory hist:priceHistory) {
			if(TradingUtil.compareDate(startDate, hist.getStartTime())) {
				
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
		/*
		 * Portfolio saved = doInTransaction(() -> portfolioRepository.save(portfolio));
		 * 
		 * doInTransaction(() -> portfolioRepository.delete(portfolio));
		 */
	}
}
