package com.cxy.weberpby.controller;

import com.cxy.weberpby.service.autoImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CXY
 * @version Create Time: 2022/5/2
 * @Description 部位資料(頁面)-未完成
 *
 * 資料寫在lbzlsl裡、預設96～99(舊系統裡不顯示-lbzl裡不寫入)
 * -- 96,底模
 * -- 97,大車
 * -- 98,中底,沒有建部位也沒關係
 * -- 99,合併部件
 *
 * 導入部位資料
 */

@Controller
public class bwController {

    @Autowired
    private autoImportService autoImportService;

    // 部位資料頁
    @ResponseBody
    @GetMapping("/bwList")
    public String bwList(Model model) {

        // 導入部位資料
        model.addAttribute("", "");
        return autoImportService.insertBW();
    }

}
