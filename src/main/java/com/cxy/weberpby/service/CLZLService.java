package com.cxy.weberpby.service;

import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.dto.CLZLUpdateParams;
import com.cxy.weberpby.model.CLZL;
import com.cxy.weberpby.model.clzlsl;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 材料基本資料讀取
 * <p>
 * String updateDdCLZLa();  // 自动导入材料基本资料
 * List<CLZL> getCLZL(CLZLQueryParams clzlQueryParams); // Get CLZL
 * void updateCLZL(String cllb, CLZLUpdateParams cup);   // Update CLZL
 * void insertCLZL(String cllb, CLZLUpdateParams cup);   // Insert CLZL
 * void deleteCLZL(String cldh, String CGLB);    // Delete CLZL(配方有版次問題、所以多傳一個版次資料)
 */

public interface CLZLService {

    // 自动导入材料基本资料
    String updateDdCLZLa();

    // Get CLZL
    List<CLZL> getCLZL(CLZLQueryParams clzlQueryParams);

    // Update CLZL
    void updateCLZL(String cllb, CLZLUpdateParams cup);

    // Insert CLZL
    void insertCLZL(String cllb, CLZLUpdateParams cup);

    // Delete CLZL(配方有版次問題、所以多傳一個版次資料)
    void deleteCLZL(String cldh);
}
