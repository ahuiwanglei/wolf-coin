package com.coin.shortline.controller.manager;

import com.coin.shortline.entity.manger.MenuTree;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/web/index/")
public class IndexController {
    @RequestMapping()
    public String index() {
        System.out.println("ssss");
        return "home";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "menutree", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public List<MenuTree> getAdminMenuTree(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        return sysRoleService.getAdminMenuTree(admin);
        List<MenuTree> menuTrees = new ArrayList<>();
        MenuTree parent0 =  new MenuTree();
        parent0.setId("0");
        parent0.setText("监控配置");
        parent0.setUrl("");

        MenuTree sub01 = new MenuTree();
        sub01.setText("权重配置");
        sub01.setUrl("");
        sub01.setId("01");

        MenuTree sub02 = new MenuTree();
        sub02.setText("监控币种");
        sub02.setUrl("/web/coin/index");
        sub02.setId("02");

        List<MenuTree> sub0List = new ArrayList<>();
        sub0List.add(sub01);
        sub0List.add(sub02);
        parent0.setChildren(sub0List);

        menuTrees.add(parent0);

        MenuTree sub11 = new MenuTree();
        sub11.setText("市场行情");
        sub11.setId("11");
        sub11.setUrl("/web/coin_weight/index");
        List<MenuTree> sub1List = new ArrayList<>();
        sub1List.add(sub11);

        MenuTree parent1 =  new MenuTree();
        parent1.setId("1");
        parent1.setText("数据查询");
        parent1.setUrl("");
        parent1.setChildren(sub1List);
        menuTrees.add(parent1);

        return menuTrees;
    }
}
