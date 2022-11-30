package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.service.CLZLService;
import com.cxy.weberpby.service.MJZLService;
import com.cxy.weberpby.service.lbzlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/4
 * @Description 部位规格建立(模具、部位、Size對照、標準配方)(頁面)
 */

@Controller
public class bwBuildController {

    @Autowired
    private lbzlsService lbzlsService;
    @Autowired
    private MJZLService mjzlService;

    @Autowired
    private CLZLService clzlService;

    // 配方類查詢條件設定
    CLZLQueryParams clzlQueryParams;

    // 部位规格建立(模具、部位、Size對照、標準配方)
    @GetMapping("/bwBuild")
    public String bwBuild(Model model) {
        // 取得鞋廠部位資料
        model.addAttribute("bwList", lbzlsService.get2BW());
        // 取得鞋廠模具資料
        model.addAttribute("erpMJList", mjzlService.getErpMJ());
        // Get 標準配方主资料
        clzlQueryParams = new CLZLQueryParams();
        clzlQueryParams.setLb("R");
        model.addAttribute("getAllRList", clzlService.getCLZL(clzlQueryParams));

        return "bwBuild";
    }

    // Test
    @GetMapping("/test")
    @ResponseBody
    public List<String> getData() {
        return mjzlService.getErpMJ();
    }

}
