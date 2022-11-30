package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.dto.llUpdateParams;
import com.cxy.weberpby.model.LLZLS;
import com.cxy.weberpby.service.CLZLService;
import com.cxy.weberpby.service.LLZLService;
import com.cxy.weberpby.service.PGZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/30
 * @Description 領料作業(頁面)
 *
 * public String llwml(Model model) // 萬馬力領料單號
 * public List<PGZLS1> getPGZLS1(@PathVariable String PGDATE) // Get PGZLS1 and 算料
 *
 */

@Controller
public class LLZLController {

    @Autowired
    private PGZLService pgzlService;

    @Autowired
    private CLZLService clzlService;

    // 配方類查詢條件設定
    CLZLQueryParams clzlQueryParams;

    @Autowired
    private LLZLService llzlService;

    // 領料單號
    @GetMapping("/LLZL")
    public String LLZL(Model model){
        clzlQueryParams = new CLZLQueryParams();
        // 取得所有原物料 AW
        clzlQueryParams.setLb("A");
        model.addAttribute("getAllAList", clzlService.getCLZL(clzlQueryParams));
        // 取得領料單頭
        model.addAttribute("getLLZL", llzlService.getLLZL("N"));
        return "LLZL";
    }

    // Get LLZLS
    @ResponseBody
    @GetMapping("/LLZL/getLLZLS/{LLBH}")
    public List<LLZLS> getLLZLS(@PathVariable String LLBH){
        return llzlService.getLLZLS(LLBH);
    }

    // 確認領料後Update LLZL.LLRQ,CFM & LLZLS.KGS_YL,KGS_CL & PGZL.CFM
    @ResponseBody
    @PutMapping("/LLZL/updateLLZL")
    public String updateLLZL(@RequestBody llUpdateParams lup) {
        // llzlService.updateLLZL(lup);
        return "success";
    }

}
