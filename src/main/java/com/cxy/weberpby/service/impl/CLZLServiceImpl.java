package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.CLZLDao;
import com.cxy.weberpby.dao.clzlslDao;
import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.dto.CLZLUpdateParams;
import com.cxy.weberpby.model.CLZL;
import com.cxy.weberpby.model.clzlsl;
import com.cxy.weberpby.service.CLZLService;
import com.cxy.weberpby.service.timeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 材料基本資料讀取
 * <p>
 * String updateDdCLZLa();  // 自动导入材料基本资料
 * List<CLZL> getCLZL(CLZLQueryParams clzlQueryParams); // Get CLZL
 * void updateCLZL(String cllb, CLZLUpdateParams cup);   // Update CLZL(配方有版次問題、所以多傳一個版次資料)// 這裡因為有需要 clzlsz & clzlsl 所以寫在一起、未來考慮分割(*)
 * void insertCLZL(String cllb, CLZLUpdateParams cup);   // Insert CLZL
 * void deleteCLZL(String cldh);    // Delete CLZL(配方有版次問題、所以多傳一個版次資料)
 * String getVersion(String cldh)  // 取得配方最新版次
 */

@Component
public class CLZLServiceImpl implements CLZLService {

    @Autowired
    private CLZLDao clzlDao;

    @Autowired
    private clzlslDao clzlslDao;

    @Autowired
    private timeService timeservice;

    // 自动导入材料基本资料
    @Override
    public String updateDdCLZLa() {
        // 取得小于本月的底厂材料基本资料
        List<CLZL> oldDdCLZLa = clzlDao.getOldDdCLZLa(timeservice.nowYM() + "%");   // 记得加%
        return "共导入" + oldDdCLZLa.size() + "筆材料基本資料";
    }

    // Get CLZL
    @Override
    public List<CLZL> getCLZL(CLZLQueryParams clzlQueryParams) {
        // For Version Control
        if (clzlQueryParams.getLb().equals("R") || clzlQueryParams.getLb().equals("V")) {
            List<CLZL> forVersion = clzlDao.getCLZL(clzlQueryParams);
            for (CLZL value : forVersion) {
                // 版本、不寫入資料庫、只在Service替換
                if (!value.getCldh().contains("*")) {
                    value.setCGLB("000");
                } else {
                    int getStar = value.getCldh().indexOf("*");
                    String cldh = value.getCldh().substring(0, getStar);
                    String version = value.getCldh().substring(getStar + 1, getStar + 4);
                    value.setCldh(cldh);
                    value.setCGLB(version);
                }

                // cldj四捨五入到小數點後2位
                Double cldjRound = Double.valueOf(Math.round(value.getCldj() * 100) / 100.0);
                value.setCldj(cldjRound);
            }
            return forVersion;
        } else {
            return clzlDao.getCLZL(clzlQueryParams);
        }
    }

