package com.coin.shortline.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coin.shortline.service.MarketDepthService;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 市场深度，交易量
 */
@Service
public class MarketDepthServiceImpl implements MarketDepthService {

    protected static final Logger logger = LoggerFactory.getLogger(MarketDepthServiceImpl.class);

//    @Autowired
//    private MarketListRepository marketListRepository;
//
//    @Autowired
//    private MarketListConfigRepository marketListConfigRepository;
//
//    @Autowired
//    private OrderBookRepository orderBookRepository;
//
//    @Override
//    public List<MarketList> getAllList() {
//        return marketListRepository.findAllMarketList();
//    }
//
//    @Override
//    public List<CoinMarketConfig> getConfigAllList() {
//        return marketListConfigRepository.findAll();
//    }


//    @Transactional
//    @Override
//    public MarketList udpateRemind(MarketList marketList) {
//        marketList.setIs_remind(0);
//        marketListRepository.saveAndFlush(marketList);
//        return marketList;
//    }

//    @Transactional
//    @Override
//    public void storeMarketList() {
//        logger.info("**********************更新币种排行start***********************");
//        String host = "https://data.gateio.io/api2/1/marketlist";
//        HttpResponse httpResponse = null;
//        try {
//            httpResponse = HttpUtils.doGet(host, "", "", new HashMap<>(), null);
//            logger.info("result=" + httpResponse.getStatusLine());
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                String httpRequestStr = EntityUtils.toString(httpResponse.getEntity());
//                Gson gson = new Gson();
//                GateMarkeList gateMarkeList = gson.fromJson(httpRequestStr, GateMarkeList.class);
//                for (int i = 0; i < gateMarkeList.getData().size(); i++) {
//                    GateMarkeList.DataBean dataBean = gateMarkeList.getData().get(i);
//                    MarketList marketList = marketListRepository.findNoId(dataBean.getNo());
//                    if (marketList == null) {
//                        marketList = new MarketList();
//                        marketList.setNoid(dataBean.getNo());
//                        marketList.setSymbol(dataBean.getSymbol());
//                        marketList.setName(dataBean.getName());
//                        marketList.setRank(i + 1);
//                        marketList.setHistory_rank(0);
//                        marketList.setIs_remind(0);
//                    } else {
//                        if (marketList.getRank() != i + 1) {
//                            logger.info("币种：" + dataBean.getSymbol() + " 上次排名：" + marketList.getRank() + " 本次排名：" + (i + 1));
////                            CoinMarketConfig coinMarketConfig = marketListConfigRepository.findSymbol(dataBean.getSymbol());
//                            //只要排名上下浮动达到预设的就要提醒
////                            if (coinMarketConfig != null && coinMarketConfig.getUpgrade_rank_value() < Math.abs(marketList.getRank() - (i + 1))) {
//                                marketList.setIs_remind(1);
////                            }
//                            marketList.setHistory_rank(marketList.getRank());
//                            marketList.setRank(i + 1);
//                        }
//                    }
//                    marketList.setMarketcap(dataBean.getMarketcap());
//                    marketList.setRate(dataBean.getRate());
//                    marketList.setRate_percent(dataBean.getRate_percent());
//                    marketListRepository.save(marketList);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        logger.info("**********************更新币种排行end***********************");
//    }
//
//    @Transactional
//    @Override
//    public void storeOrderBooks() {
//        logger.info("**********************更新市场深度 start***********************");
//        String host = "https://data.gateio.io/api2/1/orderBooks";
//        HttpResponse httpResponse = null;
//        try {
//            httpResponse = HttpUtils.doGet(host, "", "", new HashMap<>(), null);
//            logger.info("result=" + httpResponse.getStatusLine());
//            if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                String httpRequestStr = EntityUtils.toString(httpResponse.getEntity());
//                Gson gson = new Gson();
//                JSONObject jsonObject = JSON.parseObject(httpRequestStr);
//                Set<String> keySet = jsonObject.keySet();
//                List<Orderbook> orderbooks = new ArrayList<>();
//                for (String key : keySet) {
//                    Orderbook orderbook = orderBookRepository.findBySymbol(key);
//                    if(orderbook == null){
//                        orderbook = new Orderbook();
//                    }
//                    orderbook.setSymbol(key);
//                    JSONArray asks = jsonObject.getJSONObject(key).getJSONArray("asks");
//                    JSONArray bids = jsonObject.getJSONObject(key).getJSONArray("bids");
//                    BigDecimal asksBD =  new BigDecimal("0");
//                    for (int i = 0; i < asks.size(); i++) {
//                        JSONArray asksItem = asks.getJSONArray(i);
//                        asksBD = asksBD.add(asksItem.getBigDecimal(0).multiply(asksItem.getBigDecimal(1)));
//                    }
//                    BigDecimal bidsBD =  new BigDecimal("0");
//                    for (int i = 0; i < bids.size(); i++) {
//                        JSONArray bidsItem = bids.getJSONArray(i);
//                        bidsBD = bidsBD.add(bidsItem.getBigDecimal(0).multiply(bidsItem.getBigDecimal(1)));
//                    }
//                    orderbook.setAsks(asksBD.stripTrailingZeros().toPlainString());
//                    orderbook.setBids(bidsBD.stripTrailingZeros().toPlainString());
//                    if(bidsBD.compareTo(new BigDecimal("0"))>0){
//                        orderbook.setRate(asksBD.divide(bidsBD, 6, BigDecimal.ROUND_HALF_UP));
//                    }else{
//                        orderbook.setRate(new BigDecimal("0"));
//                    }
//                    orderbooks.add(orderbook);
//                }
//                orderBookRepository.save(orderbooks);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        logger.info("**********************更新市场深度 end***********************");
//    }
//
//    @Override
//    public List<Orderbook> getOrderbooksList() {
//        return orderBookRepository.findAllOrderbook();
//    }
}
