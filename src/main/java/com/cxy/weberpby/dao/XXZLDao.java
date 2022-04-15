package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.XXZL;
import com.cxy.weberpby.model.XXZLS;
import com.cxy.weberpby.model.XXZLS1;
import com.cxy.weberpby.model.XXZLSERP;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体資料
 *
 * List<XXZL> getErpXiexing();  // 取得鞋厂订单的型体资料(*注意、是已放行的订单*)
 * List<XXZLSERP> getErpXxzls();    // 取得鞋厂型体部位资料
 * List<XXZL> getDdXiexing();   // 取得底厂的型体资料(部份)
 * String XXZLInsert(XXZL xxzl);    // 写入型体资料
 * String XXZLS1Insert(XXZLS1 xxzls1);  // 写入 Size & 重量 资料
 * String XXZLSInsert(XXZLS xxzls); // 写入部位 & 配方资料
 */

public interface XXZLDao {

    // 取得鞋厂订单的型体资料(*注意、是已放行的订单*)
    List<XXZL> getErpXiexing();

    // 取得鞋厂型体部位资料
    List<XXZLSERP> getErpXxzls(XXZL xxzl);

    // 取得底厂的型体资料
    List<XXZL> getDdXiexing();

    // 写入型体资料
    String XXZLInsert(XXZL xxzl);

    // 写入 Size & 重量 资料
    String XXZLS1Insert(XXZLS1 xxzls1);

    // 写入部位 & 配方资料
    String XXZLSInsert(XXZLS xxzls);
}
