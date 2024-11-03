package com.trading.repository;

import com.trading.model.Portfolio;
import com.trading.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Portfolio findByAssetName(String assetName);

}
