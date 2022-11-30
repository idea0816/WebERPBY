package com.cxy.weberpby.service;

import com.cxy.weberpby.model.XXZL;
import com.cxy.weberpby.model.XXZLS;
import com.cxy.weberpby.model.XXZLSERP;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体資料
 * <p>
 * String countNoXXZLData();  // 檢查有多少型體沒有部位資料
 * List<XXZL> getXSANA();    // 列出沒有部位的ARTICLE
 * List<String> getARTICLE();   // 取得ARTICLE
 * List<XXZLSERP> getXXZLSErp(String ARTICLE);    // 取得鞋廠BOM表
 * List<XXZL> getXSA();   // 取得型体资料(XieXing, SheHao, ARTICLE)
 * void insertXXZLS(List<XXZLS> insertXXZLS);    // Insert XXZLS
 * void updateXXZLS(List<XXZLS> updateXXZLS);    // Update XXZLS
 */

public interface XXZLService {

    // 檢查有多少型體沒有部位資料
    String countNoXXZLData();

    // 列出沒有部位的ARTICLE
    List<XXZL> getXSANA();

    // 取得ARTICLE
    List<String> getARTICLE();

    // 取得鞋廠BOM表
    List<XXZLSERP> getXXZLSErp(String ARTICLE);

    // 取得型体资料(XieXing, SheHao, ARTICLE)
    List<XXZL> getXSA();

    // Insert XXZLS
    void insertXXZLS(List<XXZLS> insertXXZLS);

    // Update XXZLS
    void updateXXZLS(List<XXZLS> updateXXZLS);
}
