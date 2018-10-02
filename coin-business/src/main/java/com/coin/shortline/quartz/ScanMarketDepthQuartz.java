package com.coin.shortline.quartz;

import com.coin.shortline.service.MarketDepthService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class ScanMarketDepthQuartz implements Job{

    protected static final Logger logger = LoggerFactory.getLogger(ScanMarketDepthQuartz.class);

    @Autowired
    MarketDepthService marketDepthService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("........开始刷新市场深度...start.......");
        logger.info("........市场深度刷新完成...end.......");
    }
}
