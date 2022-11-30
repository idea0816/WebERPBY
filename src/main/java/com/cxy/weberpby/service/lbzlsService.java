package com.cxy.weberpby.service;

import com.cxy.weberpby.dto.lbzlUpdateParams;
import com.cxy.weberpby.model.lbzl;
import com.cxy.weberpby.model.lbzls;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/20
 * @Description 代号类别明细资料 - 上一层是 lbzl(共用)
 * <p>
 * List<lbzls> get2BW(); // 取得鞋廠部位(編號、名稱)
 * List<lbzls> get2BWbw(String lb); // 取得底廠部位(編號、名稱)
 * List<lbzl> getlbzlList();   // 取得類別資料
 * List<lbzls> getlbzlsList();  // 取得类别明细资料
 * void updatelbzl(lbzlUpdateParams lup);    // Update lbzl & lbzls
 * void insertlbzl(lbzlUpdateParams lup);    // Insert lbzl & lbzls
 * void deletelbzl(String lb);  // Delete lbzl & lbzls
 */
public interface lbzlsService {

    // 取得鞋廠部位(編號、名稱)
    List<lbzls> get2BW();

    // 取得底廠部位(編號、名稱)
    List<lbzls> get2BWbw(String lb);

    // 取得類別資料
    List<lbzl> getlbzlList();

    // 取得类别明细资料
    List<lbzls> getlbzlsList(String lb);

    // Update lbzl & lbzls
    void updatelbzl(lbzlUpdateParams lup);

    // Insert lbzl & lbzls
    void insertlbzl(lbzlUpdateParams lup);

    // Delete lbzl & lbzls
    void deletelbzl(String lb);

}

