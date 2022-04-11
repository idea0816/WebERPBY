package com.cxy.weberpby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 宝亿进入点
 *
 * @GetMapping("/indexBY") 主页
 */

@Controller
public class indexByController {

    @GetMapping("/indexBY")
    public String indexBY(){
        return "indexBY";
    }
}
