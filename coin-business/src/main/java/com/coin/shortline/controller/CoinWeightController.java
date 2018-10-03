package com.coin.shortline.controller;

import com.coin.shortline.entity.gate.CoinWeight;
import com.coin.shortline.service.CoinService;
import com.coin.shortline.util.Result;
import com.coin.shortline.util.ResultFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/")
public class CoinWeightController {

    @Autowired
    CoinService coinService;

    @PostMapping(value = "coinweight")
    public Result getCoinWeight(HttpServletRequest request) {
        String symbol = request.getParameter("symbol");
        if(StringUtils.isEmpty(symbol)){
            return ResultFactory.getErrorResult("symbol不能为空");
        }
        CoinWeight coinWeight = coinService.findCornWeight(symbol);
        return ResultFactory.getSuccessResult(coinWeight);
    }
}
