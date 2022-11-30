package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.PGZLUpdateParams;
import com.cxy.weberpby.model.*;
import com.cxy.weberpby.service.PGZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/26
 * @Description 大車派工資料(傳值)
 * <p>
 * public List<SMDD> getSMDD(@PathVariable String DDBH) // 導入鞋廠輪次資料表頭
 * public List<SMDDS> getSMDDS(@PathVariable String DDBH, @PathVariable String Round)   // 導入鞋廠輪次明細資料
 * public List<PGZLS> getPGZLS(@PathVariable String PGDATE) // Get PGZLS
 * public List<PGZLS1> setPGZLS1dc(@PathVariable String DDBH,
 * @PathVariable String size,
 * @PathVariable String qty)    // DC算料(廢棄)
 * public String insertPGZL(@RequestBody PGZLUpdateParams pup)  // Insert PGZL & PGZLS
 *
 * public List<String> getdcpgPrint(String DDBH, String PGDATE)    // 取得大車派工列印資料
 */
@RestController
public class PGZLdcRController {
    @Autowired
    private PGZLService pgzlService;

    // 導入鞋廠輪次資料表頭
    @GetMapping("/PGZL/getSMDD/{DDBH}")
    public List<SMDD> getSMDD(@PathVariable String DDBH) {
        return pgzlService.getSMDD(DDBH, "D");
    }

    // 導入鞋廠輪次明細資料
    @GetMapping("/PGZL/getSMDDS/{DDBH}/{Round}")
    public List<SMDDS> getSMDDS(@PathVariable String DDBH, @PathVariable String Round) {
        return pgzlService.getSMDDS(DDBH, Round);
    }

    // Get PGZLS
    @GetMapping("/PGZLdc/getPGZLS/{PGDATE}")
    public List<PGZLS> getPGZLS(@PathVariable String PGDATE) {
        return pgzlService.getPGZLS(PGDATE);
    }

    // Get PGZLS1
    @GetMapping("/PGZLdc/getPGZLS1dc/{PGDATE}")
    public List<PGZLS1> getPGZLS1dc(@PathVariable String PGDATE) {
        return pgzlService.getPGZLS1dc(PGDATE);
    }

    // Insert PGZL & PGZLS
    @PostMapping("/PGZLdc/insertPGZL/")
    public String insertPGZL(@RequestBody PGZLUpdateParams pup) {
        pgzlService.insertPGZL(pup);
        return "success";
    }

    // 取得大車派工列印資料
    @GetMapping("/PGZLdc/getdcpgPrint/{DDBH}/{PGDATE}")
    public List<DDZLS1> getdcpgPrint(@PathVariable String DDBH,
                                     @PathVariable String PGDATE){

        return pgzlService.getdcpgPrint(DDBH, PGDATE);
    }
}
