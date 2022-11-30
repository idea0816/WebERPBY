package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.MJZL;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/4
 * @Description 模具明细资料
 *
 * List<String> getErpDD();   // 取得鞋廠大底模具資料
 * List<String> getErpDM();   // 取得鞋廠刀模模具資料
 * List<MJZL> getML();  // 取得模具资料(mjbh, lbdh)
 */
public interface MJZLDao {

    // 取得鞋廠模具資料
    List<String> getErpDD();

    // 取得鞋廠刀模模具資料
    List<String> getErpDM();

    // 取得模具资料(mjbh, lbdh)
    List<MJZL> getML();
}
