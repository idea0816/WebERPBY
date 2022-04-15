package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.XXZLDao;
import com.cxy.weberpby.dao.lbzlsDao;
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
 * String updateDdXXZL();   // 自动导入鞋厂型体资料(*注意、对应已放行的订单*)
 * List<XXZL> getErpXiexing();  // 取得鞋厂订单的部分型体资料(*注意、是已放行的订单*)
 */

@Component
public class XXZLServiceImpl implements XXZLService {

    @Autowired
    private XXZLDao xxzlDao;
    @Autowired
    private lbzlsDao lbzlsdao;
    @Autowired
    private timeService timeservice;

    @Override
    public String updateDdXXZL() {
        // 取得鞋厂型体资料(部分)
        List<XXZL> ErpXiexing = xxzlDao.getErpXiexing();
        // 取得底厂型体资料(部分)
        List<XXZL> DdXXZL = xxzlDao.getDdXiexing();
        // 取的鞋厂型体和底厂有重复要删除的
        ArrayList<Integer> removeJ = new ArrayList<Integer>();

        for (int i = 0; i < DdXXZL.size(); i++) {
            String DdXiexing = DdXXZL.get(i).getXieXing();
            String DdShehao = DdXXZL.get(i).getSheHao();

            for (int j = 0; j < ErpXiexing.size(); j++) {
                if (ErpXiexing.get(j).getXieXing().contains(DdXiexing) && ErpXiexing.get(j).getSheHao().contains(DdShehao)) {
                    removeJ.add(j);
                    // 在底厂有型体资料的要不要再检查 Size & 部位 ???
                } else {
                }
            }
        }

        // ErpXiexing.remove // 這裡寫得很爛、找時間改寫成 JDK8 的 filter 用法
        int delX = 0;
        for (int i = 0; i < removeJ.size(); i++) {
            delX = removeJ.get(i) - i;
            ErpXiexing.remove(delX);
        }

        // 写入型体资料 // 写入 Size & 重量 资料 // 写入部位 & 配方资料
        XXZL xxzl = new XXZL();
        XXZLS1 xxzls1 = new XXZLS1();
        XXZLS xxzls = new XXZLS();
        for (int i = 0; i < ErpXiexing.size(); i++) {
            xxzl.setXieXing(ErpXiexing.get(i).getXieXing());
            xxzls1.setXieXing(ErpXiexing.get(i).getXieXing());
            xxzl.setSheHao(ErpXiexing.get(i).getSheHao());
            xxzls1.setSheHao(ErpXiexing.get(i).getSheHao());
            xxzl.setARTICLE(ErpXiexing.get(i).getARTICLE());
            xxzl.setUSERDATE(timeservice.now());
            xxzls1.setUSERDATE(timeservice.now());
            // 写入型体资料
            xxzlDao.XXZLInsert(xxzl);
            // 写入 Size & 重量 资料
            xxzlDao.XXZLS1Insert(xxzls1);

            // 取得鞋厂部位资料
            List<XXZLSERP> getErpXxzls = xxzlDao.getErpXxzls(xxzl);
            int countXh = 0;    // 部位序号A
            int recountXh = 0;  // 部位序号O
            for (int y = 0; y < getErpXxzls.size(); y++) {
                // 检查鞋厂部位对应类别明细资料是否存在、若无则写入类别明细资料
                if(lbzlsdao.checklbzls(getErpXxzls.get(y).getBWBH()) == 0) {
                    lbzls lbzls = new lbzls();
                    if ((String.valueOf(getErpXxzls.get(y).getCCQQ()).equals("")) || (String.valueOf(getErpXxzls.get(y).getCCQQ()).equals("null"))) {
                        // 有 Size, lb = 91
                        lbzls.setLb("91");
                        lbzls.setLbdh(getErpXxzls.get(y).getBWBH());
                        lbzls.setBz("");
                        lbzls.setBz1("");
                    }else {
                        // 没有 Size, lb = 92
                        lbzls.setLb("92");
                        lbzls.setLbdh(getErpXxzls.get(y).getBWBH());
                        lbzls.setBz(getErpXxzls.get(y).getCCQQ());
                        lbzls.setBz1(getErpXxzls.get(y).getCCQZ());
                    }
                    lbzls.setUSERDATE(timeservice.now());
                    lbzlsdao.lbzlsInsert(lbzls);
                }
                // 写入部位 & 配方资料
                if ((String.valueOf(getErpXxzls.get(y).getCCQQ()).equals("")) || (String.valueOf(getErpXxzls.get(y).getCCQQ()).equals("null"))) {
                    xxzls.setXieXing(ErpXiexing.get(i).getXieXing());
                    xxzls.setSheHao(ErpXiexing.get(i).getSheHao());
                    xxzls.setXh(String.valueOf(Character.toUpperCase((char) (97 + countXh)))); // (char)(97)=英文A // 转大写
                    xxzls.setYSSM(getErpXxzls.get(y).getBWBH()); // 取得部位代号、Select lbzls.zwsm
                    xxzls.setUSERDATE(timeservice.now());
                    xxzls.setLB(getErpXxzls.get(y).getBWBH());
                    countXh += 1;
                }else{
                    xxzls.setXieXing(ErpXiexing.get(i).getXieXing());
                    xxzls.setSheHao(ErpXiexing.get(i).getSheHao());
                    xxzls.setXh(String.valueOf(Character.toUpperCase((char) (111 - recountXh)))); // (char)（111)=英文O // 转大写
                    xxzls.setYSSM(getErpXxzls.get(y).getBWBH()); // 取得部位代号、Select lbzls.zwsm
                    xxzls.setUSERDATE(timeservice.now());
                    xxzls.setLB(getErpXxzls.get(y).getBWBH());
                    recountXh +=1;
                }
                xxzlDao.XXZLSInsert(xxzls);
            }

            // for (int x = 1; x <= 15; x++) { // 自动产生A～O
                // 小写 (char) (96 + i)
                // 大写 Character.toUpperCase((char) (97 + countXh))
            // }
        }

        return "共导入" + ErpXiexing.size() + "筆型体資料";
    }


    // 取得鞋厂订单的型体资料(*注意、是已放行的订单*)
    @Override
    public List<XXZL> getErpXiexing() {
        return null;
    }
}
