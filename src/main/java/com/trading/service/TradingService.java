package com.trading.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.trading.model.Portfolio;
import com.trading.model.PriceHistory;

/**
 * The methods that must be implemented. Typically,
 * by a class of the same name with an 'Impl' suffix.
 *
 * Used for abstraction.
 */
public interface TradingService {

 
    ResponseEntity<Portfolio> update(Portfolio userToUpdate);
    
    ResponseEntity<Portfolio> getByassetName(String assetName);

	/**
	 * The getAll() retrieves all the users from the db.
	 *
	 * @return All the users.
	 */
	ResponseEntity<List<PriceHistory>> getTradingHistory(String assetName);


}
