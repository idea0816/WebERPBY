package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.FSIZEDao;
import com.cxy.weberpby.dao.bwBuildDao;
import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.model.bwBuild;
import com.cxy.weberpby.model.bwBuild2;
import com.cxy.weberpby.service.bwBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/6
 * @Description 部位规格建立
 * <p>
 * List<bwBuild> getAllXB(String bwlb, String lbdh); // 取得有同部位所有資料(XXZLS1_B)
 * List<bwBuild2> getAllXBB(String NO); // 取得配方&重量資料(XXZLS1_BB)
 * List<bwBuild> getBMC(String NO); // 部位B模具M顏色C對照
 * List<bwBuild2> getP(String NO); // 配方P
 * List<bwBuild2> getSG(String NO, String cldh); // Size,Gram
 * List<bwBuild> getList(String NO);    // 標準配方組成明細
 * void insertXXZLS1_B(List<bwBuild> insertBwBuild); //insertXXZLS1_B
 * void updateXXZLS1_B(List<bwbuild> updateBwBuild); // updateXXZLS1_B
 * void deleteXXZLS1_B(String delNO);  // Delete XXZLS1_B
 * void insertXXZLS1_BB(List<bwBuild2> insertXXZLS1_BB); //insertXXZLS1_BB
 * void deleteXXZLS1_BB(List<bwBuild2> deleteXXZLS1_BB);  // Delete XXZLS1_BB
 * String getVersion(String NO);    // 標準部位項次號
 */

@Component
public class bwBuildServiceImpl implements bwBuildService {

    @Autowired
    private bwBuildDao bwBuildDao;

    @Autowired
    private FSIZEDao fsizeDao;

    // 取得有同部位所有資料(XXZLS1_B)
    @Override
    public List<bwBuild> getAllXB(String bwlb, String lbdh) {
        List<bwBuild> getXB = bwBuildDao.getAllXB(bwlb, lbdh);
        for(bwBuild value: getXB){
            String temp = value.getNO();
            int getStar = temp.indexOf("*");
            String NO = temp.substring(getStar + 1, 10);
            value.setNO(NO);
        }
        return getXB;
    }

    // 部位B模具M顏色C對照
    @Override
    public List<bwBuild> getBMC(String NO) {
        List<bwBuild> getBMCData = bwBuildDao.getBMC(NO);
        for (bwBuild value : getBMCData) {
            int getStar = value.getNO().indexOf("*");
            String version = value.getNO().substring(getStar + 1, getStar + 4);
            value.setNO(version);
        }
        return getBMCData;
    }

    // 配方P
    @Override
    public List<bwBuild2> getP(String NO) {
        return bwBuildDao.getP(NO);
    }

    // Size,Gram
    @Override
    public List<bwBuild2> getSG(String NO, String cldh, String YSSM) {
        return bwBuildDao.getSG(NO, cldh, YSSM);
    }

    // 標準配方組成明細
    @Override
    public List<bwBuild> getList(String NO) {
        return bwBuildDao.getList(NO);
    }

    //insertXXZLS1_B
    @Override
    public void insertXXZLS1_B(List<bwBuild> insertBwBuild) {

        for (bwBuild value : insertBwBuild) {
            // 爛爛爛～要先刪除、還是全刪才能新增、一定要改掉
            bwBuildDao.deleteXXZLS1_B(value.getNO());
            bwBuild insertData = new bwBuild();
            String temp = value.getNO();
            int getStar = temp.indexOf("*");
            String bwlb = temp.substring(0, 2);
            String lbdh = temp.substring(2, getStar);
            // insert XXZLS1_B
            insertData.setNO(value.getNO());
            insertData.setBwlb(bwlb);
            insertData.setLbdh(lbdh);
            insertData.setMjbh(value.getMjbh());
            insertData.setHw(value.getHw());
            insertData.setWidth(value.getWidth());
            insertData.setThickness(value.getThickness());
            insertData.setYSSM(value.getYSSM());
            bwBuildDao.insertXXZLS1_B(insertData);
        }
    }

    // updateXXZLS1_B
    @Override
    public void updateXXZLS1_B(List<bwBuild> updateBwBuild) {
        bwBuild updateData = new bwBuild();
        for(bwBuild value: updateBwBuild){
            updateData.setNO(value.getNO());
            updateData.setBwlb(value.getBwlb());
            updateData.setLbdh(value.getLbdh());
            updateData.setMjbh(value.getMjbh());
            updateData.setHw(value.getHw());
            updateData.setWidth(value.getWidth());
            updateData.setThickness(value.getThickness());
            updateData.setYSSM(value.getYSSM());

            bwBuildDao.updateXXZLS1_B(updateData);
        }
    }

    // Delete XXZLS1_B
    @Override
    public void deleteXXZLS1_B(String delNO) {
        bwBuildDao.deleteXXZLS1_B(delNO);
        bwBuild2 delBB = new bwBuild2();
        delBB.setNO(delNO);
        bwBuildDao.deleteXXZLS1_BB(delBB);
    }

    //insertXXZLS1_BB
    @Override
    public void insertXXZLS1_BB(List<bwBuild2> insertXXZLS1_BB) {
        List getsize = fsizeDao.getUsSize();
        bwBuild2 insertXXZLS1BB = new bwBuild2();
        int count = 0;
        for (bwBuild2 value : insertXXZLS1_BB) {
            insertXXZLS1BB.setNO(value.getNO());
            insertXXZLS1BB.setYSSM(value.getYSSM());
            insertXXZLS1BB.setCldh(value.getCldh());
            insertXXZLS1BB.setCC((String) getsize.get(count));
            insertXXZLS1BB.setG01(value.getG01());
            insertXXZLS1BB.setG02(value.getG02());
            bwBuildDao.insertXXZLS1_BB(insertXXZLS1BB);
            count += 1;
        }
    }

    // Delete XXZLS1_BB
    @Override
    public void deleteXXZLS1_BB(List<bwBuild2> deleteXXZLS1_BB) {
        bwBuild2 deleteXXZLS1BB = new bwBuild2();
        for(bwBuild2 value: deleteXXZLS1_BB){
            deleteXXZLS1BB.setNO(value.getNO());
            deleteXXZLS1BB.setYSSM(value.getYSSM());
            deleteXXZLS1BB.setCldh(value.getCldh());
            bwBuildDao.deleteXXZLS1_BB(deleteXXZLS1BB);
        }
    }

    // 標準部位項次號
    @Override
    public String getVersion(String NO) {
        List<String> getVersion = bwBuildDao.getVersion(NO);
        int newVersion;
        if (getVersion.size() == 0) {
            newVersion = 1;
        } else {
            int getStar = getVersion.get(0).indexOf("*");
            String oldversion = getVersion.get(0).substring(getStar + 1, getStar + 4);
            newVersion = Integer.parseInt(oldversion) + 1;
        }

        return String.format("%03d", newVersion);
    }
}
