package com.coin.shortline.controller.manager;

import com.coin.shortline.dao.CbCoinWeightHistoryRepository;
import com.coin.shortline.entity.CbCoinWeightHistory;
import com.coin.shortline.entity.manger.MobilePage;
import com.coin.shortline.util.CreatePageRequest;
import com.coin.shortline.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/web/coin_weight/")
public class ManagerCoinWeightController {

    @Autowired
    private CbCoinWeightHistoryRepository cbCoinWeightHistoryRepository;

    /**
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "coin/coin_weight_list";
    }


    @RequestMapping(value = "/list/json", method = RequestMethod.POST)
    @ResponseBody
    public MobilePage getCompanyRegisterList(HttpServletRequest request) {
        int page = StringUtil.getInt(request.getParameter("page"));
        int rows = StringUtil.getInt(request.getParameter("rows"));
        String searchkey = request.getParameter("searchkey");

        Pageable pageable = CreatePageRequest.buildPageRequest(page, rows, "desc", "id");
        Page<CbCoinWeightHistory> activityPages = cbCoinWeightHistoryRepository.findBySearchKeyAndPage("%" + searchkey + "%", pageable);
        return new MobilePage(activityPages.getTotalElements(), activityPages.getContent());
    }

}
