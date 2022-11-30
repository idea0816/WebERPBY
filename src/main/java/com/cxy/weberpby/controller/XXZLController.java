package com.cxy.weberpby.controller;

import com.cxy.weberpby.service.XXZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time: 2022/5/3
 * @Description 鞋型資料(頁面)
 */

@Controller
public class XXZLController {

    @Autowired
    private XXZLService xxzlService;

    // 型體頁面
    @GetMapping("/XXZLList")
    public String XXZLList(Model model) {
//        model.addAttribute("XSAList", xxzlService.getXSA());
        model.addAttribute("getArticleList", xxzlService.getARTICLE());
        model.addAttribute("getNa", xxzlService.getXSANA());

        return "XXZLList";
    }

}
