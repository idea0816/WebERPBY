package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.CLZLDao;
import com.cxy.weberpby.dao.DDZLDao;
import com.cxy.weberpby.dao.XXZLDao;
import com.cxy.weberpby.dao.lbzlsDao;
import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.model.*;
import com.cxy.weberpby.service.autoImportService;
import com.cxy.weberpby.service.clzlslService;
import com.cxy.weberpby.service.timeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/2
 * @Description 導入的功能放在這裡、不要混雜在其他Service中
 * <p>
 * String insertBW();   // 導入部位資料
 * String insertXXZL(); // 导入型体资料
 * String insertCLZL(); // 標準配方R轉換V(沒考慮到版本問題)
 * String inserDDZLS1();    // 導入訂單配方資料
 */

@Component
public class autoImportServiceImpl implements autoImportService {

    @Autowired
    private XXZLDao xxzlDao;

    @Autowired
    private lbzlsDao lbzlsDao;

    @Autowired
    private CLZLDao clzlDao;

    @Autowired
    private clzlslService clzlslService;

    @Autowired
    private DDZLDao ddzlDao;

    @Autowired
    private timeService timeservice;

    // 標準配方R轉換V
    @Override
    public String insertBW() {
        // 检查鞋厂部位对应类别明细资料是否存在、若无则写入类别明细资料
        // 取得鞋厂部位资料
        List<String> getErpBw = xxzlDao.getErpBWBH();
        // 取得底廠底模部位資料
        List<lbzls> getBwList96 = lbzlsDao.getlbzlsList("96");
        // 取得底廠大車部位資料
        List<lbzls> getBwList97 = lbzlsDao.getlbzlsList("97");

        // 取的鞋厂部位和底厂有重复要删除的(96)
        ArrayList<Integer> removeB = new ArrayList<>();

        for (lbzls value : getBwList96) {
            String lbdh96 = value.getLbdh();
            for (int b = 0; b < getErpBw.size(); b++) {
                if (getErpBw.get(b).contains(lbdh96)) {
                    removeB.add(b);
                }
            }
        }
        // getErpBw.remove // 這裡寫得很爛、找時間改寫成 JDK8 的 filter 用法
        int delX;
        for (int i = 0; i < removeB.size(); i++) {
            delX = removeB.get(i) - i;
            getErpBw.remove(delX);
        }

        // 取的鞋厂部位和底厂有重复要删除的(97)
        ArrayList<Integer> removeC = new ArrayList<>();

        for (lbzls value : getBwList97) {
            String lbdh97 = value.getLbdh();
            for (int b = 0; b < getErpBw.size(); b++) {
                if (getErpBw.get(b).contains(lbdh97)) {
                    removeC.add(b);
                }
            }
        }
        // getErpBw.remove // 這裡寫得很爛、找時間改寫成 JDK8 的 filter 用法
        int delY;
        for (int i = 0; i < removeC.size(); i++) {
            delY = removeC.get(i) - i;
            getErpBw.remove(delY);
        }

        // 寫入部位資料
        for (String value : getErpBw) {
            List<lbzls> getBWList = lbzlsDao.getBW(value);
            // 判斷部位類別
            if (getBWList.get(0).getBz() == null || getBWList.get(0).getBz().equals("")) {
                // 96,底模
                getBWList.get(0).setLb("96");
                getBWList.get(0).setBz("");
                getBWList.get(0).setBz1("");
            } else {
                // 97,大車
                getBWList.get(0).setLb("97");
                getBWList.get(0).setUSERID("SUPER");
            }
            getBWList.get(0).setUSERID("SUPER");
            getBWList.get(0).setUSERDATE(timeservice.now());
            lbzlsDao.insertlbzls(getBWList.get(0));
        }

        return "共导入" + getErpBw.size() + "筆型体資料";
    }

