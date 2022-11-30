package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.DDZLDao;
import com.cxy.weberpby.dao.PGZLDao;
import com.cxy.weberpby.dao.XXZLDao;
import com.cxy.weberpby.dao.bwBuildDao;
import com.cxy.weberpby.dto.PGZLUpdateParams;
import com.cxy.weberpby.dto.llUpdateParams;
import com.cxy.weberpby.model.*;
import com.cxy.weberpby.service.LLZLService;
import com.cxy.weberpby.service.PGZLService;
import com.cxy.weberpby.service.clzlslService;
import com.cxy.weberpby.service.timeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description 派工資料
 * <p>
 * String getVersion(String dw)  // 取得派工最新單號
 * List<PGZL> getPGZL(String BZ);   // 取得派工表頭
 * List<PGZLS> getPGZLS(String PGDATE); // Get PGZLS
 * List<PGZLS1> getPGZLS1(String PGDATE); // Get PGZLS1
 * void setPGZLS1(String PGDATE);   // set PGZLS1 and 算料
 * List<PGZLS1> getPGZLS1dc(List pgdateDC);   // for PGZLS1dc 算料
 * List<PGZLS1> getPGZLS1dc(String PGDATE);   // Get PGZLS1dc
 * void insertPGZL(PGZLUpdateParams pup);   // Insert PGZL & PGZLS
 * void updatePGZLS1(llUpdateParams);   // Update pgzls1
 * <p>
 * List<SMDD> getSMDD(String DDBH); // 導入鞋廠輪次資料表頭
 * List<SMDDS> getSMDDS(String DDBH, String Round);   // 導入鞋廠輪次明細資料
 * List<String> getdcpgPrint(String DDBH, String PGDATE)    // 取得大車派工列印資料
 * List<BDepartment> getdepList();    // 取得鞋廠部門資料
 */

@Component
public class PGZLServiceImpl implements PGZLService {

    @Autowired
    private PGZLDao pgzlDao;

    @Autowired
    private clzlslService clzlslService;

    @Autowired
    private LLZLService llzlService;

    @Autowired
    private DDZLDao ddzlDao;

    @Autowired
    private XXZLDao xxzlDao;

    @Autowired
    private bwBuildDao bwBuildDao;

    @Autowired
    private timeService timeService;


    // 取得派工最新單號
    @Override
    public String getVersion(String dw) {
        String getPGDATE = "";
        if (dw.equals("D")) { // 大車派工單號
            getPGDATE = "D" + (timeService.now()).substring(2, 8) + "%";
            List<String> getVersion = pgzlDao.getVersion(getPGDATE);
            if (getVersion.size() == 0) {
                return "D" + (timeService.now()).substring(2, 8) + "001";
            } else {
                String oldversion = getVersion.get(0).substring(7, 10);
                int newVersion = Integer.parseInt(oldversion) + 1;
                return "D" + (timeService.now()).substring(2, 8) + String.format("%03d", newVersion);
            }
        } else if (dw.equals("R")) {    // 打粗派工單號
            getPGDATE = "R" + (timeService.now()).substring(2, 8) + "%";
            List<String> getVersion = pgzlDao.getVersion(getPGDATE);
            if (getVersion.size() == 0) {
                return "R" + (timeService.now()).substring(2, 8) + "001";
            } else {
                String oldversion = getVersion.get(0).substring(7, 10);
                int newVersion = Integer.parseInt(oldversion) + 1;
                return "R" + (timeService.now()).substring(2, 8) + String.format("%03d", newVersion);
            }
        } else {
            getPGDATE = timeService.now() + "%";
            List<String> getVersion = pgzlDao.getVersion(getPGDATE);
            if (getVersion.size() == 0) {
                return timeService.now() + "01";
            } else {
                String oldversion = getVersion.get(0).substring(8, 10);
                int newVersion = Integer.parseInt(oldversion) + 1;
                return timeService.now() + String.format("%02d", newVersion);
            }
        }
    }

    // 取得派工表頭
    @Override
    public List<PGZL> getPGZL(String BZ) {
        List<PGZL> getPGZL = pgzlDao.getPGZL(BZ);
        if (getPGZL == null) {
            return null;
        } else {
            for (PGZL value : getPGZL) {
                value.setDATE1(timeService.forWebDate(value.getDATE1()));
            }
            return getPGZL;
        }
    }

    // Get PGZLS
    @Override
    public List<PGZLS> getPGZLS(String PGDATE) {
        if (PGDATE.substring(0, 1).equals("D")) {
            return pgzlDao.getPGZLSdc(PGDATE);
        } else if (PGDATE.substring(0, 1).equals("R")) {
            return pgzlDao.getPGZLSdc(PGDATE);
        } else {
            return pgzlDao.getPGZLS(PGDATE);
        }
    }

