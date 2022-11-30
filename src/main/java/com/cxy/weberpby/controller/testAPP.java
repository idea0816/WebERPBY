package com.cxy.weberpby.controller;

import com.cxy.weberpby.model.CLZL;
import com.cxy.weberpby.model.lbzls;
import com.cxy.weberpby.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testAPP {

    @Autowired
    private XXZLService xxzlService;
    @Autowired
    private CLZLService clzlService;

    @Autowired
    private PGZLService pgzlService;


    @Autowired
    private autoImportService autoImportService;

    @Autowired
    private lbzlsService lbzlsService;

    // 取得部位类别资料
    @GetMapping("/getlbzlsBwList")
    public List<lbzls> getlbzlsBwList() {
        return lbzlsService.getlbzlsList("90");
    }

    // updateDDCLZLa
    @GetMapping("/updateDdCLZL")
    public String updateDdCLZL() {
        return clzlService.updateDdCLZLa();
    }

    // getCLZL(V)
    @GetMapping("/getCLZL")
    public List<CLZL> getCLZLV() {
        return clzlService.getCLZL(null);
    }

    // 標準配方R轉換V
    @GetMapping("/transCLZL")
    public String insertCLZL(){
        return autoImportService.insertCLZL();
    }

    // Test
//    @GetMapping("/getPGZL")
//    public List<XXZL> getPgZL(){
//        return xxzlService.getXSANA();
//    }

}
