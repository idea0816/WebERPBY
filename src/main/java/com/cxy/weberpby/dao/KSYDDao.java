package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.KSYD;
import com.cxy.weberpby.model.KSYDS;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/31
 * @Description 入出庫資料
 *
 * 取得 KSYD 資料
 * 取得 KSYDS 資料
 * 月底寫入鞋廠 KCLL & KCLLS
 */
public interface KSYDDao {

    // 取得 KSYD 資料
    List<KSYD> getKSYDs();
    // 取得 KSYDS 資料
    List<KSYDS> getKSYDSs();
    // 月底寫入鞋廠 KCLL & KCLLS
}
