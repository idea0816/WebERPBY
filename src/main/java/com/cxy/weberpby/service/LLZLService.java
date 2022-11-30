package com.cxy.weberpby.service;

import com.cxy.weberpby.model.LLZL;
import com.cxy.weberpby.model.LLZLS;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/6/2
 * @Description 申領資料(LLZL & LLZLS)
 *
 * String getVersion()  // 取得領料最新單號
 * List<LLZLS> insertLLZL(String LLBH, String PGDATE);    // Insert LLZL  & LLZLS(新增領料單以派工單為基礎、在派工單中已算好料直接傳過來即可、同時若已有領料單則回傳)
 * List<LLZLS> insertLLZLdc(String LLBH, List pgdateDC);    // for 大車領料單
 * List<LLZL> getLLZL(String CFM);   // getLLZL(取得領料單表頭)
 * List<LLZLS> getLLZLS(String LLBH);   // getLLZLS
 */
public interface LLZLService {

    // 取得領料最新單號
    String getVersion();

    // Insert LLZL
    List<LLZLS> insertLLZL(String LLBH, String PGDATE);

    // for 大車領料單
    List<LLZLS> insertLLZLdc(String LLBH, List<String> pgdateDC);


    // getLLZL(取得有效領料單表頭)
    List<LLZL> getLLZL(String CFM);

    // getLLZLS
    List<LLZLS> getLLZLS(String LLBH);
}
