package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.XXZLDao;
import com.cxy.weberpby.dao.bwBuildDao;
import com.cxy.weberpby.model.*;
import com.cxy.weberpby.service.XXZLService;
import com.cxy.weberpby.service.timeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

@Component
public class XXZLServiceImpl implements XXZLService {

    @Autowired
    private XXZLDao xxzlDao;

    @Autowired
    private bwBuildDao bwBuildDao;

    @Autowired
    private timeService timeservice;

    // 檢查有多少型體沒有部位資料
    @Override
    public String countNoXXZLData() {
        return "还有" + xxzlDao.countNoXXZLData() + "筆型体没有部位资料";
    }

    // 列出沒有部位的ARTICLE
    @Override
    public List<XXZL> getXSANA() {
        return xxzlDao.getXSANA();
    }

    // 取得ARTICLE
    @Override
    public List<String> getARTICLE() {
        return xxzlDao.getARTICLE();
    }

    // 取得鞋廠BOM表
    @Override
    public List<XXZLSERP> getXXZLSErp(String ARTICLE) {
        // 先檢查底廠有沒有型體資料
        List<Integer> count = xxzlDao.checkARTICLE(ARTICLE);
        List<XXZLSERP> bomERP;
        if (count.get(0) == 0) {
            return xxzlDao.getXXZLSErp(ARTICLE);
        } else {
            // 取得部位資料
            List<XXZLS> getXXZLS = xxzlDao.getXXZLS(ARTICLE);
            // COB18SUC4401,02,B7U,A,RC025,97A802*001前圍條,SUPER,20220518,00,,,
            // 97A802*001,97,A802,BY-NL4434,CSLB-223,50mm,2.6mm,冬白
            // 把資料換成網頁要呈現的
            for (XXZLS value : getXXZLS) {
                // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
                String xbbw = value.getYSSM().substring(4, 6);
                String xblb = value.getYSSM().substring(6, 10);
                value.setXieXing(value.getYSSM().substring(4, 6));
                value.setXieXing(value.getYSSM().substring(6, 10) + "," + value.getYSSM().substring(14));
                value.setCQDH(value.getYSSM().substring(11, 14));
                List<bwBuild> getXB = bwBuildDao.getAllXB(xbbw, xblb);
                for (bwBuild value2 : getXB) {
                    if (value2.getNO().equals(value.getYSSM().substring(4, 14))) {
                        value.setXh(value2.getYSSM());
                        value.setCldh(value2.getMjbh());
                        value.setUSERID(value2.getHw());
                        value.setUSERDATE(value2.getWidth());
                        value.setLB(value2.getThickness());
                    }
                }
            }

            // Send Data //JSON 只能傳遞一組數列,所以用Map(List)把多個數列組合起來一起傳
            List SendData = new ArrayList();
            SendData.add(0, getXXZLS);
            SendData.add(1, xxzlDao.getXXZLSErp(ARTICLE));

            return SendData;
        }
    }

    // 取得型体资料(XieXing, SheHao, ARTICLE)
    @Override
    public List<XXZL> getXSA() {
        return xxzlDao.getXSA();
    }