    // 导入型体资料
    @Override
    public String insertXXZL() {
        // 取得鞋厂型体资料(XSA)
        List<XXZL> getErpXSA = xxzlDao.getErpXSA();
        // 取得底厂型体资料(部分)
        List<XXZL> getXSA = xxzlDao.getXSA();
        // 取的鞋厂型体和底厂有重复要删除的
        ArrayList<Integer> removeJ = new ArrayList<>();
        if (getXSA == null) {
            // do nothing
        } else {
            for (XXZL value : getXSA) {
                String DdXiexing = value.getXieXing();
                String DdShehao = value.getSheHao();

                for (int j = 0; j < getErpXSA.size(); j++) {
                    if (getErpXSA.get(j).getXieXing().contains(DdXiexing) && getErpXSA.get(j).getSheHao().contains(DdShehao)) {
                        removeJ.add(j);
                    }
                }
            }

            // ErpXiexing.remove // 這裡寫得很爛、找時間改寫成 JDK8 的 filter 用法
            int delX;
            for (int i = 0; i < removeJ.size(); i++) {
                delX = removeJ.get(i) - i;
                getErpXSA.remove(delX);
            }
        }

        // 写入型体资料 // 写入 Size 资料
        XXZL xxzl = new XXZL();
        XXZLS1 xxzls1 = new XXZLS1();
        for (XXZL value : getErpXSA) {
            xxzl.setXieXing(value.getXieXing());
            xxzls1.setXieXing(value.getXieXing());
            xxzl.setSheHao(value.getSheHao());
            xxzls1.setSheHao(value.getSheHao());
            xxzl.setARTICLE(value.getARTICLE());
            xxzl.setUSERDATE(timeservice.now());
            xxzls1.setUSERDATE(timeservice.now());
            // 写入型体资料
            xxzlDao.XXZLInsert(xxzl);
            // 写入 Size 资料
            xxzlDao.XXZLS1Insert(xxzls1);
        }
        return "共导入" + getErpXSA.size() + "筆型体資料";
    }

    // 標準配方R轉換V(沒考慮到版本問題、重量沒重算)
    @Override
    public String insertCLZL() {
        CLZLQueryParams clzlQueryParams = new CLZLQueryParams();
        clzlQueryParams.setLb("R");
        // 找到R的資料
        List<CLZL> getR = clzlDao.getCLZL(clzlQueryParams);
        for (CLZL value : getR) {
            // 取得clzlsz RV005*001
            List<clzlsl> getRclzlsz = clzlslService.getRealclzlsz(value.getCldh());
            CLZL R2VRclzl = new CLZL();
            clzlsl R2VRclzlsl = new clzlsl();
            int getStar = value.getCldh().indexOf("*");
            String cldh = "V" + value.getCldh().substring(0, getStar);
            // double sumcldj = 0, sumWclyl = 0, oneHand = 0, sumclyl = 0;
            for (clzlsl value2 : getRclzlsz) {
                if (value2.getLb().equals("R")) {
                    List<clzlsl> getRRclzlsz = clzlslService.getRealclzlsz(value2.getCldhz());
                    for (clzlsl value3 : getRRclzlsz) {
                        // (:cldh, :lb, :cldhz, :clyl, :phr, :cldj, :USERID, :USERDATE)
                        // System.out.println("A:" + cldh + "," + value3.getLb() + "," + value3.getCldhz() + "," + value3.getClyl() + "," + value3.getPhr() + "," + value3.getCldj() + "USERIDDATE");
                        R2VRclzlsl.setCldh(cldh);
                        R2VRclzlsl.setLb(value3.getLb());
                        R2VRclzlsl.setCldhz(value3.getCldhz());
                        R2VRclzlsl.setClyl(value3.getClyl());
                        R2VRclzlsl.setPhr(value3.getPhr());
                        R2VRclzlsl.setCldj(value3.getCldj());
                        R2VRclzlsl.setUSERID("SUPER");
                        R2VRclzlsl.setUSERDATE(timeservice.now());
                    }
                } else {
                    // System.out.println("B:" + cldh + "," + value2.getLb() + "," + value2.getCldhz() + "," + value2.getClyl() + "," + value2.getPhr() + "," + value2.getCldj() + "USERIDDATE");
                    R2VRclzlsl.setCldh(cldh);
                    R2VRclzlsl.setLb(value2.getLb());
                    R2VRclzlsl.setCldhz(value2.getCldhz());
                    R2VRclzlsl.setClyl(value2.getClyl());
                    R2VRclzlsl.setPhr(value2.getPhr());
                    R2VRclzlsl.setCldj(value2.getCldj());
                    R2VRclzlsl.setUSERID("SUPER");
                    R2VRclzlsl.setUSERDATE(timeservice.now());


                }
                clzlslService.insertClzlsl(R2VRclzlsl);
            }
            // RV005*001,R,泡棉膠,,KGS,,19.356826,,,,,90.00,SUPER,20220505,0604,,
            // System.out.println("AA:" + cldh + "." + "V" + "," + value.getZwpm() + "," + value.getDwbh() + "," + value.getCldj() + "," + value.getTotKgs());
            R2VRclzl.setCldh(cldh);
            R2VRclzl.setCllb("V");
            R2VRclzl.setZwpm(value.getZwpm());
            R2VRclzl.setDwbh(value.getDwbh());
            R2VRclzl.setCldj(value.getCldj());
            R2VRclzl.setTotKgs(value.getTotKgs());
            R2VRclzl.setUSERID("SUPER");
            R2VRclzl.setUSERDATE(timeservice.now());

            clzlDao.insertCLZL(R2VRclzl);


        }
        return null;
    }

