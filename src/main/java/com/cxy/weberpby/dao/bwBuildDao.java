package com.cxy.weberpby.dao;

import com.cxy.weberpby.model.bwBuild;
import com.cxy.weberpby.model.bwBuild2;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/6
 * @Description 部位规格建立
 *
 * List<bwBuild> getAllXB(String bwlb, String lbdh); // 取得有同部位所有資料(XXZLS1_B)
 * List<bwBuild2> getAllXBB(String NO); // 取得配方&重量資料(XXZLS1_BB)
 * List<bwBuild> getBMC(String NO); // 部位B模具M顏色C對照
 * List<bwBuild2> getP(String NO); // 配方P
 * List<bwBuild2> getSG(String NO, String cldh, String YSSM); // Size,Gram
 * List<bwBuild> getList(String NO);    // 標準配方組成明細
 * void insertXXZLS1_B(List<bwBuild> insertBwBuild); //insertXXZLS1_B
 * void insertXXZLS1_BB(List<bwBuild2> insertXXZLS1_BB); //insertXXZLS1_BB
 * void deleteXXZLS1_B(String NO); //deleteXXZLS1_B
 * void deleteXXZLS1_BB(bwBuild2 deleteXXZLS1_BB);  // Delete XXZLS1_BB
 * void updateXXZLS1_B(bwBuild updateBwBuild); // update XXZLS1_B
 * List<String> getVersion(String NO);    // 標準部位項次號
 */

@Component
public interface bwBuildDao {

    // 取得有同部位所有資料
    List<bwBuild> getAllXB(String bwlb, String lbdh);

    // 取得配方&重量資料(XXZLS1_BB)
    List<bwBuild2> getAllXBB(String NO);

    // 部位B模具M顏色C對照
    List<bwBuild> getBMC(String NO);

    // 配方P
    List<bwBuild2> getP(String NO);

    // Size,Gram
    List<bwBuild2> getSG(String NO, String cldh, String YSSM);

    // 標準配方組成明細
    List<bwBuild> getList(String NO);

    //insertXXZLS1_B
    void insertXXZLS1_B(bwBuild insertBwBuild);

    //insertXXZLS1_BB
    void insertXXZLS1_BB(bwBuild2 insertXXZLS1_BB);

    //deleteXXZLS1_B
    void deleteXXZLS1_B(String NO);

    // Delete XXZLS1_BB
    void deleteXXZLS1_BB(bwBuild2 deleteXXZLS1_BB);

    // update XXZLS1_B
    void updateXXZLS1_B(bwBuild updateBwBuild);

    // 標準部位項次號
    List<String> getVersion(String NO);
}
