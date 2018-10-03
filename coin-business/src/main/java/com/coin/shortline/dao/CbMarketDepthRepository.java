package com.coin.shortline.dao;

import com.coin.shortline.entity.CbCoin;
import com.coin.shortline.entity.CbMarketDepth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CbMarketDepthRepository extends JpaRepository<CbMarketDepth, Integer> {

    @Query("select u from CbMarketDepth u where u.symbol =?1 and u.create_at > ?2")
    List<CbMarketDepth> findByCreateAt(String symbol, Date time);

    @Query(value = "select * from cb_market_depths where symbol =?1 order by id desc limit ?2 , 1 ", nativeQuery = true)
    CbMarketDepth findPageMarketDepth(String symbol, int start);

//    @Query(value = "select u from CbMarketDepth u ")
//    CbMarketDepth find1minMarketDepth();

}
