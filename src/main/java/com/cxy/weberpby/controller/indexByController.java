package com.cxy.weberpby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 宝亿进入点
 * <p>
 * GetMapping("/index") // 主页(頁面)
 * GetMapping("/front") // 前段Menu
 * GetMapping("/manager")   // 生管
 * GetMapping("/blank") // 填充用的空白頁
 */

@Controller
public class indexByController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/front")
    public String front() {
        return "front";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/blank")
    public String blank() {
        return "blank";
    }

}
