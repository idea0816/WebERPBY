package com.cxy.weberpby.service;

/**
 * @author CXY
 * @version Create Time: 2022/5/2
 * @Description 導入的功能放在這裡、不要混雜在其他Service中
 *
 * 自動導入指的是將鞋廠的資料抓取到底廠的資料裡(*注意、对应已放行的订单*)
 *
 * String insertBW();   // 導入部位資料
 * String insertXXZL(); // 导入型体资料
 * String insertCLZL(); // 导入配方资料
 * String inserDDZLS1();    // 導入訂單配方資料
 */

public interface autoImportService {

    // 導入部位資料
    String insertBW();

    // 导入型体资料
    String insertXXZL();

    // 导入配方资料
    String insertCLZL();

    // 導入訂單配方資料
    String inserDDZLS1();
}
