package com.trading.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * The persistent class for the test_table database table.
 * 
 */
@Entity
@Table(name="portfolio")
@NamedQuery(name="Portfolio.findAll", query="SELECT t FROM Portfolio t")
@Getter @Setter @ToString @AllArgsConstructor
public class Portfolio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="asset_id")
	private Integer assetId;

	@Column(name="asset_class")
	private String assetClass;
	
	@Column(name="asset_name")
	private String assetName;
	
	@Column(name="quantity")
	private BigDecimal quantity;

	@Column(name="equity")
	private BigDecimal equity;

	@Column(name="average_cost")
	private BigDecimal avgCost;

	@Column(name="total_return")
	private BigDecimal totalReturn;
	
	@Column(name="total_return_percent")
	private BigDecimal totalReturnPercent;

	@Column(name="entry_price")
	private BigDecimal entryPrice;

	@Column(name="entry_value")
	private BigDecimal entryValue;

	@Column(name="creation_date")
	private Timestamp creationDate;

	@Column(name="last_updated")
	private Timestamp lastUpdated;

	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="last_trade")
	private String lastTrade;

	public Portfolio() {
	}

	/*
	 * public Integer getId() { return this.id; }
	 * 
	 * public void setId(Integer id) { this.id = id; }
	 * 
	 * public BigDecimal getAth() { return this.ath; }
	 * 
	 * public void setAth(BigDecimal ath) { this.ath = ath; }
	 * 
	 * public BigDecimal getClose() { return this.close; }
	 * 
	 * public void setClose(BigDecimal close) { this.close = close; }
	 * 
	 * public String getCoinId() { return this.coinId; }
	 * 
	 * public void setCoinId(String coinId) { this.coinId = coinId; }
	 * 
	 * public String getCoinName() { return this.coinName; }
	 * 
	 * public void setCoinName(String coinName) { this.coinName = coinName; }
	 * 
	 * public Timestamp getCreationDate() { return this.creationDate; }
	 * 
	 * public void setCreationDate(Timestamp creationDate) { this.creationDate =
	 * creationDate; }
	 * 
	 * public Timestamp getEndTime() { return this.endTime; }
	 * 
	 * public void setEndTime(Timestamp endTime) { this.endTime = endTime; }
	 * 
	 * public String getFrequency() { return this.frequency; }
	 * 
	 * public void setFrequency(String frequency) { this.frequency = frequency; }
	 * 
	 * public BigDecimal getHigh() { return this.high; }
	 * 
	 * public void setHigh(BigDecimal high) { this.high = high; }
	 * 
	 * public BigDecimal getLow() { return this.low; }
	 * 
	 * public void setLow(BigDecimal low) { this.low = low; }
	 * 
	 * public BigDecimal getMarketCap() { return this.marketCap; }
	 * 
	 * public void setMarketCap(BigDecimal marketCap) { this.marketCap = marketCap;
	 * }
	 * 
	 * public BigDecimal getOpen() { return this.open; }
	 * 
	 * public void setOpen(BigDecimal open) { this.open = open; }
	 * 
	 * public Timestamp getStartTime() { return this.startTime; }
	 * 
	 * public void setStartTime(Timestamp startTime) { this.startTime = startTime; }
	 * 
	 * public Timestamp getUpdationDate() { return this.updationDate; }
	 * 
	 * public void setUpdationDate(Timestamp updationDate) { this.updationDate =
	 * updationDate; }
	 * 
	 * public BigDecimal getVolume() { return this.volume; }
	 * 
	 * public void setVolume(BigDecimal volume) { this.volume = volume; }
	 */
	@Override
	public String toString() { 
	    return "Equity: '" + this.equity + "', Quantity: '" + this.quantity + "', TotalReturn: '" + this.totalReturn + "'";
	} 
	

}