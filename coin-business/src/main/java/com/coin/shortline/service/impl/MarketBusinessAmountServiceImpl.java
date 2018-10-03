package com.coin.shortline.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coin.shortline.dao.CbCoinRepository;
import com.coin.shortline.dao.CbMarketDepthRepository;
import com.coin.shortline.dao.CbPriceTrendsRepository;
import com.coin.shortline.entity.CbCoin;
import com.coin.shortline.entity.CbMarketDepth;
import com.coin.shortline.entity.CbPriceTrends;
import com.coin.shortline.entity.gate.SymbolTicker;
import com.coin.shortline.quartz.ScanMarketBusinessAmountQuartz;
import com.coin.shortline.service.MarketBusinessAmountService;
import com.coin.shortline.util.HttpUtils;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MarketBusinessAmountServiceImpl implements MarketBusinessAmountService {

    protected static final Logger logger = LoggerFactory.getLogger(MarketBusinessAmountServiceImpl.class);

    @Autowired
    private CbPriceTrendsRepository cbPriceTrendsRepository;

    @Autowired
    private CbCoinRepository cbCoinRepository;

    @Override
    public void storeCoinBusinessAmount() {
        List<CbCoin> cbCoinList = cbCoinRepository.findAll();
        for (int i = 0; i < cbCoinList.size(); i++) {
            CbCoin cbCoin = cbCoinList.get(i);
            if (cbCoin.getStatus() != CbCoin.Status.Disable.getValue()) {
                SymbolTicker symbolTicker = getGateTicker(cbCoin.getSymbol());
                if(symbolTicker != null){
                    CbPriceTrends cbPriceTrends = new CbPriceTrends();
                    cbPriceTrends.setSymbol(cbCoin.getSymbol());
                    cbPriceTrends.setAmount(new BigDecimal(symbolTicker.getLast()));
                    cbPriceTrends.setHighestbid(new BigDecimal(symbolTicker.getHighestBid()));
                    cbPriceTrends.setLowestask(new BigDecimal(symbolTicker.getLowestAsk()));
                    cbPriceTrends.setBase_volume(new BigDecimal(symbolTicker.getBaseVolume()));
                    cbPriceTrends.setQuote_volume(new BigDecimal(symbolTicker.getQuoteVolume()));
                    CbPriceTrends amountTrends = getCurrent1minData(cbCoin.getSymbol());
                    cbPriceTrends.setAmount1min(amountTrends.getAmount1min());
                    cbPriceTrends.setVolume_1min(amountTrends.getVolume_1min());
                    cbPriceTrends.setCreate_at(new Date());
                    cbPriceTrendsRepository.save(cbPriceTrends);
                }
            } else {
                logger.info(cbCoinList.get(i).getSymbol() + "已被禁用，暂时无法获取市场行情");
            }
        }
    }

    private CbPriceTrends getCurrent1minData(String symbol){
        Calendar calendar =  Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -1);
        CbPriceTrends cbPriceTrend = new CbPriceTrends();
        List<CbPriceTrends> cbPriceTrends = cbPriceTrendsRepository.findPriceTrendsBy1Min(symbol, calendar.getTime());
        if(cbPriceTrends != null && !cbPriceTrends.isEmpty()){
            BigDecimal amount = new BigDecimal(0);
            BigDecimal volume = new BigDecimal(0);
            for (CbPriceTrends cbPriceTrends1 : cbPriceTrends){
                amount = amount.add(cbPriceTrends1.getAmount());
                volume = volume.add(cbPriceTrends1.getBase_volume());
            }
            cbPriceTrend.setAmount1min(amount.divide(new BigDecimal(cbPriceTrends.size()),5, BigDecimal.ROUND_HALF_EVEN));
            cbPriceTrend.setVolume_1min(volume.divide(new BigDecimal(cbPriceTrends.size()), 5, BigDecimal.ROUND_HALF_EVEN));
        }
        return cbPriceTrend;
    }

    private SymbolTicker getGateTicker(String symbol) {
        String host = "https://data.gateio.io/api2/1/ticker/" + symbol + "_usdt";
        HttpResponse httpResponse = null;
        try {
            httpResponse = HttpUtils.doGet(host, "", "", new HashMap<>(), null);
            logger.info("result=" + httpResponse.getStatusLine());
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String httpRequestStr = EntityUtils.toString(httpResponse.getEntity());
                logger.info("获取" + symbol + "行情：" + httpRequestStr);
                Gson gson = new Gson();
                SymbolTicker symbolTicker = gson.fromJson(httpRequestStr, SymbolTicker.class);
                if("true".equals(symbolTicker.getResult())){
                    return symbolTicker;
                }else{
                    return null;
                }
            } else {
                logger.info("获取市场行情失败：" + symbol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}