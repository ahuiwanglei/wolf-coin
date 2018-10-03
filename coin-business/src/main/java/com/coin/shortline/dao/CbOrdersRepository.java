package com.coin.shortline.dao;

import com.coin.shortline.entity.CbMarketDepth;
import com.coin.shortline.entity.CbOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CbOrdersRepository extends JpaRepository<CbOrders, Integer> {

}
