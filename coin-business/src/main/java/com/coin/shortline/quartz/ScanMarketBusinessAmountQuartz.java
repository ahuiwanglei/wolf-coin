package com.coin.shortline.quartz;

import com.coin.shortline.service.MarketBusinessAmountService;
import com.coin.shortline.service.MarketDepthService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 市场交易量
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class ScanMarketBusinessAmountQuartz implements Job{

    protected static final Logger logger = LoggerFactory.getLogger(ScanMarketBusinessAmountQuartz.class);

    @Autowired
    MarketBusinessAmountService marketBusinessAmountService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("........开始刷新市场交易量...start.......");
        logger.info("........市场交易量刷新完成...end.......");
    }
}
