package com.coin.shortline.dao;

import com.coin.shortline.entity.CbCoinWeightHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CbCoinWeightHistoryRepository extends JpaRepository<CbCoinWeightHistory, Integer> {
    @Query("select u from CbCoinWeightHistory u where u.symbol like ?1 ")
    Page<CbCoinWeightHistory> findBySearchKeyAndPage(String s, Pageable pageable);
}