    // Update CLZL // 這裡因為有需要 clzlsz & clzlsl 所以寫在一起、未來考慮分割(*)
    @Override
    public void updateCLZL(String cllb, CLZLUpdateParams cup) {
        /* 2015-Sep-05
			詢問吳文峰:
			PHR算法是--W(膠類)總合重量為100%,其他的再除以W的總和
			ex:
				BR-150 + SBR-1502 + 3L = 38kg(W總重)
				ZQ-356 用量 20kg, 配方量 = 20/38*100% = 52.632%
				所以配方量總合%一定會超過100%
			USD單價 = 總金額/總配方kg
			一手重量 = 扣掉Z的總配方重量*/
        // 進到這裡、認定都會改clzlsl & clzlsz
        // 如果配方代號改新、就直接新增後刪掉舊配方(含配方明細)
        // 依資料庫需求、寫入順序 clzlsz->clzlsl->CLZL
        // 總價格；W總重；一手重量；配方總重
        double sumcldj = 0, sumWclyl = 0, oneHand = 0, sumclyl = 0;
        for (clzlsl value : cup.getNewclzlsl()) {
            // 先查原物料價格
            CLZLQueryParams clzlQueryParams = new CLZLQueryParams();
            clzlQueryParams.setCldh(value.getCldhz());
            List<CLZL> temp = clzlDao.getCLZL(clzlQueryParams);
            // 查到的價格寫回
            value.setCldj(temp.get(0).getCldj());
            // 總價格(sumcldj) = sum(clyl * cldj)
            sumcldj += (value.getClyl() * value.getCldj());
            // W總重
            if (value.getLb().equals("W")) {
                sumWclyl += value.getClyl();
            }
            // 一手重量(扣除掉Z)
            if (!(value.getLb().equals("Z"))) {
                oneHand += value.getClyl();
            }
            // 配方總重量
            sumclyl += value.getClyl();
        }

        // 判斷配方代號是*000的是第一版(刪掉版號)
        String realcldh;
        int getStar = cup.getNewCLZL().get(0).getCldh().indexOf("*");
        if (Objects.equals(cup.getNewCLZL().get(0).getCldh().substring(getStar + 1, getStar + 4), "000")) {
            realcldh = cup.getNewCLZL().get(0).getCldh().substring(0, getStar);
        } else {
            realcldh = cup.getNewCLZL().get(0).getCldh();
        }
        // Delete clzlsz
        clzlslDao.deleteclzlsz(realcldh);

        // 判斷配方是V或R、若為V、則多寫入clzlsl
        if (cllb.equals("V")) {
            // Insert clzlsl
            System.out.println("V");
        }
        // Insert clzlsz
        for (clzlsl value : cup.getNewclzlsl()) {
            clzlsl insertclzlsz = new clzlsl();
            insertclzlsz.setCldh(realcldh);
            insertclzlsz.setLb(value.getLb());
            insertclzlsz.setCldhz(value.getCldhz());
            insertclzlsz.setClyl(value.getClyl());
            insertclzlsz.setPhr((value.getClyl() / sumWclyl) * 100);
            // 四捨五入到小數點後2位
            // Double insCldj = Double.valueOf(Math.round((value.getCldj() * 100)/100.0 ));
            insertclzlsz.setCldj(value.getCldj());
            insertclzlsz.setUSERID("SUPER");
            insertclzlsz.setUSERDATE(timeservice.now());

            clzlslDao.insertclzlsz(insertclzlsz);
        }
        // 判斷新舊 CLZL
        if (cup.getNewCLZL().get(0).getCldh().equals(cup.getOldCLZL().get(0).getCldh())) {
            // 檢查新舊 CLZL 內容
            if (cup.getNewCLZL().get(0).getZwpm().equals(cup.getOldCLZL().get(0).getZwpm()) && cup.getNewCLZL().get(0).getCgcqdh().equals(cup.getOldCLZL().get(0).getCgcqdh())) {
                //do nothing
            } else {
                // 改變配方名稱 & 配方類別
                CLZL updateCLZL = new CLZL();
                updateCLZL.setCldh(realcldh);
                updateCLZL.setCllb(cllb);
                updateCLZL.setZwpm(cup.getNewCLZL().get(0).getZwpm());
                updateCLZL.setDwbh("KGS");
                updateCLZL.setCldj(sumcldj / sumclyl);
                updateCLZL.setTotKgs(oneHand);
                updateCLZL.setUSERID("SUPER");
                updateCLZL.setUSERDATE(timeservice.now());
                updateCLZL.setCgcqdh(cup.getNewCLZL().get(0).getCgcqdh());

                clzlDao.updateCLZL(updateCLZL);
            }
        } else {
            // 還沒做的：歷史單價、配方歷史記錄、物性(含歷史記錄)

            // Insert CLZL
            CLZL insertCLZL = new CLZL();
            insertCLZL.setCldh(realcldh);
            insertCLZL.setCllb(cllb);
            insertCLZL.setZwpm(cup.getNewCLZL().get(0).getZwpm());
            insertCLZL.setDwbh("KGS");
            insertCLZL.setCldj(sumcldj / sumclyl);
            insertCLZL.setTotKgs(oneHand);
            insertCLZL.setUSERID("SUPER");
            insertCLZL.setUSERDATE(timeservice.now());
            insertCLZL.setCgcqdh(cup.getNewCLZL().get(0).getCgcqdh());

            clzlDao.insertCLZL(insertCLZL);

            // Delete CLZL
            clzlDao.deleteCLZL(cup.getOldCLZL().get(0).getCldh());
        }
    }

