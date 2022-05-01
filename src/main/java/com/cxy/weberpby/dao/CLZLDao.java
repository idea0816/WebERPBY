package com.cxy.weberpby.dao;

import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.model.CLZL;
import com.cxy.weberpby.model.clzlsl;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 材料基本資料讀取
 * <p>
 * List<CLZL> getCLZL(CLZLQueryParams clzlQueryParams);    // CLZL Data  // Get V(传回的值要考虑 NULL 的状态吗？) // Get A
 * List<clzlsl> getclzlsz(String cldh, String cllb);    // Get clzlsz
 * void insertCLZL(CLZL CLZL);  // 新增CLZL資料
 * void updateCLZL(CLZL CLZL);  // Update CLZL
 * void deleteCLZL(String cldh);  // Delete CLZL
 * List<CLZL> getOldDdCLZLa();  // 取得小于本月的底厂材料基本资料
 */

public interface CLZLDao {

    // Get CLZL
    List<CLZL> getCLZL(CLZLQueryParams clzlQueryParams);

    // Get clzlsz
    List<clzlsl> getclzlsz(String cldh);

    // 新增CLZL資料
    void insertCLZL(CLZL CLZL);

    // Update CLZL
    void updateCLZL(CLZL CLZL);

    // Delete CLZL
    void deleteCLZL(String cldh);

    // 取得小于本月的底厂材料基本资料
    List<CLZL> getOldDdCLZLa(String yyyyMM);

}
