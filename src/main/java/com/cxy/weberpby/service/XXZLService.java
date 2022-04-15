package com.cxy.weberpby.service;

import com.cxy.weberpby.model.XXZL;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体資料
 *
 * String updateDdXXZL();   // 自动导入鞋厂型体资料
 * List<XXZL> getErpXiexing();  // 取得鞋厂订单的型体资料(*注意、是已放行的订单*)
 */

public interface XXZLService {

    // 自动导入鞋厂型体资料
    String updateDdXXZL();

    // 取得鞋厂订单的型体资料(*注意、是已放行的订单*)
    List<XXZL> getErpXiexing();
}
