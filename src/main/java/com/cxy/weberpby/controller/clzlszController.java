package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.service.CLZLService;
import com.cxy.weberpby.service.lbzlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time: 2022/4/29
 * @Description 標準配方資料(頁面)
 *
 * public String clzlsz(Model model)    // 標準配方頁面  // Get 標準配方主资料   // 取得所有原物料 AW
 */

@Controller
public class clzlszController {

    @Autowired
    private CLZLService clzlService;

    @Autowired
    private lbzlsService lbzlsService;

    // 配方類查詢條件設定
    CLZLQueryParams clzlQueryParams;

    // 標準配方頁面
    @GetMapping("/clzlsz")
    public String clzlsz(Model model) {
        clzlQueryParams = new CLZLQueryParams();
        // Get 標準配方主资料
        clzlQueryParams.setLb("R");
        model.addAttribute("getAllRList", clzlService.getCLZL(clzlQueryParams));
        // 取得所有原物料 AW
        clzlQueryParams.setLb("A");
        model.addAttribute("getAllAList", clzlService.getCLZL(clzlQueryParams));
        // 取得配方類別資料
        model.addAttribute("getlbzlsList", lbzlsService.getlbzlsList("06"));

        return "clzlsz";
    }
}
