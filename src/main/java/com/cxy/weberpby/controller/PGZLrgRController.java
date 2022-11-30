package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.PGZLUpdateParams;
import com.cxy.weberpby.model.DDZLS1;
import com.cxy.weberpby.model.PGZLS;
import com.cxy.weberpby.model.SMDD;
import com.cxy.weberpby.service.PGZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/6/9
 * @Description 打粗派工資料(傳值)
 *
 * public List<SMDD> getSMDD(@PathVariable String DDBH) // 導入鞋廠輪次資料表頭
 * public String insertPGZL(@RequestBody PGZLUpdateParams pup)  // Insert PGZL & PGZLS
 * public List<PGZLS> getPGZLS(@PathVariable String PGDATE) // Get PGZLS
 * public List<DDZLS1> getrgpgPrint(@PathVariable String DDBH, @PathVariable String PGDATE) // 取得打粗派工列印資料
 */

@RestController
public class PGZLrgRController {
    @Autowired
    private PGZLService pgzlService;

    // 導入鞋廠輪次資料表頭
    @GetMapping("/PGZLrg/getSMDD/{DDBH}")
    public List<SMDD> getSMDD(@PathVariable String DDBH) {
        return pgzlService.getSMDD(DDBH, "R");
    }

    // Insert PGZL & PGZLS
    @PostMapping("/PGZLrg/insertPGZL/")
    public String insertPGZL(@RequestBody PGZLUpdateParams pup) {
        pgzlService.insertPGZL(pup);
        return "success";
    }

    // Get PGZLS
    @GetMapping("/PGZLrg/getPGZLS/{PGDATE}")
    public List<PGZLS> getPGZLS(@PathVariable String PGDATE) {
        return pgzlService.getPGZLS(PGDATE);
    }

    // 取得打粗派工列印資料
    @GetMapping("/PGZLrg/getrgpgPrint/{DDBH}/{PGDATE}")
    public List<DDZLS1> getrgpgPrint(@PathVariable String DDBH,
                                     @PathVariable String PGDATE){

        return pgzlService.getdcpgPrint(DDBH, PGDATE);
    }
}
