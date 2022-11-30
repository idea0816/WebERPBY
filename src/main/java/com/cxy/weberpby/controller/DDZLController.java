package com.cxy.weberpby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time: 2022/5/6
 * @Description 訂單資料(頁面)
 */

@Controller
public class DDZLController {

    @GetMapping("/DDZL")
    public String DDZL(){
        return "DDZL";
    }
}
