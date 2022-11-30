package com.cxy.weberpby.dao;

import com.cxy.weberpby.dto.PGZLUpdateParams;
import com.cxy.weberpby.model.LLZL;
import com.cxy.weberpby.model.LLZLS;
import com.cxy.weberpby.model.LLZLS_temp;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/6/7
 * @Description 申領資料(LLZL & LLZLS)
 *
 * List<String> getVersion(String LLBH);  // 取得領料最新單號
 * void insertLLZL(LLZL llzl);   // Insert LLZL
 * void insertLLZLS(LLZLS llzls);   // Inseert LLZLS
 * void insertLLZLS_temp(LLZLS_temp llzls_temp);   // Inseert LLZLS_temp
 * List<LLZL> getLLZL(String CFM);   // getLLZL(取得領料單表頭)
 * List<LLZLS> getLLZLS(String LLBH);   // getLLZLS
 * List<LLZLS> checkLLZLS(String PGDATE);   // checkLLZLS
 */
public interface LLZLDao {

    // 取得領料最新單號
    List<String> getVersion(String LLBH);

    // Insert LLZL
    void insertLLZL(LLZL llzl);

    // Inseert LLZLS
    void insertLLZLS(LLZLS llzls);

    // Inseert LLZLS_temp
    void insertLLZLS_temp(LLZLS_temp llzls_temp);

    // getLLZL(取得領料單)
    List<LLZL> getLLZL(String CFM);

    // getLLZLS
    List<LLZLS> getLLZLS(String LLBH);

    // checkLLZLS
    List<LLZLS> checkLLZLS(String PGDATE);
}
