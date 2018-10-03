package com.coin.shortline.dao;

import com.coin.shortline.entity.CbCoin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CbCoinRepository extends JpaRepository<CbCoin, Integer> {

    @Query("select u from CbCoin u where u.symbol like ?1")
    Page<CbCoin> findBySearchKeyAndPage(String s, Pageable pageable);
}
