package com.cxy.weberpby.service;

import com.cxy.weberpby.dto.PGZLUpdateParams;
import com.cxy.weberpby.dto.llUpdateParams;
import com.cxy.weberpby.model.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description 派工資料
 *
 * String getVersion(String dw)  // 取得派工最新單號
 * List<PGZL> getPGZL(String BZ);   // 取得派工表頭
 * List<PGZLS> getPGZLS(String PGDATE); // Get PGZLS
 * void setPGZLS1(String PGDATE);   // set PGZLS1 and 算料
 * List<PGZLS1> getPGZLS1(String PGDATE); // Get PGZLS1
 * List<PGZLS1> setPGZLS1dc(List pgdateDC);   // for PGZLS1dc 算料
 * List<PGZLS1> getPGZLS1dc(String PGDATE);   // Get PGZLS1dc
 * void insertPGZL(PGZLUpdateParams pup);   // Insert PGZL & PGZLS
 * void updatePGZLS1(llUpdateParams lup);   // Update pgzls1
 *
 * List<SMDD> getSMDD(String DDBH); // 導入鞋廠輪次資料表頭
 * List<SMDDS> getSMDDS(String DDBH, String Round);   // 導入鞋廠輪次明細資料
 *
 * List<String> getdcpgPrint(String DDBH, String PGDATE)    // 取得大車派工列印資料
 * List<BDepartment> getdepList();    // 取得鞋廠部門資料
 */
public interface PGZLService {

    // 取得派工最新單號
    String getVersion(String dw);

    // 取得派工表頭
    List<PGZL> getPGZL(String BZ);

    // Get PGZLS
    List<PGZLS> getPGZLS(String PGDATE);

    // set PGZLS1 and 算料
    void setPGZLS1(String PGDATE);

    // Get PGZLS1
    List<PGZLS1> getPGZLS1(String PGDATE);

    // PGZLS1dc 算料
    // List<PGZLS1> setPGZLS1dc(String DDBH, String size, String qty);
    void setPGZLS1dc(String pgdateDC);

    // Get PGZLS1dc
    List<PGZLS1> getPGZLS1dc(String PGDATE);

    // Insert PGZL & PGZLS
    void insertPGZL(PGZLUpdateParams pup);

    // Update pgzls1
    void updatePGZLS1(llUpdateParams lup);

    // 導入鞋廠輪次資料表頭
    List<SMDD> getSMDD(String DDBH, String dw);

    // 導入鞋廠輪次明細資料
    List<SMDDS> getSMDDS(String DDBH, String Round);

    // 取得大車派工列印資料
    List<DDZLS1> getdcpgPrint(String DDBH, String PGDATE);

    // 取得鞋廠部門資料
    List<BDepartment> getdepList();
}
