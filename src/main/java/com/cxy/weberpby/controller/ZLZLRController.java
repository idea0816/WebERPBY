package com.cxy.weberpby.controller;

import com.cxy.weberpby.model.DDZL;
import com.cxy.weberpby.service.DDZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/19
 * @Description 订单鞋型合并(傳值)
 */

@RestController
public class ZLZLRController {

    @Autowired
    private DDZLService ddzlService;

    public List<DDZL> getDDZLbyERP(){

        return ddzlService.getDDZLbyERP();
    }
}
