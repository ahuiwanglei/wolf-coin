package com.coin.shortline.service;

import com.coin.shortline.entity.CbCoin;
import com.coin.shortline.entity.gate.CoinWeight;

public interface CoinService {

    public CoinWeight findCornWeight(String symbol);

    public void storeCoinWeightHistory();
}
