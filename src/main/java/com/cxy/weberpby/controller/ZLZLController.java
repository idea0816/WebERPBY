package com.cxy.weberpby.controller;

import com.cxy.weberpby.service.DDZLService;
import com.cxy.weberpby.service.XXZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time: 2022/5/19
 * @Description 订单鞋型合并(頁面)
 */

@Controller
public class ZLZLController {
    @Autowired
    private XXZLService xxzlService;

    @Autowired
    private DDZLService ddzlService;

    // 订单鞋型合并(頁面)
    @GetMapping("/ZLZL")
    public String XXZLList(Model model) {
        model.addAttribute("getArticleList", xxzlService.getARTICLE());

        return "ZLZL";
    }
}
