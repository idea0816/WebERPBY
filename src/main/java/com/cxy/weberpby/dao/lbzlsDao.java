package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.lbzl;
import com.cxy.weberpby.model.lbzls;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 代号类别明细资料 - 上一层是 lbzl
 * <p>
 * Integer checkBwlbzls(String BWBH);    // 检查部位类别明细资料是否存在
 * String lbzlsBwInsert();    // 写入部位类别明细资料
 * List<lbzl> getlbzlList();   // 取得類別資料
 * List<lbzls> getlbzlsList();  // 取得明细资料
 * void insertlbzl(lbzl lbzl);   //新增lbzl資料
 * void updatelbzl(lbzl lbzl);   //修改lbzl資料
 * void deletelbzl(String lb);   // 刪除lbzl資料
 * void insertlbzls(lbzls lbzls);   //新增lbzls資料
 * void deletelbzls(String lb);   // 刪除lbzls資料
 */

public interface lbzlsDao {

    // 检查类别明细资料是否存在
    Integer checkBwlbzls(String BWBH);

    // 写入类别资料
    String lbzlsBwInsert(lbzls lbzls);

    // 取得類別資料
    List<lbzl> getlbzlList();

    // 取得所有类别明细资料
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
