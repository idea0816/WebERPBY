package com.cxy.weberpby.controller;

import com.cxy.weberpby.model.DDZL;
import com.cxy.weberpby.model.DDZLS;
import com.cxy.weberpby.service.DDZLService;
import com.cxy.weberpby.service.timeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/9
 * @Description 訂單資料(傳值)
 *
 * @GetMapping("/DDZL/renewDDZL")  // renew DDZL  // 导入鞋厂订单资料
 * @GetMapping("/DDZL/getDDZL") // get DDZL
 * @GetMapping("/DDZL/getDDZLS/{DDBH}") // get DDZLS
 */

@RestController
@CrossOrigin
public class DDZLRController {

    @Autowired
    private DDZLService ddzlService;

    @Autowired
    private timeService timeService;

    // update DDZL
    // 在鞋型合併時再算料 DDZLS1 & DDZLS2
    @GetMapping("/DDZL/renewDDZL/{StarDate}/{EndDate}")
    public String renewDDZL(@PathVariable String StarDate, @PathVariable String EndDate) {
        // 20220522改為手動依日期區間導入
        return ddzlService.renewDDZL(StarDate, EndDate);
    }

    // 取得訂單資料
    @GetMapping("/DDZL/getDDZL/{StarDate}/{EndDate}")
    public List<DDZL> getDDZL(@PathVariable String StarDate, @PathVariable String EndDate) {
        // 20220522改為手動依日期區間導入
        return ddzlService.getDDZL(StarDate, EndDate);
    }

    // 取得訂單資料(尺寸、雙數)
    @GetMapping("/DDZL/getDDZLS/{DDBH}")
    public List<DDZLS> getDDZLS(@PathVariable String DDBH) {
        return ddzlService.getDDZLS(DDBH);
    }

    // test retun date
//    @GetMapping("/testdate")
//    public String getDate(){
//        return timeService.date2String("2022-05-09");
//    }
}
