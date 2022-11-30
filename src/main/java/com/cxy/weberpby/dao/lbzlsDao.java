package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.lbzl;
import com.cxy.weberpby.model.lbzls;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 代号类别明细资料 - 上一层是 lbzl
 * <p>
 * List<lbzls> get2BW(); // 取得鞋廠部位(編號、名稱)
 * List<lbzls> get2BWbw(String lb); // 取得底廠部位(編號、名稱)
 * List<lbzls> getBW(String bwbh); // 取得鞋廠部位資料
 * List<lbzl> getlbzlList();   // 取得类别lbzl資料
 * List<lbzls> getlbzlsList();  // 取得lbzls類別明細资料
 * void insertlbzl(lbzl lbzl);   //新增lbzl資料
 * void updatelbzl(lbzl lbzl);   //修改lbzl資料
 * void deletelbzl(String lb);   // 刪除lbzl資料
 * void insertlbzls(lbzls lbzls);   //新增lbzls資料
 * void deletelbzls(String lb);   // 刪除lbzls資料
 */

public interface lbzlsDao {

    // 取得鞋廠部位(編號、名稱)
    List<lbzls> get2BW();

    // 取得底廠部位(編號、名稱)
    List<lbzls> get2BWbw(String lb);

    // 取得鞋廠部位資料
    List<lbzls> getBW(String bwbh);

    // 取得类别lbzl資料
    List<lbzl> getlbzlList();

    // 取得lbzls類別明細资料
    List<lbzls> getlbzlsList(String lb);

    //新增lbzl資料
    void insertlbzl(lbzl lbzl);

    //修改lbzl資料
    void updatelbzl(lbzl lbzl);

    // 刪除lbzl資料
    void deletelbzl(String lb);

    //新增lbzls資料
    void insertlbzls(lbzls lbzls);

    // 刪除lbzls資料
    void deletelbzls(String lb);
}
