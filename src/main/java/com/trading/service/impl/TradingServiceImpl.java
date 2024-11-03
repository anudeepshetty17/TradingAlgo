package com.trading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trading.model.Portfolio;
import com.trading.model.PriceHistory;
import com.trading.repository.PortfolioRepository;
import com.trading.repository.PriceHisotryRepository;
import com.trading.service.TradingService;

/**
 * UserServiceImpl defines the method signatures provided by the UserService
 * interface for the controller to use.
 * <p>
 * Within each method, it uses methods from the portfolioRepository.
 */
@Service
public class TradingServiceImpl implements TradingService {

	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Autowired
	private PriceHisotryRepository priceHisotryRepository;

	@Autowired
	ResourceLoader resourceLoader;

	/**
	 * The getAll() retrieves all the users from the db.
	 *
	 * @return All the users.
	 */
	@Override
	public ResponseEntity<List<PriceHistory>> getTradingHistory(String coinName) {
		List<PriceHistory> list = priceHisotryRepository.findByCoinName(coinName);
		return new ResponseEntity<List<PriceHistory>>(list, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<Portfolio> update(Portfolio userToUpdate) {
		return new ResponseEntity<>(portfolioRepository.save(userToUpdate), HttpStatus.OK);
	}



	@Override
	public ResponseEntity<Portfolio> getByassetName(String assetName) {
		return new ResponseEntity<Portfolio>(portfolioRepository.findByAssetName(assetName), HttpStatus.OK);
	}



}
