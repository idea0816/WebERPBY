package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.service.CLZLService;
import com.cxy.weberpby.service.DDZLService;
import com.cxy.weberpby.service.PGZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time: 2022/5/20
 * @Description 萬馬力派工資料(頁面)
 */

@Controller
public class PGZLController {

    @Autowired
    private DDZLService ddzlService;
    @Autowired
    private CLZLService clzlService;

    @Autowired
    private PGZLService pgzlService;

    // 配方類查詢條件設定
    CLZLQueryParams clzlQueryParams;

    @GetMapping("/PGZL")
    public String PGZL(Model model) {
        clzlQueryParams = new CLZLQueryParams();
        // Get 配方主资料
        clzlQueryParams.setLb("V");
        model.addAttribute("getAllVRList", clzlService.getCLZL(clzlQueryParams));
        // Get 訂單中的胚料
        model.addAttribute("getVRfromDDZL", ddzlService.getDDZLS1());
        // 取得萬馬力派工資料
        model.addAttribute("getPGZL", pgzlService.getPGZL("WML"));
        return "PGZL";
    }
}
