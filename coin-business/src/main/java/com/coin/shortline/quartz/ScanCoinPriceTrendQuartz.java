package com.coin.shortline.quartz;

import com.coin.shortline.service.CoinPriceTrendService;
import com.coin.shortline.service.MarketDepthService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class ScanCoinPriceTrendQuartz implements Job{

    protected static final Logger logger = LoggerFactory.getLogger(ScanCoinPriceTrendQuartz.class);

    @Autowired
    CoinPriceTrendService coinPriceTrendService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("........开始刷新价格趋势...start.......");
        logger.info("........价格趋势刷新完成...end.......");
    }
}
