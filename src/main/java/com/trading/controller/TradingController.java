package com.trading.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trading.model.Portfolio;
import com.trading.model.PriceHistory;
import com.trading.service.TradingService;

@CrossOrigin
@RestController
@RequestMapping("/trading")
public class TradingController {
	
	

    @Autowired
    private TradingService tradingService;
  
    /**
     * fetch-all-users endpoint
     *
     * NOTE: Used only for debugging or admins; this
     * is not used in the angular project
     * @return
     */
    @GetMapping(value = {"/pricechart/{assetName}", "/pricechart/{assetName}/"})
    public ResponseEntity<List<PriceHistory>> fetchAllUsers(@PathVariable("assetName") String assetName, HttpSession session,HttpServletRequest request) {
        return tradingService.getTradingHistory(assetName);
    }

    /**
     * fetch-one-user endpoint
     * @param email
     * @return
     */
    @GetMapping(value = {"/portfolio/{assetName}", "/portfolio/{assetName}/"})
    public ResponseEntity<Portfolio> fetchPortfolio(@PathVariable("assetName") String assetName, HttpSession session,HttpServletRequest request) {
        return tradingService.getByassetName(assetName);
    }  

}
