package com.cxy.weberpby.service;

import com.cxy.weberpby.model.MJZL;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/4
 * @Description 模具明细资料
 *
 * List<String> getErpMJ(); //取得鞋廠的大底&刀模資料
 * List<MJZL> getML();  // 取得模具资料(mjbh, lbdh)
 */
public interface MJZLService {

    //取得鞋廠的大底&刀模資料
    List<String> getErpMJ();

    // 取得模具资料(mjbh, lbdh)
    List<MJZL> getML();
}
