package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.PGZLUpdateParams;
import com.cxy.weberpby.model.PGZLS;
import com.cxy.weberpby.service.PGZLService;
import com.cxy.weberpby.service.autoImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description 派工資料(傳值)
 *
 * public String insertDDZLS1() // 導入訂單配方資料
 * public String getVersion()  // 取得派工最新單號
 * public List<PGZLS> getPGZLS(@PathVariable String PGDATE) // Get PGZLS
 * public String insertPGZL(@RequestBody PGZLUpdateParams pup)  // Insert PGZL
 */
@RestController
public class PGZLRController {

    @Autowired
    private PGZLService pgzlService;

    @Autowired
    private autoImportService autoImportService;

    // 導入訂單配方資料
    @GetMapping("/PGZL/insertDDZLS1")
    public String insertDDZLS1(){
        return autoImportService.inserDDZLS1();
    }

    // 取得派工最新單號
    @GetMapping("/PGZL/getVersion/{dw}")
    public String getVersion(@PathVariable String dw) {
        return pgzlService.getVersion(dw);
    }

    // Get PGZLS
    @GetMapping("/PGZL/getPGZLS/{PGDATE}")
    public List<PGZLS> getPGZLS(@PathVariable String PGDATE) {
        return pgzlService.getPGZLS(PGDATE);
    }

    // Insert PGZL
    @PostMapping("/PGZL/insertPGZL/")
    public String insertPGZL(@RequestBody PGZLUpdateParams pup) {
        pgzlService.insertPGZL(pup);
        return "success";
    }
}
