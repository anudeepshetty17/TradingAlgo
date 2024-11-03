package com.trading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trading.model.PriceHistory;

public interface PriceHisotryRepository extends JpaRepository<PriceHistory, Long> {

    List<PriceHistory> findByCoinName(String coinName);

}
