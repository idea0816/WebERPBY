package com.cxy.weberpby.controller;

import com.cxy.weberpby.model.LLZLS;
import com.cxy.weberpby.service.LLZLService;
import com.cxy.weberpby.service.PGZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/6/2
 * @Description 領料單列印頁面(合併所有的派工)
 * <p>
 * public List<LLZLS> getLLZLS(@PathVariable String PGDATE) // Get LLZLS(insertPGZLS1 & 算料 & insert LLZL & LLZLS)
 * *
 */

@Controller
public class PGZLS1Controller {

    @Autowired
    private PGZLService pgzlService;

    @Autowired
    private LLZLService llzlService;

    @GetMapping("/PGZLS1")
    public String getPGZL(Model model) {
        // 取得派工單號
        model.addAttribute("getPGZL", pgzlService.getPGZL(""));
        return "PGZLS1";
    }

    // Get LLZLS(insertPGZLS1 & 算料 & insert LLZL & LLZLS)
    @ResponseBody
    @GetMapping("/PGZLS1/getLLZLS/{PGDATE}")
    public List<LLZLS> getLLZLS(@PathVariable String PGDATE) {
        // 萬馬力算料
        pgzlService.setPGZLS1(PGDATE);
        // 產生領料單並回傳
        String LLBH = llzlService.getVersion();
        return llzlService.insertLLZL(LLBH, PGDATE);
    }

    // Get LLZLS(insertPGZLS1 & 算料 & insert LLZL & LLZLS)
    @ResponseBody
    @GetMapping("/PGZLS1/getLLZLSdc/{PGDATE1}/{PGDATE2}/{PGDATE3}/{PGDATE4}/{PGDATE5}")
    public List<LLZLS> getLLZLS(@PathVariable String PGDATE1,
                                @PathVariable String PGDATE2,
                                @PathVariable String PGDATE3,
                                @PathVariable String PGDATE4,
                                @PathVariable String PGDATE5) {
        List<String> pgdateDC = new ArrayList<>();
        pgdateDC.add(PGDATE1);
        pgdateDC.add(PGDATE2);
        pgdateDC.add(PGDATE3);
        pgdateDC.add(PGDATE4);
        pgdateDC.add(PGDATE5);

        // 移除空值
        pgdateDC.removeIf(s -> s.equals(" "));

        for (String value : pgdateDC) {
            // 大車算料
            pgzlService.setPGZLS1dc(value);
            pgzlService.getPGZLS1dc(value);
        }

        // 產生領料單
        String LLBH = llzlService.getVersion();
        return llzlService.insertLLZLdc(LLBH, pgdateDC);
    }
}
