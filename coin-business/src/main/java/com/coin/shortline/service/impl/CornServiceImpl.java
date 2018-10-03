package com.coin.shortline.service.impl;

import com.coin.shortline.dao.CbCoinRepository;
import com.coin.shortline.dao.CbCoinWeightHistoryRepository;
import com.coin.shortline.dao.CbMarketDepthRepository;
import com.coin.shortline.dao.CbPriceTrendsRepository;
import com.coin.shortline.entity.CbCoin;
import com.coin.shortline.entity.CbCoinWeightHistory;
import com.coin.shortline.entity.CbMarketDepth;
import com.coin.shortline.entity.CbPriceTrends;
import com.coin.shortline.entity.gate.CoinWeight;
import com.coin.shortline.quartz.ScanMarketBusinessAmountQuartz;
import com.coin.shortline.service.CoinService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CornServiceImpl implements CoinService {

    protected static final Logger logger = LoggerFactory.getLogger(CornServiceImpl.class);

    @Autowired
    CbCoinRepository cbCoinRepository;

    @Autowired
    CbMarketDepthRepository cbMarketDepthRepository;

    @Autowired
    CbPriceTrendsRepository cbPriceTrendsRepository;

    @Autowired
    CbCoinWeightHistoryRepository cbCoinWeightHistoryRepository;


    @Value("${coin.depth.weight}")
    BigDecimal depth_weight;
    @Value("${coin.businessamount.weight}")
    BigDecimal businessamount;
    @Value("${coin.price.weight}")
    BigDecimal priceweight;

    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;


    @Override
    public CoinWeight findCornWeight(String symbol) {
        //前1分钟于当前1分钟内 交易量比 占40%
        //前1分钟于当前1分钟内 深度比   占40%
        //前1分钟于当前1分钟内 价格比   占20%
        Cache cache = ehCacheCacheManager.getCacheManager().getCache("coinCache");
        CoinWeight coinWeight = null;
        String key = "value_" + symbol;
        if (cache == null || cache.get(key) == null) {
            coinWeight = new CoinWeight();
            coinWeight.setBusiness_amount_weight(businessamount);
            coinWeight.setMarket_depth_weight(depth_weight);
            coinWeight.setPrice_weight(priceweight);
            coinWeight.setSymbol(symbol);
            Element element = new Element(key, coinWeight);
            cache.put(element);
        } else {
            coinWeight = (CoinWeight) cache.get(key).getObjectValue();
        }
        BigDecimal[] amounts = getBusinessAmountRate(symbol);
        coinWeight.setBusiness_amount_rate(amounts[0]);
        coinWeight.setPrice_rate(amounts[1]);
        coinWeight.setMarket_depth_rate(getMarketDepthRate(symbol));
        return coinWeight;
    }

    @Override
    public void storeCoinWeightHistory() {

        logger.info("........计算权重值...start.......");
        List<CbCoin> cbCoinList = cbCoinRepository.findAll();
        for (int i = 0; i < cbCoinList.size(); i++) {
            CbCoin cbCoin = cbCoinList.get(i);
            logger.info("正在计算交易权重：" + cbCoin.getSymbol());
            if (cbCoin.getStatus() != CbCoin.Status.Disable.getValue()) {
                CoinWeight coinWeight = findCornWeight(cbCoin.getSymbol());
                BigDecimal weight = coinWeight.getRealWeight();
                logger.info("当前交易权重值为：" + weight);
                logger.info("当前交易权重值组成：交易量比：" + coinWeight.getBusiness_amount_rate() + "  买卖深度：" + coinWeight.getMarket_depth_rate() + "  价格比：" + coinWeight.getPrice_rate());

                CbCoinWeightHistory cbCoinWeightHistory = new CbCoinWeightHistory();
                BeanUtils.copyProperties(coinWeight, cbCoinWeightHistory);
                cbCoinWeightHistory.setCreate_at(new Date());
                cbCoinWeightHistory.setComplex_weight(coinWeight.getRealWeight());
                cbCoinWeightHistoryRepository.save(cbCoinWeightHistory);
            }
        }
        logger.info("........计算权重值...end.......");
    }

    private BigDecimal getMarketDepthRate(String symbol) {
        CbMarketDepth cbMarketDepth = cbMarketDepthRepository.findPageMarketDepth(symbol, 0);
        CbMarketDepth cbMarketDepth1min = cbMarketDepthRepository.findPageMarketDepth(symbol, 6);
        if(cbMarketDepth1min.getScale1min().compareTo(new BigDecimal(0)) > 0){
            return cbMarketDepth.getScale1min().divide(cbMarketDepth1min.getScale1min(), 5, BigDecimal.ROUND_HALF_EVEN);
        }else{
            return new BigDecimal(0);
        }
    }

    private BigDecimal[] getBusinessAmountRate(String symbol) {
        BigDecimal[] datas = new BigDecimal[2];
        CbPriceTrends cbPriceTrends = cbPriceTrendsRepository.findPagePriceTrends(symbol, 0);
        CbPriceTrends cbPriceTrend1s = cbPriceTrendsRepository.findPagePriceTrends(symbol, 6);
        if(cbPriceTrend1s != null && cbPriceTrends != null && cbPriceTrends.getVolume_1min().compareTo(new BigDecimal(0))>0){
            datas[0] = cbPriceTrend1s.getVolume_1min().divide(cbPriceTrends.getVolume_1min(), 5, BigDecimal.ROUND_HALF_EVEN);
        }else{
            datas[0] = new BigDecimal(0);
        }
        if(cbPriceTrend1s != null  && cbPriceTrends != null && cbPriceTrends.getAmount().compareTo(new BigDecimal(0))>0){
            datas[1] = cbPriceTrend1s.getAmount().divide(cbPriceTrends.getAmount(), 5, BigDecimal.ROUND_HALF_EVEN);
        }else{
            datas[1] = new BigDecimal(0);
        }
        return datas;
    }


}