    // set PGZLS1 and 算料
    @Override
    public void setPGZLS1(String PGDATE) {
        // 算料並寫入 PGZLS1
        List<PGZLS1> getPGZLS1 = pgzlDao.getPGZLS1(PGDATE);
        if (getPGZLS1 == null) {
            PGZLS1 insertPGZLS1 = new PGZLS1();
            PGZLS1 insertPGZLS1_temp = new PGZLS1();
            // 取得PGZLS資料
            List<PGZLS> getPGZLS = pgzlDao.getPGZLS(PGDATE);
            // Set<String> cldh = new HashSet<>();
            for (PGZLS value : getPGZLS) {
                insertPGZLS1.setPGDATE(value.getPGDATE());
                // 取得W.X.Y的數量
                List<clzlsl> getclzlsl = clzlslService.getclzlsl(value.getCldh());
                for (clzlsl value2 : getclzlsl) {
                    if (!(value2.getLb().equals("Z"))) {
                        insertPGZLS1_temp.setCldh(value2.getCldhz());
                        insertPGZLS1_temp.setClyl(value2.getClyl() * value.getPGSS());
                        pgzlDao.inserPGZLS1_temp(insertPGZLS1_temp);
                    }
                }
            }
            insertPGZLS1.setCQDH("B7U");
            insertPGZLS1.setUSERID("SUPER");
            insertPGZLS1.setUSERDATE(timeService.now());
            // 暫存檔、排除重複的料號
            List<PGZLS1> getPGZLS1_temp = pgzlDao.getPGZLS1_temp();
            for (PGZLS1 temp : getPGZLS1_temp) {
                insertPGZLS1.setCldh(temp.getCldh());
                insertPGZLS1.setClyl(temp.getClyl());
                pgzlDao.insertPGZLS1(insertPGZLS1);
            }
            // 刪掉暫存檔資料
            pgzlDao.deletePGZLS1_temp();
        }
    }

    // Get PGZLS1
    @Override
    public List<PGZLS1> getPGZLS1(String PGDATE) {
        List<PGZLS1> getPGZLS1 = pgzlDao.getPGZLS1(PGDATE);
        return pgzlDao.getPGZLS1(PGDATE);
    }

