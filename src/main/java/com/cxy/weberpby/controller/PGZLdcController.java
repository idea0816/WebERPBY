package com.cxy.weberpby.controller;

import com.cxy.weberpby.service.DDZLService;
import com.cxy.weberpby.service.PGZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time: 2022/5/26
 * @Description 大車派工資料(頁面)
 */
@Controller
public class PGZLdcController {

    @Autowired
    private DDZLService ddzlService;

    @Autowired
    private PGZLService pgzlService;

    @GetMapping("/PGZLdc")
    public String PGZLdc(Model model) {
        // 取得鞋廠輪次資料
        model.addAttribute("DDZLList", ddzlService.getDDZLbyXXZLS());
        // 取得大車派工資料
        model.addAttribute("getPGZL", pgzlService.getPGZL("DC"));
        // 取得鞋廠單位資料
        model.addAttribute("depList", pgzlService.getdepList());

        return "PGZLdc";
    }
}
