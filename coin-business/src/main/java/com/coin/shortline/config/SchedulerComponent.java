package com.coin.shortline.config;

import com.coin.shortline.quartz.ScanMarketDepthQuartz;
import com.coin.shortline.quartz.ScanMarketBusinessAmountQuartz;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Configuration
public class SchedulerComponent {

    protected static final Logger logger = LoggerFactory.getLogger(SchedulerComponent.class);

    @Value("${scan.marketdepth.cron}")
    private String marketdepth;//买卖深度corn
    @Value("${scan.businessamount.cron}")
    private String businessamount;//交易量corn

    @Autowired
    SchedulerJobFactory schedulerJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(schedulerJobFactory);
        logger.info("***************schedulerFactoryBean init***************");
        return schedulerFactoryBean;
    }

    public void scheduleJobs(SchedulerFactoryBean schedulerFactoryBean ) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        startScanMarketDepth(scheduler);
        startScanMarketBusinessAmount(scheduler);
    }

    /**
     * 交易深度（买方和卖方的比例）
     * @param scheduler
     * @throws SchedulerException
     */
    public void startScanMarketDepth(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ScanMarketDepthQuartz.class)
                .withIdentity("job1", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(marketdepth);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();
    }

    /**
     * 市场交易量
     * @param scheduler
     * @throws SchedulerException
     */
    public void startScanMarketBusinessAmount(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ScanMarketBusinessAmountQuartz.class)
                .withIdentity("job2", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(businessamount);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();
    }



    @PreDestroy
    public void onDestroy(){
        try {
            schedulerFactoryBean().getScheduler().shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }



}
