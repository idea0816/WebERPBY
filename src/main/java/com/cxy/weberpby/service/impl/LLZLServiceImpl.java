package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.LLZLDao;
import com.cxy.weberpby.dao.PGZLDao;
import com.cxy.weberpby.model.LLZL;
import com.cxy.weberpby.model.LLZLS;
import com.cxy.weberpby.model.LLZLS_temp;
import com.cxy.weberpby.model.PGZLS1;
import com.cxy.weberpby.service.LLZLService;
import com.cxy.weberpby.service.timeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/6/2
 * @Description 申領資料(LLZL & LLZLS)
 * <p>
 * String getVersion()  // 取得領料最新單號
 * List< insertLLZL(String LLBH, String PGDATE);    // Insert LLZL  & LLZLS(新增領料單以派工單為基礎、在派工單中已算好料直接傳過來即可、同時若已有領料單則回傳)
 * List<LLZLS> insertLLZLdc(String LLBH, List pgdateDC);
 * List<LLZL> getLLZL(String CFM);   // getLLZL(取得領料單表頭)
 * List<LLZLS> getLLZLS(String LLBH);   // getLLZLS
 */

@Component
public class LLZLServiceImpl implements LLZLService {

    @Autowired
    private LLZLDao llzlDao;

    @Autowired
    private PGZLDao pgzlDao;
    @Autowired
    private timeService timeService;

    // 取得領料單最新單號
    @Override
    public String getVersion() {
        // E220600001
        String LLBH = "E" + timeService.nowYM().substring(2) + "%";
        List<String> getVersion = llzlDao.getVersion(LLBH);
        if (getVersion.size() == 0) {
            return "E" + timeService.nowYM().substring(2) + "00001";
        } else {
            String oldversion = getVersion.get(0).substring(5);
            int newVersion = Integer.parseInt(oldversion) + 1;
            return "E" + timeService.nowYM().substring(2) + String.format("%05d", newVersion);
        }
    }

    // Insert LLZL  & LLZLS(新增領料單以派工單為基礎、在派工單中已算好料直接傳過來即可、同時若已有領料單則回傳)
    @Override
    public List<LLZLS> insertLLZL(String LLBH, String PGDATE) {
        // 檢查有沒有領料單、如果有、不再重複產生
        List<LLZLS> checkLLZL = llzlDao.checkLLZLS(PGDATE);
        if (checkLLZL == null) {
            // insert LLZL
            LLZL insertLLZL = new LLZL();
            insertLLZL.setLLBH(LLBH);
            insertLLZL.setCQDH("B7U");
            insertLLZL.setCQDH1("7VA"); // 鞋廠的出底廠材料倉代號
            insertLLZL.setCFM("N");
            insertLLZL.setUSERID("SUPER");
            insertLLZL.setUSERDATE(timeService.now());
            llzlDao.insertLLZL(insertLLZL);

            // insert LLZLS
            LLZLS insertLLZLS = new LLZLS();
            insertLLZLS.setLLBH(LLBH);
            insertLLZLS.setCQDH("B7U");
            insertLLZLS.setDDBH(PGDATE);
            insertLLZLS.setUSERID("SUPER");
            insertLLZLS.setUSERDATE(timeService.now());
            insertLLZLS.setLB("WML");
            List<PGZLS1> getPGZLS1 = pgzlDao.getPGZLS1(PGDATE);
            for (PGZLS1 value : getPGZLS1) {
                insertLLZLS.setCldh(value.getCldh());
                insertLLZLS.setKGS_LL(value.getClyl());
                llzlDao.insertLLZLS(insertLLZLS);
            }
            return llzlDao.getLLZLS(LLBH);
        }else{
            return checkLLZL;
        }
    }

    // for 大車領料單
    @Override
    public List<LLZLS> insertLLZLdc(String LLBH, List<String> pgdateDC) {
        // 檢查有沒有領料單、如果有、不再重複產生
        List<LLZLS> checkLLZL = llzlDao.checkLLZLS(LLBH);
        if (checkLLZL == null) {
            // insert LLZL
            LLZL insertLLZL = new LLZL();
            insertLLZL.setLLBH(LLBH);
            insertLLZL.setCQDH("B7U");
            insertLLZL.setCQDH1("7VA"); // 鞋廠的出底廠材料倉代號
            insertLLZL.setCFM("N");
            insertLLZL.setUSERID("SUPER");
            insertLLZL.setUSERDATE(timeService.now());
            llzlDao.insertLLZL(insertLLZL);

            // insert LLZLS
            LLZLS insertLLZLS = new LLZLS();
            insertLLZLS.setLLBH(LLBH);
            insertLLZLS.setCQDH("B7U");
            insertLLZLS.setDDBH(LLBH);
            insertLLZLS.setUSERID("SUPER");
            insertLLZLS.setUSERDATE(timeService.now());
            insertLLZLS.setLB("DC");

            // 檢查Temp檔有沒有資料、有的話刪掉、不然會亂掉
            List<PGZLS1> getPGZLS1_temp = pgzlDao.getPGZLS1_temp();
            if (getPGZLS1_temp != null) {
                // 刪掉暫存檔資料
                pgzlDao.deletePGZLS1_temp();
            }

            // 將PGZLS1的資料寫入暫存檔
            for(String value : pgdateDC){
                PGZLS1 insertPGZLS1_temp = new PGZLS1();
                List<PGZLS1> getPGZLS1 = pgzlDao.getPGZLS1(value);
                for(PGZLS1 pgzls1data: getPGZLS1){
                    insertPGZLS1_temp.setCldh(pgzls1data.getCldh());
                    insertPGZLS1_temp.setClyl(pgzls1data.getClyl());
                    pgzlDao.inserPGZLS1_temp(insertPGZLS1_temp);
                }
            }

            // 暫存檔、排除重複的料號、並寫入LLZLS
            List<PGZLS1> getPGZLS1_temp2 = pgzlDao.getPGZLS1_temp();
            for (PGZLS1 temp : getPGZLS1_temp2) {
                insertLLZLS.setCldh(temp.getCldh());
                insertLLZLS.setKGS_LL(temp.getClyl());
                llzlDao.insertLLZLS(insertLLZLS);
            }
            // 刪掉暫存檔資料
            pgzlDao.deletePGZLS1_temp();

            // 寫入 LLZLS_temp
            LLZLS_temp ltemp = new LLZLS_temp();
            ltemp.setLLBH(LLBH);
            for(int x = 0; x < pgdateDC.size(); x++){
                switch (x) {
                    case 0:
                        ltemp.setPGDATE1(pgdateDC.get(x));
                        break;
                    case 1:
                        ltemp.setPGDATE2(pgdateDC.get(x));
                        break;
                    case 2:
                        ltemp.setPGDATE3(pgdateDC.get(x));
                        break;
                    case 3:
                        ltemp.setPGDATE4(pgdateDC.get(x));
                        break;
                    case 4:
                        ltemp.setPGDATE5(pgdateDC.get(x));
                        break;

                }
            }
            llzlDao.insertLLZLS_temp(ltemp);

            return llzlDao.getLLZLS(LLBH);
        }else{
            return checkLLZL;
        }
    }

    // getLLZL(取得有效領料單)
    @Override
    public List<LLZL> getLLZL(String CFM) {
        return llzlDao.getLLZL(CFM);
    }

    // getLLZLS
    @Override
    public List<LLZLS> getLLZLS(String LLBH) {
        return llzlDao.getLLZLS(LLBH);
    }
}