    // for PGZLS1dc 算料
    @Override
    public void setPGZLS1dc(String pgdateDC) {

        // 檢查Temp檔有沒有資料、有的話刪掉、不然會亂掉
        List<PGZLS1> getPGZLS1_temp = pgzlDao.getPGZLS1_temp();
        if (getPGZLS1_temp != null) {
            // 刪掉暫存檔資料
            pgzlDao.deletePGZLS1_temp();
        }

        PGZLS1 insertPGZLS1_temp = new PGZLS1();
        // 取得PGZLS資料
        List<PGZLS> getPGZLS = pgzlDao.getPGZLSdc(pgdateDC);
        for (PGZLS pgzlsvalue : getPGZLS) {
            String DDBH = pgzlsvalue.getCldh();
            String Round = pgzlsvalue.getCQDH();
            // 取得鞋廠輪次明細資料
            List<SMDDS> getSMDDS = ddzlDao.getSMDDS(DDBH, Round);
            for (SMDDS smddsData : getSMDDS) {
                String size = smddsData.getXXCC();
                Integer qty = smddsData.getQty();
                List<DDZLS1> getDDZLS1 = ddzlDao.getDDZLS1(DDBH);
                for (DDZLS1 ddzls1Data : getDDZLS1) {
                    // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
                    if (ddzls1Data.getYSSM().substring(4, 6).equals("97")) {
                        List<DDZL> getXS = ddzlDao.getDDZL(DDBH);
                        String XS = "";
                        for (DDZL value2 : getXS) {
                            XS = value2.getXieXing() + value2.getSheHao();
                        }
                        List<XXZLS1> getXXZLS1 = xxzlDao.getXXZLS1(XS);
                        Double sumKGS = 0.0;
                        for (XXZLS1 value3 : getXXZLS1) {
                            char xh = ddzls1Data.getXh().charAt(0);
                            int countG = (int) xh - 64;

                            switch (countG) {
                                case 1:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG01();
                                    }
                                    break;
                                case 2:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG02();
                                    }
                                    break;
                                case 3:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG03();
                                    }
                                    break;
                                case 4:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG04();
                                    }
                                    break;
                                case 5:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG05();
                                    }
                                    break;
                                case 6:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG06();
                                    }
                                    break;
                                case 7:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG07();
                                    }
                                    break;
                                case 8:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG08();
                                    }
                                    break;
                                case 9:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG09();
                                    }
                                    break;
                                case 10:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG10();
                                    }
                                    break;
                                case 11:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG11();
                                    }
                                    break;
                                case 12:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG12();
                                    }
                                    break;
                                case 13:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG13();
                                    }
                                    break;
                                case 14:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG14();
                                    }
                                    break;
                                case 15:
                                    if (size.equals(value3.getCC())) {
                                        sumKGS += qty * value3.getG15();
                                    }
                                    break;
                            }
                        }
                        insertPGZLS1_temp.setCldh(ddzls1Data.getCldh());
                        insertPGZLS1_temp.setClyl(sumKGS / 1000);
                        pgzlDao.inserPGZLS1_temp(insertPGZLS1_temp);
                        // 取得Z的數量
                        List<clzlsl> getclzlsl = clzlslService.getclzlsl(ddzls1Data.getCldh());
                        for (clzlsl value2 : getclzlsl) {
                            if (value2.getLb().equals("Z")) {
                                insertPGZLS1_temp.setCldh(value2.getCldhz());
                                insertPGZLS1_temp.setClyl((value2.getClyl() * qty) / 1000);
                                pgzlDao.inserPGZLS1_temp(insertPGZLS1_temp);
                            }
                        }
                    }
                }
            }
        }
    }

    // 大車算料資料存入PGZLS1
    @Override
    public List<PGZLS1> getPGZLS1dc(String PGDATE) {
        List<PGZLS1> getPGZLS1 = pgzlDao.getPGZLS1(PGDATE);
        if (getPGZLS1 == null) {
            PGZLS1 insertPGZLS1 = new PGZLS1();
            insertPGZLS1.setPGDATE(PGDATE);
            insertPGZLS1.setCQDH("B7U");
            insertPGZLS1.setUSERID("SUPER");
            insertPGZLS1.setUSERDATE(timeService.now());
            // 暫存檔、排除重複的料號
            List<PGZLS1> getPGZLS1_temp = pgzlDao.getPGZLS1_temp();
            for (PGZLS1 temp : getPGZLS1_temp) {
                insertPGZLS1.setCldh(temp.getCldh());
                insertPGZLS1.setClyl(temp.getClyl());
                pgzlDao.insertPGZLS1(insertPGZLS1);
            }
            // 刪掉暫存檔資料
            pgzlDao.deletePGZLS1_temp();
            return pgzlDao.getPGZLS1(PGDATE);
        } else {
            return getPGZLS1;
        }
    }

    // Insert PGZL & PGZLS
    @Override
    public void insertPGZL(PGZLUpdateParams pup) {
        String PGDATE = pup.getPgzl().get(0).getPGDATE();
        // insertPGZL
        PGZL insertPGZL = new PGZL();
        insertPGZL.setPGDATE(PGDATE);
        insertPGZL.setCQDH("B7U");
        insertPGZL.setDATE1(timeService.date2String(pup.getPgzl().get(0).getDATE1()));
        insertPGZL.setBZ(pup.getPgzl().get(0).getBZ());
        String dw = pup.getPgzl().get(0).getBZ();   // 為了PGZLS存檔判斷用
        insertPGZL.setCFM("N");
        insertPGZL.setUSERID("SUPER");
        insertPGZL.setUSERDATE(timeService.now());
        pgzlDao.insertPGZL(insertPGZL);

        // insertPGZLS
        for (PGZLS value : pup.getPgzls()) {
            PGZLS insertPGZLS = new PGZLS();
            insertPGZLS.setPGDATE(PGDATE);
            insertPGZLS.setCldh(value.getCldh());
            insertPGZLS.setKGS(value.getKGS());
            insertPGZLS.setPGSS(value.getPGSS());
            insertPGZLS.setKGS_RKS(value.getKGS_RKS());
            insertPGZLS.setGSSM(value.getGSSM());  // GSSM  替代為班別
            if (dw.equals("DC")) { // 大車派工
                insertPGZLS.setCQDH(value.getCQDH());  // CQDH  替代為輪次
                insertPGZLS.setUSERID(value.getUSERID());  // USERID  替代為鞋廠組別
                if (!(value.getUSERDATE().equals(""))) {
                    insertPGZLS.setUSERDATE(value.getUSERDATE().substring(0, 5));  // USERDATE  替代為時間段
                }
            } else if (dw.equals("RG")) {   // 打粗派工
                insertPGZLS.setCQDH(value.getCQDH());  // CQDH  替代為輪次
                insertPGZLS.setUSERID(value.getUSERID());  // USERID  替代為鞋廠組別
                if (!(value.getUSERDATE().equals(""))) {
                    insertPGZLS.setUSERDATE(value.getUSERDATE().substring(0, 5));  // USERDATE  替代為時間段
                }
            } else {
                insertPGZLS.setCQDH("B7U");
                insertPGZLS.setUSERID("SUPER");
                insertPGZLS.setUSERDATE(timeService.now());
            }
            pgzlDao.insertPGZLS(insertPGZLS);
        }
    }

    // Update pgzls1
    @Override
    public void updatePGZLS1(llUpdateParams lup) {

    }

    // 導入鞋廠輪次資料表頭
    @Override
    public List<SMDD> getSMDD(String DDBH, String dw) {
        List<SMDD> getSMDD = ddzlDao.getSMDD(DDBH, dw);
        for (SMDD value : getSMDD) {
            value.setPlanDate((value.getPlanDate()).substring(0, 10));
            value.setXH(String.format("%03d", Integer.parseInt(value.getXH())));
        }
        return getSMDD;
    }

    // 導入鞋廠輪次明細資料
    @Override
    public List<SMDDS> getSMDDS(String DDBH, String Round) {
        List<SMDDS> getSMDDS = ddzlDao.getSMDDS(DDBH, Round);
        // 置換輪次編號
        for (SMDDS value : getSMDDS) {
            value.setUSERID(Round);
        }
        return getSMDDS;
    }

    // 取得大車派工列印資料
    @Override
    public List<DDZLS1> getdcpgPrint(String DDBH, String PGDATE) {
        // Send Data //JSON 只能傳遞一組數列,所以用Map(List)把多個數列組合起來一起傳
        List SendData = new ArrayList();
        // Get DDZL's ARTICLE
        List<DDZL> getDDZL = ddzlDao.getDDZL(DDBH);
        String ARTICLE = "", XieXing = "";
        Integer Pairs = 0;
        ARTICLE = getDDZL.get(0).getARTICLE();
        Pairs = getDDZL.get(0).getPairs();
        XieXing = getDDZL.get(0).getXieXing();

        // Get PGZLS
        Set<String> size = new HashSet<>(); // 取得唯一Size
        List<PGZLS> getPGZLS = pgzlDao.getPGZLSdc(PGDATE); // 派工輪次資料
        for (PGZLS value : getPGZLS) {
            // 取得輪次尺寸
            List<SMDDS> getSMDDS = ddzlDao.getSMDDS(value.getCldh(), value.getCQDH());
            for (SMDDS value2 : getSMDDS) {
                size.add(value2.getXXCC());
            }
        }

        // JDK8 Map's foreach
//        SP.forEach((key, value) -> {
//            // System.out.println(key + ":" + value);
//            for (xxgjs value2 : getxxgjs) {
//                if (key.equals(value2.getXXCC())) {
//                    newSize.put(value2.getXXCC(), value);
//                    break;
//                } else {
//                    // newSize.add(value);
//                    newSize.put(value2.getXXCC(), value);
//                }
//            }
//        });

        // Get DDZLS1
        List<DDZLS1> getDDZLS1 = ddzlDao.getDDZLS1(DDBH);
        List<DDZLS1> removeDDZLS1 = new ArrayList<>();

        // 判斷屬於那一種類別(D大車、R打粗)
        if(PGDATE.substring(0,1).equals("D")){
            for (DDZLS1 value : getDDZLS1) {
                // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
                if ((value.getYSSM().substring(4, 6)).equals("96")) {
                    removeDDZLS1.add(value);
                }
            }
            // Remove 96
            for (DDZLS1 remove : removeDDZLS1) {
                getDDZLS1.remove(remove);
            }
        } else if (PGDATE.substring(0,1).equals("R")) {
            for (DDZLS1 value : getDDZLS1) {
                // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
                if ((value.getYSSM().substring(4, 6)).equals("97")) {
                    removeDDZLS1.add(value);
                }
                // 打粗去除"Logo"
                if((value.getYSSM()).contains("LOGO")){
                    removeDDZLS1.add(value);
                }
            }
            // Remove 97
            for (DDZLS1 remove : removeDDZLS1) {
                getDDZLS1.remove(remove);
            }
        }


        // 取得鞋廠BOM資料    getDDZLS1(已排除底模)    這裡的尺碼已降碼且合併所有輪次的尺寸
        List<XXZLSERP> getXXZLSErp2remove = xxzlDao.getXXZLSErp(ARTICLE);

        Map<String, List> sizeDetail = new HashMap<>();
        // List<DDZLS> getsizeList = new ArrayList<>();
        PGZLS1 setPGZLS1 = new PGZLS1();
        for (DDZLS1 checkSize : getDDZLS1) {
            for (XXZLSERP value : getXXZLSErp2remove) {
                // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
                if (checkSize.getYSSM().substring(0, 4).equals(value.getBWBH())) {
                    List<String> sizeList = new ArrayList<>();
                    // for (String getSize : sortSize) {
                    for (String getSize : size) {
                        if (value.getCCQQ() != null && !(value.getCCQQ().equals(""))) {
                            if (Double.valueOf(getSize) >= Double.valueOf(value.getCCQQ()) && Double.valueOf(getSize) <= Double.valueOf(value.getCCQZ())) {
                                sizeList.add(getSize);
                            } else {
                                removeDDZLS1.add(checkSize);  // 不要用
                            }
                        } else {
                            sizeList.add(getSize);
                        }
                    }
                    // 取得降碼資料(這裡要判斷是否有取到值嗎？)
                    Set<String> newSize = new HashSet<>(); // 取得唯一newSize
                    List<xxgjs> getxxgjs = xxzlDao.getxxgjs(XieXing);
                    for (String getsize : sizeList) {
                        for (xxgjs value2 : getxxgjs) {
                            if (getsize.equals(value2.getXXCC())) {
                                newSize.add(value2.getGJCC());
                                break;
                            } else {
                                newSize.add(getsize);
                            }
                        }
                    }
                    // converting HashSet to arraylist
                    ArrayList<String> sortSize = new ArrayList<>(newSize);
                    // sorting the list and then printing
                    Collections.sort(sortSize);

                    // sizeDetail.put(value.getBWBH(), sizeList);
                    sizeDetail.put(value.getBWBH(), sortSize);
                }
            }
        }

        // Set ARTICLE
        getDDZLS1.get(0).setCQDH(ARTICLE); // 用CQDH代替ARTICLE傳資料
        getDDZLS1.get(0).setLB(String.valueOf(Pairs));   // 用LB代替總雙數傳資料
        SendData.add(0, getDDZLS1);

        List sendXXZLS1_B = new ArrayList();

        int i = 0;
        // 排除底模資料
        // Get XXZLS1_B
        for (DDZLS1 value : getDDZLS1) {
            // Get XXZLS1_B
            bwBuild getNewXXZLS1_B = new bwBuild();
            // 20220606-C01396C013*015後跟標(前面加上4碼鞋廠的部位代號)
            List<bwBuild> getXXZLS1_B = bwBuildDao.getBMC(value.getYSSM().substring(4, 14));
            List<XXZLSERP> getXXZLSErp = xxzlDao.getXXZLSErp(ARTICLE);
            // 部位說明(鞋廠和底廠的部位可能代號相同、對應不同、要再比對、C01396C013*015後跟標(前面加上4碼鞋廠的部位代號))
            for (XXZLSERP value2 : getXXZLSErp) {
                if (getXXZLS1_B.get(0).getNO().substring(2, 6).equals(value.getYSSM().substring(0, 4))) {
                    if (getXXZLS1_B.get(0).getNO().substring(2, 6).equals(value2.getBWBH())) {
                        getNewXXZLS1_B.setBwlb(value2.getClZwpm());
                    }
                } else {
                    if (value.getYSSM().substring(0, 4).equals(value2.getBWBH())) {
                        getNewXXZLS1_B.setBwlb(value2.getClZwpm());
                    }
                }
            }

            getNewXXZLS1_B.setNO(getXXZLS1_B.get(0).getNO());
            getNewXXZLS1_B.setLbdh(getXXZLS1_B.get(0).getLbdh());
            getNewXXZLS1_B.setYSSM(getXXZLS1_B.get(0).getYSSM());
            getNewXXZLS1_B.setMjbh(getXXZLS1_B.get(0).getMjbh());
            getNewXXZLS1_B.setHw(getXXZLS1_B.get(0).getHw());
            getNewXXZLS1_B.setWidth(getXXZLS1_B.get(0).getWidth());
            getNewXXZLS1_B.setThickness(getXXZLS1_B.get(0).getThickness());
            sendXXZLS1_B.add(i, getNewXXZLS1_B);
            i += 1;
        }
        SendData.add(1, sendXXZLS1_B);

        //getsizeList
        SendData.add(2, sizeDetail);

        // 取得模具降碼資料
        SendData.add(3, xxzlDao.getxxgjs(XieXing));

        return SendData;
    }

    // 取得鞋廠部門資料
    @Override
    public List<BDepartment> getdepList() {
        return pgzlDao.getdepList();
    }
}
