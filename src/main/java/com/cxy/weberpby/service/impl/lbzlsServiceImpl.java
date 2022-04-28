package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.lbzlsDao;
import com.cxy.weberpby.dto.lbzlUpdateParams;
import com.cxy.weberpby.model.lbzl;
import com.cxy.weberpby.model.lbzls;
import com.cxy.weberpby.service.lbzlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/20
 * @Description 代号类别明细资料 - 上一层是 lbzl
 * <p>
 * List<lbzl> getlbzlList();   // 取得類別資料
 * List<lbzls> getlbzlsList();  // 取得类别明细资料
 * void updatelbzl(lbzlUpdateParams lup);   // Update lbzl & lbzls
 * void insertlbzl(lbzlUpdateParams lup);   // Insert lbzl & lbzls
 * void deletelbzl(String lb);  // Delete lbzl & lbzls
 */

@Component
public class lbzlsServiceImpl implements lbzlsService {

    @Autowired
    private lbzlsDao lbzlsDao;

    @Autowired
    private timeServiceImpl timeService;

    // 取得類別資料
    @Override
    public List<lbzl> getlbzlList() {
        return lbzlsDao.getlbzlList();
    }

    // 取得部位类别明细资料
    @Override
    public List<lbzls> getlbzlsList(String lb) {
        return lbzlsDao.getlbzlsList(lb);
    }

    // Update lbzl & lbzls
    @Override
    public void updatelbzl(lbzlUpdateParams lup) {
        // 判斷新舊 lbzls

        // 判斷新舊 lbzl
        if (lup.getNewlbzl().get(0).getLb().equals(lup.getOldlbzl().get(0).getLb())) {
            // 檢查新舊 lbzl 內容
            if (lup.getNewlbzl().get(0).getZwsm().equals(lup.getOldlbzl().get(0).getZwsm()) || lup.getNewlbzl().get(0).getBz().equals(lup.getOldlbzl().get(0).getBz())) {
                // do nothing
            } else {
                // 改變中文說明 & 備註
                lbzl updatelbzl = new lbzl();
                updatelbzl.setLb(lup.getNewlbzl().get(0).getLb());
                updatelbzl.setZwsm(lup.getNewlbzl().get(0).getZwsm());
                updatelbzl.setBz(lup.getNewlbzl().get(0).getBz());
                updatelbzl.setUSERDATE(timeService.now());

                lbzlsDao.updatelbzl(updatelbzl);
            }
            // 不管 lbzl 有沒有變動、都當做 lbzls 資料變動了
            // Delete old lbzls
            lbzlsDao.deletelbzls(lup.getOldlbzl().get(0).getLb());
            // Insert lbzls
            for (lbzls value : lup.getNewlbzls()) {
                lbzls insertlbzls = new lbzls();
                insertlbzls.setLb(lup.getNewlbzl().get(0).getLb());
                insertlbzls.setLbdh(value.getLbdh());
                insertlbzls.setZwsm(value.getZwsm());
                insertlbzls.setBz(value.getBz());
                insertlbzls.setBz1(value.getBz1());
                insertlbzls.setUSERID("SUPER");
                insertlbzls.setUSERDATE(timeService.now());
                lbzlsDao.insertlbzls(insertlbzls);
            }

        } else {
            // 如果新類別編號改變、就直接新增後刪掉舊類別(含類別明細)
            // Insert newlbzl
            lbzl insertlbzl = new lbzl();
            insertlbzl.setLb(lup.getNewlbzl().get(0).getLb());
            insertlbzl.setZwsm(lup.getNewlbzl().get(0).getZwsm());
            insertlbzl.setYwsm("");
            insertlbzl.setBz(lup.getNewlbzl().get(0).getBz());
            insertlbzl.setUSERID("SUPER");
            insertlbzl.setUSERDATE(timeService.now());
            lbzlsDao.insertlbzl(insertlbzl);
            // Delete oldlbzl & old lbzls
            lbzlsDao.deletelbzl(lup.getOldlbzl().get(0).getLb());
            lbzlsDao.deletelbzls(lup.getOldlbzl().get(0).getLb());
            // Insert lbzls
            for (lbzls value : lup.getNewlbzls()) {
                lbzls insertlbzls = new lbzls();
                insertlbzls.setLb(lup.getNewlbzl().get(0).getLb());
                insertlbzls.setLbdh(value.getLbdh());
                insertlbzls.setZwsm(value.getZwsm());
                insertlbzls.setBz(value.getBz());
                insertlbzls.setBz1(value.getBz1());
                insertlbzls.setUSERID("SUPER");
                insertlbzls.setUSERDATE(timeService.now());
                lbzlsDao.insertlbzls(insertlbzls);
            }
        }
    }

    // Insert lbzl & lbzls
    @Override
    public void insertlbzl(lbzlUpdateParams lup) {
        // Insert newlbzl
        lbzl insertlbzl = new lbzl();
        insertlbzl.setLb(lup.getNewlbzl().get(0).getLb());
        insertlbzl.setZwsm(lup.getNewlbzl().get(0).getZwsm());
        insertlbzl.setYwsm("");
        insertlbzl.setBz(lup.getNewlbzl().get(0).getBz());
        insertlbzl.setUSERID("SUPER");
        insertlbzl.setUSERDATE(timeService.now());
        lbzlsDao.insertlbzl(insertlbzl);
        // Insert lbzls
        for (lbzls value : lup.getNewlbzls()) {
            lbzls insertlbzls = new lbzls();
            insertlbzls.setLb(lup.getNewlbzl().get(0).getLb());
            insertlbzls.setLbdh(value.getLbdh());
            insertlbzls.setZwsm(value.getZwsm());
            insertlbzls.setBz(value.getBz());
            insertlbzls.setBz1(value.getBz1());
            insertlbzls.setUSERID("SUPER");
            insertlbzls.setUSERDATE(timeService.now());
            lbzlsDao.insertlbzls(insertlbzls);
        }

    }

    // Delete lbzl & lbzls
    @Override
    public void deletelbzl(String lb) {
        lbzlsDao.deletelbzl(lb);
        lbzlsDao.deletelbzls(lb);
    }
}
