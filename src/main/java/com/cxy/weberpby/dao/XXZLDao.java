package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体資料
 * <p>
 * List<XXZL> getErpXSA();  // 取得鞋廠型体资料(XieXing, SheHao, ARTICLE)(*注意、是已放行的订单*)
 * List<XXZL> getXSA();   // 取得型体资料(XieXing, SheHao, ARTICLE)
 *
 * String XXZLInsert(XXZL xxzl);    // 写入型体资料 From ERP
 * void XXZLSInsert(XXZLS xxzls); // 写入部位 & 配方资料
 * void XXZLSDelete(String XS); // 刪除部位 & 配方资料
 * String XXZLS1Insert(XXZLS1 xxzls1);  // 写入 Size 资料 From ERP
 * void XXZLS1Update(String sql);  // 写入重量资料
 *
 * 以下為新開發
 * List<String> getErpBWBH(); // 取得鞋厂部位編號(BOM、部位編號)(先取得全部編號、再比對)
 * public List<String> countNoXXZLData()  // 檢查有多少型體沒有部位資料
 * List<XXZL> getXSANA();    // 列出沒有部位的ARTICLE
 * Integer checkARTICLE(ARTICLE);   // 檢查底廠有沒有型體資料
 * List<XXZL> getXSANA();    // 列出沒有部位的ARTICLE
 * List<String> getARTICLE();   // 取得ARTICLE
 * List<XXZLSERP> getXXZLSErp(String ARTICLE);    // 取得鞋廠BOM表
 * List<XXZLS> getXXZLS(String ARTICLE);    // 取得底廠部位資料
 * List<XXZLS1> getXXZLS1(String XS);    // 取得底廠部位Size & Gram資料
 * List<xxgjs> getxxgjs(String XieXing);    // 取得鞋廠降碼對照表
 */

public interface XXZLDao {

    // 取得鞋廠型体资料(XieXing, SheHao, ARTICLE)(*注意、是已放行的订单*)
    List<XXZL> getErpXSA();

    // 取得型体资料(XieXing, SheHao, ARTICLE)
    List<XXZL> getXSA();

    // 写入型体资料
    String XXZLInsert(XXZL xxzl);

    // 写入 Size 资料
    String XXZLS1Insert(XXZLS1 xxzls1);

    // 写入重量资料
    void XXZLS1Update(String sql);

    // 写入部位 & 配方资料
    void XXZLSInsert(XXZLS xxzls);

    // 刪除部位 & 配方资料
    void XXZLSDelete(String XS);

    // 取得鞋厂部位资料(BOM、部位編號)(先取得全部資料、再比對)
    List<String> getErpBWBH();

    // 檢查有多少型體沒有部位資料
    String countNoXXZLData();

    // 列出沒有部位的ARTICLE
    List<XXZL> getXSANA();

    // 檢查底廠有沒有型體資料
    List<Integer> checkARTICLE(String ARTICLE);

    // 取得ARTICLE
    List<String> getARTICLE();

    // 取得鞋廠BOM表
    List<XXZLSERP> getXXZLSErp(String ARTICLE);

    // 取得底廠部位資料
    List<XXZLS> getXXZLS(String ARTICLE);

    // 取得底廠部位Size & Gram資料
    List<XXZLS1> getXXZLS1(String XS);

    // 取得鞋廠降碼對照表
    List<xxgjs> getxxgjs(String XieXing);
}
