package com.cxy.weberpby.controller;

import com.cxy.weberpby.service.lbzlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time: 2022/4/20
 * @Description 代号类别明细资料(頁面)
 * <p>
 * GetMapping("/lbzls") // 类别资料頁
 */

@Controller
public class lbzlsController {

    @Autowired
    private lbzlsService lbzlsService;

    // 类别资料頁
    @GetMapping("/lbzls")
    public String lbzlList(Model model) {
        // 取得類別資料
        model.addAttribute("getlbzlList", lbzlsService.getlbzlList());
        return "lbzls";
    }

}
