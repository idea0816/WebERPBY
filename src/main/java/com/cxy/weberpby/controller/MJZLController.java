package com.cxy.weberpby.controller;

import com.cxy.weberpby.service.MJZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time: 2022/5/4
 * @Description 模具明细资料(頁面)
 */

@Controller
public class MJZLController {

    @Autowired
    private MJZLService mjzlService;

    @GetMapping("/MJZLList")
    public String MJZLList(Model model) {
        // 模具資料
        model.addAttribute("MLList", mjzlService.getML());
        return "MJZLList";
    }
}
