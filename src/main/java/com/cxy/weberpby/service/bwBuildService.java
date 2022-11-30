package com.cxy.weberpby.service;

import com.cxy.weberpby.model.bwBuild;
import com.cxy.weberpby.model.bwBuild2;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/6
 * @Description 部位规格建立
 * <p>
 * List<bwBuild> getAllXB(String lbdh); // 取得有同部位所有資料(XXZLS1_B)
 * List<bwBuild> getBMC(String NO); // 部位B模具M顏色C對照
 * List<bwBuild2> getP(String NO); // 配方P
 * List<bwBuild2> getSG(String NO, String cldh, String YSSM); // Size,Gram
 * void insertXXZLS1_B(List<bwBuild> insertBwBuild); // insertXXZLS1_B
 * void updateXXZLS1_B(List<bwbuild> updateBwBuild); // updateXXZLS1_B
 * void deleteXXZLS1_B(String delNO);  // Delete XXZLS1_B
 * void insertXXZLS1_BB(List<bwBuild2> insertXXZLS1_BB); //insertXXZLS1_BB
 * void deleteXXZLS1_BB(List<bwBuild2> deleteXXZLS1_BB);  // Delete XXZLS1_BB
 * String getVersion(String NO);    // 標準部位項次號
 */

public interface bwBuildService {

    // 取得有同部位所有資料(XXZLS1_B)
    List<bwBuild> getAllXB(String bwlb, String lbdh);

    // 部位B模具M顏色C對照
    List<bwBuild> getBMC(String NO);

    // 配方P
    List<bwBuild2> getP(String NO);

    // Size,Gram
    List<bwBuild2> getSG(String NO, String cldh, String YSSM);

    // 標準部位項次號
    String getVersion(String NO);

    //insertXXZLS1_B
    void insertXXZLS1_B(List<bwBuild> insertBwBuild);

    // updateXXZLS1_B
    void updateXXZLS1_B(List<bwBuild> updateBwBuild);

    // Delete XXZLS1_B
    void deleteXXZLS1_B(String delNO);

    //insertXXZLS1_BB
    void insertXXZLS1_BB(List<bwBuild2> insertXXZLS1_BB);

    // Delete XXZLS1_BB
    void deleteXXZLS1_BB(List<bwBuild2> deleteXXZLS1_BB);

    // 標準配方組成明細
    List<bwBuild> getList(String NO);
}