    // 導入訂單配方資料
    @Override
    public String inserDDZLS1() {

        // 取得订单资料（依XXZLS有配方的且排除已派工的)
        List<DDZL> getDDZL = ddzlDao.getDDZLbyXXZLS();
        for (DDZL value : getDDZL) {
            // 先刪、再新增
            ddzlDao.DDZLS1Delete(value.getDDBH());
            DDZLS1 ddzls1 = new DDZLS1();
            ddzls1.setDDBH(value.getDDBH());    // 訂單編號
            ddzls1.setCQDH("B7U");  // 廠區別
            List<DDZLS> getDDZLS = ddzlDao.getDDZLS(value.getDDBH());
            List<XXZLS1> getXXZLS1 = xxzlDao.getXXZLS1(value.getXieXing() + value.getSheHao());

            List<XXZLS> getXXZLS = xxzlDao.getXXZLS(value.getARTICLE());
            for (XXZLS value3 : getXXZLS) {
                if (!(value3.getCldh().equals("V"))) {
//                    System.out.println(value.getDDBH()+":"+value3.getXh()+","+value3.getCldh());
                    char xh = value3.getXh().charAt(0);
                    int countG = (int) xh - 64;
                    Double sumKGS = 0.0;
                    switch (countG) {
                        case 1:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG01();
                                    }
                                }
                            }
                            break;
                        case 2:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG02();
                                    }
                                }
                            }
                            break;
                        case 3:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG03();
                                    }
                                }
                            }
                            break;
                        case 4:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG04();
                                    }
                                }
                            }
                            break;
                        case 5:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG05();
                                    }
                                }
                            }
                            break;
                        case 6:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG06();
                                    }
                                }
                            }
                            break;
                        case 7:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG07();
                                    }
                                }
                            }
                            break;
                        case 8:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG08();
                                    }
                                }
                            }
                            break;
                        case 9:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG09();
                                    }
                                }
                            }
                            break;
                        case 10:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG10();
                                    }
                                }
                            }
                            break;
                        case 11:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG11();
                                    }
                                }
                            }
                            break;
                        case 12:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG12();
                                    }
                                }
                            }
                            break;
                        case 13:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG13();
                                    }
                                }
                            }
                            break;
                        case 14:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG14();
                                    }
                                }
                            }
                            break;
                        case 15:
                            for (DDZLS value2 : getDDZLS) {
                                for (XXZLS1 value4 : getXXZLS1) {
                                    if (value2.getCC().equals(value4.getCC())) {
                                        sumKGS += value2.getQty() * value4.getG15();
                                    }
                                }
                            }
                            break;
                    }
                    ddzls1.setXh(value3.getXh());
                    ddzls1.setCldh(value3.getCldh());
                    ddzls1.setYSSM(value3.getYSSM());
                    ddzls1.setKGS(sumKGS/1000); // 別忘記把g轉成Kg
                    ddzls1.setLOSS(0.0);
                    ddzls1.setUSERID("SUPER");
                    ddzls1.setUSERDATE(timeservice.now());
                    ddzls1.setLB("00");
                    ddzlDao.DDZLS1Insert(ddzls1);
                }

            }
        }

        return "success";
    }
}
