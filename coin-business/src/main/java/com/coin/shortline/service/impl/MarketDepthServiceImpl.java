package com.coin.shortline.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coin.shortline.dao.CbCoinRepository;
import com.coin.shortline.dao.CbMarketDepthRepository;
import com.coin.shortline.entity.CbCoin;
import com.coin.shortline.entity.CbMarketDepth;
import com.coin.shortline.entity.gate.SymbolTicker;
import com.coin.shortline.service.MarketDepthService;
import com.coin.shortline.util.HttpUtils;
import com.google.gson.Gson;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIGlobalBinding;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 市场深度，交易量
 */
@Service
public class MarketDepthServiceImpl implements MarketDepthService {

    protected static final Logger logger = LoggerFactory.getLogger(MarketDepthServiceImpl.class);

    @Autowired
    private CbMarketDepthRepository cbMarketDepthRepository;

    @Autowired
    private CbCoinRepository cbCoinRepository;

    @Override
    public void storeOrderBooks() {
        List<CbCoin> cbCoinList = cbCoinRepository.findAll();
        for (int i = 0; i < cbCoinList.size(); i++) {
            CbCoin cbCoin = cbCoinList.get(i);
            if (cbCoin.getStatus() != CbCoin.Status.Disable.getValue()) {
                CbMarketDepth cbMarketDepth = getGateMarketDepth(cbCoin.getSymbol());
                cbMarketDepth.setScale1min(getScale1minData(cbCoin.getSymbol()));
                cbMarketDepthRepository.save(cbMarketDepth);
            }
        }
    }

    /**
     * 获取一分钟内买卖深度 比
     *
     * @return
     */
    private BigDecimal getScale1minData(String symbol) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -1);
        List<CbMarketDepth> cbMarketDepths = cbMarketDepthRepository.findByCreateAt(symbol, calendar.getTime());
        BigDecimal buy = new BigDecimal(0);
        BigDecimal sell = new BigDecimal(0);
        for (CbMarketDepth marketDepth : cbMarketDepths){
            buy = buy.add(marketDepth.getBids());
            sell = sell.add(marketDepth.getAsks());
        }
        if(sell.compareTo(new BigDecimal(0))>0){
            return buy.divide(sell, 5, BigDecimal.ROUND_HALF_EVEN);
        }else{
            return new BigDecimal(0);
        }

    }

    private CbMarketDepth getGateMarketDepth(String symbol) {
        String host = "https://data.gateio.io/api2/1/orderBook/" + symbol + "_usdt";
        HttpResponse httpResponse = null;
        try {
            httpResponse = HttpUtils.doGet(host, "", "", new HashMap<>(), null);
            logger.info("result=" + httpResponse.getStatusLine());
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String httpRequestStr = EntityUtils.toString(httpResponse.getEntity());
                logger.info("获取" + symbol + "市场深度：" + httpRequestStr);
                JSONObject jsonObject = JSON.parseObject(httpRequestStr);

                if ("true".equals(jsonObject.getString("result"))) {
                    JSONArray asks = jsonObject.getJSONArray("asks");
                    JSONArray bids = jsonObject.getJSONArray("bids");
                    BigDecimal asksBD = new BigDecimal("0");
                    for (int i = 0; i < asks.size(); i++) {
                        JSONArray asksItem = asks.getJSONArray(i);
                        asksBD = asksBD.add(asksItem.getBigDecimal(0).multiply(asksItem.getBigDecimal(1)));
                    }
                    BigDecimal bidsBD = new BigDecimal("0");
                    for (int i = 0; i < bids.size(); i++) {
                        JSONArray bidsItem = bids.getJSONArray(i);
                        bidsBD = bidsBD.add(bidsItem.getBigDecimal(0).multiply(bidsItem.getBigDecimal(1)));
                    }
                    CbMarketDepth cbMarketDepth = new CbMarketDepth();
                    cbMarketDepth.setAsks(asksBD);
                    cbMarketDepth.setBids(bidsBD);
                    cbMarketDepth.setSymbol(symbol);
                    cbMarketDepth.setCreate_at(new Date());
                    return cbMarketDepth;
                } else {
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
