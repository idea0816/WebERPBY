package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 订单資料
 * <p>
 * List<String> getDDBHerp(String StarDate, String EndDate);  // 取得鞋厂订单资料（订单号）(*注意、是已放行的订单*)
 * String DDZLInsert(DDZL ddzl);    // 写入订单资料 From ERP
 * String DDZLSInsert(DDZL ddzl);    // 写入 Size & 双数 资料 From ERP
 * void DDZLS1Insert(DDZLS1 ddzls1);    // 写入 部位 & 配方 资料
 * void DDZLS1Delete(String DDBH);  // 刪除 部位 & 配方 资料
 * List<DDZL> getDDZL(String StarDate, String EndDate);    // 取得訂單資料
 * List<DDZL> getDDZL(String DDBH);    // 取得訂單資料 by DDBH
 * List<DDZL> getDDZLbyXXZLS();  // 取得订单资料（依XXZLS有配方的)
 * List<DDZLS> getDDZLS(String DDBH);    // 取得訂單資料(尺寸、數量)
 * List<DDZLS1> getDDZLS1(String DDBH);    // 取得訂單配方
 * List<DDZLS1> getDDZLS1();    // 取得訂單配方(有Group by)
 *
 * List<SMDD> getSMDD(String DDBH); // 取得鞋廠生管派工
 * List<SMDDS> getSMDDS(String DDBH, String Round);   // 導入鞋廠輪次明細資料
 */

public interface DDZLDao {

    // 取得鞋厂订单资料（订单号）
    List<String> getDDBHerp(String StarDate, String EndDate);

    // 写入订单资料 From ERP
    String DDZLInsert(DDZL ddzl);

    // 写入 Size & 双数 资料 From ERP
    String DDZLSInsert(DDZL ddzl);

    // 写入 部位 & 配方 资料
    void DDZLS1Insert(DDZLS1 ddzls1);

    // 刪除 部位 & 配方 资料
    void DDZLS1Delete(String DDBH);

    // 取得訂單資料
    List<DDZL> getDDZL(String StarDate, String EndDate);

    // 取得訂單資料 by DDBH
    List<DDZL> getDDZL(String DDBH);

    // 取得订单资料（依XXZLS有配方的)
    List<DDZL> getDDZLbyXXZLS();

    // 取得訂單資料(尺寸、數量)
    List<DDZLS> getDDZLS(String DDBH);

    // 取得訂單配方
    List<DDZLS1> getDDZLS1(String DDBH);

    // 取得訂單配方(有Group by)
    List<DDZLS1> getDDZLS1();

    // 取得鞋廠生管派工
    List<SMDD> getSMDD(String DDBH, String dw);

    // 導入鞋廠輪次明細資料
    List<SMDDS> getSMDDS(String DDBH, String Round);
}
