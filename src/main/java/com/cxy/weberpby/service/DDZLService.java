package com.cxy.weberpby.service;

import com.cxy.weberpby.model.DDZL;
import com.cxy.weberpby.model.DDZLS;
import com.cxy.weberpby.model.DDZLS1;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 订单資料
 * <p>
 * String renewDDZL(String StarDate, String EndDate);   // 导入鞋厂订单资料
 * List<DDZL> getDDZL(String StarDate, String EndDate);    // 取得訂單資料
 * List<DDZL> getDDZLbyERP();    // 取得訂單資料(把DDJQ換成鞋廠上線日)
 * List<DDZLS> getDDZLS(String DDBH);    // 取得訂單資料(尺寸、數量)
 * List<DDZL> getDDZLbyXXZLS();  // 取得订单资料（依XXZLS有配方的且排除已派工的)
 * List<DDZLS1> getDDZLS1();    // 取得訂單配方(有Group by)
 */

public interface DDZLService {

    // 自动导入鞋厂订单资料
    String renewDDZL(String StarDate, String EndDate);

    // 取得訂單資料
    List<DDZL> getDDZL(String StarDate, String EndDate);

    // 取得訂單資料(把DDJQ換成鞋廠上線日)
    List<DDZL> getDDZLbyERP();

    // 取得訂單資料(尺寸、數量)
    List<DDZLS> getDDZLS(String DDBH);

    // 取得订单资料（依XXZLS有配方的且排除已派工的)
    List<DDZL> getDDZLbyXXZLS();

    // 取得訂單配方(有Group by)
    List<DDZLS1> getDDZLS1();
}
