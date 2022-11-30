package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.DDZLDao;
import com.cxy.weberpby.model.DDZL;
import com.cxy.weberpby.model.DDZLS;
import com.cxy.weberpby.model.DDZLS1;
import com.cxy.weberpby.service.DDZLService;
import com.cxy.weberpby.service.timeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 订单資料
 * <p>
 * String renewDDZL(String StarDate, String EndDate);   // 导入鞋厂订单资料
 * List<DDZL> getDDZL(String StarDate, String EndDate);    // 取得訂單資料
 * List<DDZL> getDDZLbyERP();    // 取得訂單資料(把DDJQ換成鞋廠上線日)
 * List<DDZL> getDDZLbyXXZLS();  // 取得订单资料（依XXZLS有配方的且排除已派工的)
 * List<DDZLS> getDDZLS(String DDBH);
 * List<DDZLS1> getDDZLS1();    // 取得訂單配方(有Group by)
 */

@Component
public class DDZLServiceImpl implements DDZLService {

    @Autowired
    private DDZLDao ddzlDao;
    @Autowired
    private timeService timeservice;

    // 导入鞋厂订单资料
    @Override
    public String renewDDZL(String StarDate, String EndDate) {

        // 取得鞋厂订单编号
        List<String> DDBHerp = ddzlDao.getDDBHerp(StarDate, EndDate);
        // System.out.println(DDBHerp.size());
        if (DDBHerp != null){
            // 写入底厂订单资料
            DDZL ddzl = new DDZL();
            for (String s : DDBHerp) {
                ddzl.setDDBH(s);
                 ddzl.setUSERDATE(timeservice.now());
                // 写入底厂订单资料
                ddzlDao.DDZLInsert(ddzl);
                // 写入底厂 Size & 双数 资料
                ddzlDao.DDZLSInsert(ddzl);
            }
            return "共导入" + DDBHerp.size() + "筆订单資料";
        }else {
            return "没有新的订单資料";
        }
    }

    // 取得訂單資料
    @Override
    public List<DDZL> getDDZL(String StarDate, String EndDate) {
        String transSD = timeservice.date2String(StarDate);
        String transED = timeservice.date2String(EndDate);
        List<DDZL> tranDate = ddzlDao.getDDZL(transSD, transED);
        for(DDZL value: tranDate){
            value.setDDRQ(timeservice.forWebDate(value.getDDRQ()));
            value.setDDJQ(timeservice.forWebDate(value.getDDJQ()));
        }
        return tranDate;
    }

    // 取得訂單資料(把DDJQ換成鞋廠上線日)
    @Override
    public List<DDZL> getDDZLbyERP() {
//        List<DDZL> tranDate = ddzlDao.getDDZL();
//        // 依訂單查鞋廠上線日並置換
//        for(DDZL value: tranDate){
//
//        }
        return null;
    }

    // 取得订单资料（依XXZLS有配方的且排除已派工的)
    @Override
    public List<DDZL> getDDZLbyXXZLS() {
        return ddzlDao.getDDZLbyXXZLS();
    }

    // 取得訂單資料(尺寸、數量)
    @Override
    public List<DDZLS> getDDZLS(String DDBH) {
        return ddzlDao.getDDZLS(DDBH);
    }

    // 取得訂單配方(有Group by)
    @Override
    public List<DDZLS1> getDDZLS1() {
        return ddzlDao.getDDZLS1();
    }
}