    // Insert CLZL // 這裡因為有需要 clzlsz & clzlsl 所以寫在一起、未來考慮分割(*)
    @Override
    public void insertCLZL(String cllb, CLZLUpdateParams cup) {
        // 依資料庫需求、寫入順序 clzlsz->clzlsl->CLZL
        // 總價格；W總重；一手重量；配方總重
        double sumcldj = 0, sumWclyl = 0, oneHand = 0, sumclyl = 0;
        for (clzlsl value : cup.getNewclzlsl()) {
            // 先查原物料價格
            CLZLQueryParams clzlQueryParams = new CLZLQueryParams();
            clzlQueryParams.setCldh(value.getCldhz());
            List<CLZL> temp = clzlDao.getCLZL(clzlQueryParams);
            // 查到的價格寫回
            value.setCldj(temp.get(0).getCldj());
            // 總價格(sumcldj) = sum(clyl * cldj)
            sumcldj += (value.getClyl() * value.getCldj());
            // W總重
            if (value.getLb().equals("W")) {
                sumWclyl += value.getClyl();
            }
            // 一手重量(扣除掉Z)
            if (!(value.getLb().equals("Z"))) {
                oneHand += value.getClyl();
            }
            // 配方總重量
            sumclyl += value.getClyl();
        }
        // 判斷配方代號是*000的是第一版(刪掉版號)
        String realcldh;
        int getStar = cup.getNewCLZL().get(0).getCldh().indexOf("*");
        if (Objects.equals(cup.getNewCLZL().get(0).getCldh().substring(getStar + 1, getStar + 4), "000")) {
            realcldh = cup.getNewCLZL().get(0).getCldh().substring(0, getStar);
        } else {
            realcldh = cup.getNewCLZL().get(0).getCldh();
        }
        // 判斷配方是V或R、若為V、則多寫入clzlsl
        if (cllb.equals("V")) {
            // Insert clzlsl
            System.out.println("V");
        }
        // Insert clzlsz
        for (clzlsl value : cup.getNewclzlsl()) {
            clzlsl insertclzlsz = new clzlsl();
            insertclzlsz.setCldh(realcldh);
            insertclzlsz.setLb(value.getLb());
            insertclzlsz.setCldhz(value.getCldhz());
            insertclzlsz.setClyl(value.getClyl());
            insertclzlsz.setPhr((value.getClyl() / sumWclyl) * 100);
            insertclzlsz.setCldj(value.getCldj());
            insertclzlsz.setUSERID("SUPER");
            insertclzlsz.setUSERDATE(timeservice.now());

            clzlslDao.insertclzlsz(insertclzlsz);
        }
        // Insert CLZL
        CLZL insertCLZL = new CLZL();
        insertCLZL.setCldh(realcldh);
        insertCLZL.setCllb(cllb);
        insertCLZL.setZwpm(cup.getNewCLZL().get(0).getZwpm());
        insertCLZL.setDwbh("KGS");
        insertCLZL.setCldj(sumcldj / sumclyl);
        insertCLZL.setTotKgs(oneHand);
        insertCLZL.setUSERID("SUPER");
        insertCLZL.setUSERDATE(timeservice.now());
        insertCLZL.setCgcqdh(cup.getNewCLZL().get(0).getCgcqdh());

        clzlDao.insertCLZL(insertCLZL);

    }

    // Delete CLZL(配方有版次問題、所以多傳一個版次資料)
    @Override
    public void deleteCLZL(String cldh) {
        // 判斷是否為配方代號(帶*)
        if (cldh.contains("*")) {
            // 判斷配方代號是*000的是第一版(刪掉版號)
            int getStar = cldh.indexOf("*");
            // if ((cldh.substring(getStar + 1, getStar + 4)).equals("000")) {
            if (Objects.equals(cldh.substring(getStar + 1, getStar + 4), "000")) {
                clzlDao.deleteCLZL(cldh.substring(0, getStar));
            } else {
                clzlDao.deleteCLZL(cldh);
            }
        }
    }

    // 取得配方最新版次
    @Override
    public String getVersion(String cldh) {
        int getStar = cldh.indexOf("*"), newVersion = 0;
        // 版本為000要多加一個判斷
        if((cldh.substring(getStar+1, getStar+4)).equals("000")){
            newVersion = 1;
        } else {
            List<String> oldcldh = clzlDao.getVersion(cldh.substring(0, getStar) + "%");
            String oldversion = oldcldh.get(0).substring(getStar + 1, getStar + 4);
            newVersion = Integer.parseInt(oldversion) + 1;
        }
        return String.format("%03d", newVersion);
    }
}
