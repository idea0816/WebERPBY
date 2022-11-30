package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.BDepartment;
import com.cxy.weberpby.model.PGZL;
import com.cxy.weberpby.model.PGZLS;
import com.cxy.weberpby.model.PGZLS1;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description 派工資料
 *
 * String getVersion(String PGDATE);  // 取得派工最新單號
 * List<PGZL> getPGZL(String BZ);   // 取得派工表頭
 * List<PGZLS> getPGZLS(String PGDATE); // Get PGZLS
 * List<PGZLS> getPGZLSdc(String PGDATE); // Get PGZLSdc
 * void insertPGZL(PGZL pgzl);    // Insert Data to PGZL
 * void insertPGZLS(PGZLS pgzls);    // Insert Data to PGZLS
 * void insertPGZLS1(PGZLS1 pgzls1);    // Insert Data to PGZLS1
 * List<PGZLS1> getPGZLS1(String PGDATE); // Get PGZLS1 *
 * List<BDepartment> getdepList();    // 取得鞋廠部門資料
 *
 * void inserPGZLS1_temp(PGZLS1 pgzls1);    // Insert Data to PGZLS1_temp(以後要刪)
 * List<PGZLS1> getPGZLS1_temp(); // Get PGZLS1_temp(以後要刪)
 * void deletePGZLS1_temp();  // 刪除 PGZLS1_temp(以後要刪)
 */
public interface PGZLDao {

    // 取得派工最新單號
    List<String> getVersion(String PGDATE);

    // 取得派工表頭
    List<PGZL> getPGZL(String BZ);

    // Get PGZLS
    List<PGZLS> getPGZLS(String PGDATE);

    // Get PGZLSdc
    List<PGZLS> getPGZLSdc(String PGDATE);

    // Insert Data to PGZL
    void insertPGZL(PGZL pgzl);

    // Insert Data to PGZLS
    void insertPGZLS(PGZLS pgzls);

    // Insert Data to PGZLS1
    void insertPGZLS1(PGZLS1 pgzls1);

    // Get PGZLS1
    List<PGZLS1> getPGZLS1(String PGDATE);

    // 取得鞋廠部門資料
    List<BDepartment> getdepList();

    // Insert Data to PGZLS1_temp(以後要刪)
    void inserPGZLS1_temp(PGZLS1 pgzls1);

    // Get PGZLS1_temp(以後要刪)
    List<PGZLS1> getPGZLS1_temp();

    // 刪除 PGZLS1_temp(以後要刪)
    void deletePGZLS1_temp();
}
