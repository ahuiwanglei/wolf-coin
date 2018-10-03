package com.coin.shortline.dao;

import com.coin.shortline.entity.CbOrders;
import com.coin.shortline.entity.CbPriceTrends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CbPriceTrendsRepository extends JpaRepository<CbPriceTrends, Integer> {

    @Query("select u from CbPriceTrends u where u.symbol = ?1 and u.create_at > ?2")
    List<CbPriceTrends> findPriceTrendsBy1Min(String  symbol, Date time);

    @Query(value = "select * from cb_price_trends where symbol = ?1 order by id desc limit ?2 ,1 ", nativeQuery = true)
    CbPriceTrends findPagePriceTrends(String symbol, int start);

}
