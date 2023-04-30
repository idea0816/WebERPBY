package com.cxy.weberpby.controller;

import com.cxy.weberpby.service.KSYDService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CXY
 * @version Create Time: 2022/11/30
 * @Description 入出庫資料
 *
 * 月底寫入鞋廠 KCLL & KCLLS
 */
@RestController
public class KSYDController {
    @Resource
    private KSYDService ksydService;

    // 月底寫入鞋廠 KCLL & KCLLS
    @GetMapping("/KSYD/insKCLL")
    public String insKCLL(){
        ksydService.insKCLL();
        return "success";
    }
}
