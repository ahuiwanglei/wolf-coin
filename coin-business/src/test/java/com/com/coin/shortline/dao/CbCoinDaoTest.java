package com.com.coin.shortline.dao;

import com.coin.shortline.StartupApplication;
import com.coin.shortline.dao.CbCoinRepository;
import com.coin.shortline.entity.CbCoin;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.JVM)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StartupApplication.class)
public class CbCoinDaoTest {

    @Autowired
    CbCoinRepository cbCoinRepository;

    @Test
    public void insert(){
        CbCoin cbCoin = new CbCoin();
        cbCoin.setPlatform("gate");
        cbCoin.setSymbol("dpy");
        cbCoin.setFees(new BigDecimal(0.002));
        cbCoin.setPosition_count(0L);
        cbCoin.setUnit_amont(new BigDecimal(0));
        cbCoin.setBuy_threshold_rate(new BigDecimal(1));
        cbCoin.setSell_threshold_rate(new BigDecimal(0.1));
        cbCoin.setAll_sell_threshold_rate(new BigDecimal(0.01));
        cbCoin.setStatus(CbCoin.Status.Normal.getValue());
        cbCoin.setCreate_at(new Date());
        cbCoinRepository.saveAndFlush(cbCoin);
        Assert.assertTrue(cbCoin.getId() >0);
    }

    @Test
    public void findOne(){
      CbCoin coin =  cbCoinRepository.findOne(1);
      Assert.assertTrue(coin != null);
    }
}
