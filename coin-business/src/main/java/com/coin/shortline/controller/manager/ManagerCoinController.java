package com.coin.shortline.controller.manager;


import com.coin.shortline.dao.CbCoinRepository;
import com.coin.shortline.entity.CbCoin;
import com.coin.shortline.entity.CbCoinWeightHistory;
import com.coin.shortline.entity.manger.MobilePage;
import com.coin.shortline.util.CreatePageRequest;
import com.coin.shortline.util.StringUtil;
import com.coin.shortline.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/web/coin/")
public class ManagerCoinController {

    @Autowired
    private CbCoinRepository cbCoinRepository;
    /**
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "coin/index";
    }

    @RequestMapping(value = "/list/json", method = RequestMethod.POST)
    @ResponseBody
    public MobilePage getCompanyRegisterList(HttpServletRequest request) {
        int page = StringUtil.getInt(request.getParameter("page"));
        int rows = StringUtil.getInt(request.getParameter("rows"));
        String searchkey = request.getParameter("searchkey");

        Pageable pageable = CreatePageRequest.buildPageRequest(page, rows, "desc", "id");
        Page<CbCoin> activityPages = cbCoinRepository.findBySearchKeyAndPage("%" + searchkey + "%", pageable);
        return new MobilePage(activityPages.getTotalElements(), activityPages.getContent());
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, String> delete(@PathVariable("id") int id) {
        cbCoinRepository.delete(id);
        return WebUtil.httpNotice("1", "删除成功");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map<String, String> createOrUpdate(HttpServletRequest request) {
        String coin_symbol = request.getParameter("coin_symbol");
        String coin_fees = request.getParameter("coin_fees");
        String coin_buy_threshold_rate = request.getParameter("coin_buy_threshold_rate");
        String coin_sell_threshold_rate = request.getParameter("coin_sell_threshold_rate");
        String coin_all_sell_threshold_rate = request.getParameter("coin_all_sell_threshold_rate");
        String coin_status = request.getParameter("coin_status");
        String coin_id = request.getParameter("coin_id");
        if(StringUtil.isEmpty(coin_id)){
            coin_id ="-1";
        }
        int id = StringUtil.getInt(coin_id);
        CbCoin coin = cbCoinRepository.findOne(id);
        if(coin == null){
            coin = new CbCoin();
            coin.setSymbol(coin_symbol);
            coin.setCreate_at(new Date());
            coin.setUnit_amont(new BigDecimal(0));
            coin.setPosition_count(new BigDecimal(0));
        }
        coin.setFees(new BigDecimal(coin_fees));
        coin.setBuy_threshold_rate(new BigDecimal(coin_buy_threshold_rate));
        coin.setSell_threshold_rate(new BigDecimal(coin_sell_threshold_rate));
        coin.setAll_sell_threshold_rate(new BigDecimal(coin_all_sell_threshold_rate));
        coin.setStatus(StringUtil.getInt(coin_status));

        cbCoinRepository.save(coin);
        return WebUtil.httpNotice("1", "保存成功");
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable(value = "id") int id, Model model) {
        CbCoin cbCoin = cbCoinRepository.findOne(id);
        model.addAttribute("coin", cbCoin);
        return "coin/show";
    }



}