    // Insert XXZLS
    @Override
    public void insertXXZLS(List<XXZLS> insertXXZLS) {
        // (char)(97)=英文A // 转大写
        int countABC = 0; // 英文要用的
        int countG = 1;

        XXZLS insertXXZLSData = new XXZLS();
        for (XXZLS value : insertXXZLS) {
            String getCldh = "";
            // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
            String xbNo = value.getYSSM().substring(4, 14);

            // Start-先寫入XXZLS1、如果大於33筆、代表有1個以上的配方需注意
            // 如果沒有XBB對應的資料、則用'00Z000*000'當假資料補入(前端補入)
            List<bwBuild2> getXBB = bwBuildDao.getAllXBB(xbNo);
            int tempCount = 0;
            int over33 = 0; // 判斷超過33代表有1個以上的配方
            for (bwBuild2 value2 : getXBB) {
                over33 += 1;
                String sql;
                getCldh = value2.getCldh();
                switch (countG) {
                    case 1:
                        sql = "UPDATE XXZLS1 SET G01 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 2:
                        sql = "UPDATE XXZLS1 SET G02 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 3:
                        sql = "UPDATE XXZLS1 SET G03 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 4:
                        sql = "UPDATE XXZLS1 SET G04 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 5:
                        sql = "UPDATE XXZLS1 SET G05 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 6:
                        sql = "UPDATE XXZLS1 SET G06 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 7:
                        sql = "UPDATE XXZLS1 SET G07 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 8:
                        sql = "UPDATE XXZLS1 SET G08 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 9:
                        sql = "UPDATE XXZLS1 SET G09 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 10:
                        sql = "UPDATE XXZLS1 SET G10 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 11:
                        sql = "UPDATE XXZLS1 SET G11 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 12:
                        sql = "UPDATE XXZLS1 SET G12 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 13:
                        sql = "UPDATE XXZLS1 SET G13 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 14:
                        sql = "UPDATE XXZLS1 SET G14 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 15:
                        sql = "UPDATE XXZLS1 SET G15 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                }
                if ((over33 / 33) > tempCount) {
                    tempCount += 1;
                    countG += 1;
                }
            }
            // End-先寫入XXZLS1、如果大於33筆、代表有1個以上的配方需注意

            insertXXZLSData.setXieXing(value.getXieXing());
            insertXXZLSData.setSheHao(value.getSheHao());
            insertXXZLSData.setCQDH("B7U");
            insertXXZLSData.setXh(String.valueOf(Character.toUpperCase((char) (97 + countABC))));
            insertXXZLSData.setCldh("V" + getCldh);
            insertXXZLSData.setYSSM(value.getYSSM());
            insertXXZLSData.setUSERID("SUPER");
            insertXXZLSData.setUSERDATE(timeservice.now());
            insertXXZLSData.setLB("00");

            xxzlDao.XXZLSInsert(insertXXZLSData);
            countABC += 1;

        }
    }

    // Update XXZLS
    @Override
    public void updateXXZLS(List<XXZLS> updateXXZLS) {
        // (char)(97)=英文A // 转大写
        int countABC = 0; // 英文要用的
        int countG = 1;

        // 先刪再新增、唉～
        String XS = updateXXZLS.get(0).getXieXing() + updateXXZLS.get(0).getSheHao();
        xxzlDao.XXZLSDelete(XS);

        XXZLS updateXXZLSData = new XXZLS();
        for (XXZLS value : updateXXZLS) {
            String getCldh = "";
            // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
            String xbNo = value.getYSSM().substring(4, 14);

            // Start-先寫入XXZLS1、如果大於33筆、代表有1個以上的配方需注意
            // 如果沒有XBB對應的資料、則用'00Z000*000'當假資料補入(前端補入)
            List<bwBuild2> getXBB = bwBuildDao.getAllXBB(xbNo);
            int tempCount = 0;
            int over33 = 0; // 判斷超過33代表有1個以上的配方
            for (bwBuild2 value2 : getXBB) {
                over33 += 1;
                String sql;
                getCldh = value2.getCldh();
                switch (countG) {
                    case 1:
                        sql = "UPDATE XXZLS1 SET G01 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 2:
                        sql = "UPDATE XXZLS1 SET G02 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 3:
                        sql = "UPDATE XXZLS1 SET G03 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 4:
                        sql = "UPDATE XXZLS1 SET G04 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 5:
                        sql = "UPDATE XXZLS1 SET G05 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 6:
                        sql = "UPDATE XXZLS1 SET G06 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 7:
                        sql = "UPDATE XXZLS1 SET G07 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 8:
                        sql = "UPDATE XXZLS1 SET G08 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 9:
                        sql = "UPDATE XXZLS1 SET G09 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 10:
                        sql = "UPDATE XXZLS1 SET G10 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 11:
                        sql = "UPDATE XXZLS1 SET G11 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 12:
                        sql = "UPDATE XXZLS1 SET G12 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 13:
                        sql = "UPDATE XXZLS1 SET G13 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 14:
                        sql = "UPDATE XXZLS1 SET G14 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                    case 15:
                        sql = "UPDATE XXZLS1 SET G15 = '" + value2.getG01() +
                                "' WHERE XieXing = '" + value.getXieXing() + "' AND SheHao = '" + value.getSheHao() + "' AND CC = '" + value2.getCC() + "'";
                        xxzlDao.XXZLS1Update(sql);
                        break;
                }
                if ((over33 / 33) > tempCount) {
                    tempCount += 1;
                    countG += 1;
                }
            }
            // End-先寫入XXZLS1、如果大於33筆、代表有1個以上的配方需注意

            updateXXZLSData.setXieXing(value.getXieXing());
            updateXXZLSData.setSheHao(value.getSheHao());
            updateXXZLSData.setCQDH("B7U");
            updateXXZLSData.setXh(String.valueOf(Character.toUpperCase((char) (97 + countABC))));
            updateXXZLSData.setCldh("V" + getCldh);
            updateXXZLSData.setYSSM(value.getYSSM());
            updateXXZLSData.setUSERID("SUPER");
            updateXXZLSData.setUSERDATE(timeservice.now());
            updateXXZLSData.setLB("00");

            xxzlDao.XXZLSInsert(updateXXZLSData);
            countABC += 1;

        }
    }
}
