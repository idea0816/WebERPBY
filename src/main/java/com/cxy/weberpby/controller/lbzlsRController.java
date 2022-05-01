package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.lbzlUpdateParams;
import com.cxy.weberpby.model.lbzls;
import com.cxy.weberpby.service.lbzlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/28
 * @Description 代号类别明细资料(傳值)
 * <p>
 * public List<lbzls> getlbzlsList(@PathVariable String lb) // 類別明細資料
 * public String updatelbzl(@RequestBody lbzlUpdateParams lup)  // Update lbzl & lbzls
 * public String insertlbzl(@RequestBody lbzlUpdateParams lup)  // Insert lbzl & lbzls
 * public String deletelbzl(@RequestBody String lb) // Delete lbzl & lbzls
 */

@RestController
public class lbzlsRController {

    @Autowired
    private lbzlsService lbzlsService;

    // 類別明細資料
    @GetMapping("/lbzls/getlbzlsList/{lb}")
    public List<lbzls> getlbzlsList(@PathVariable String lb) {
        return lbzlsService.getlbzlsList(lb);
    }

    // Update lbzl & lbzls
    @PutMapping("/lbzls/updatelbzl")
    public String updatelbzl(@RequestBody lbzlUpdateParams lup) {
        lbzlsService.updatelbzl(lup);
        return "success";
    }

    // Insert lbzl & lbzls
    @PostMapping("/lbzls/insertlbzl")
    public String insertlbzl(@RequestBody lbzlUpdateParams lup) {
        lbzlsService.insertlbzl(lup);
        return "success";
    }

    // Delete lbzl & lbzls
    @DeleteMapping("/lbzls/deletelbzl")
    public String deletelbzl(@RequestBody String lb) {
        lbzlsService.deletelbzl(lb);
        return "success";
    }
}
