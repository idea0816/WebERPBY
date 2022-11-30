package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.CLZLDao;
import com.cxy.weberpby.dao.clzlslDao;
import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.model.CLZL;
import com.cxy.weberpby.model.clzlsl;
import com.cxy.weberpby.service.clzlslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author CXY
 * @version Create Time: 2022/4/21
 * @Description 配方组成資料(clzlsl & clzlsz 共用)
 * <p>
 * List<clzlsl> getclzlsl(String cldh);  // Get Data of clzlsl
 * void insertClzlsl(clzlsl clzlsl);   // Insert clzlsl
 * List<clzlsl> getclzlsz(String cldh);  // Get Data of clzlsz
 * List<clzlsl> getRealclzlsz(String cldh) {}   // Get Real Data of clzlsz
 * void deleteclzlsz(String cldh);  // Delete clzlsz
 */

@Component
public class clzlslServiceImpl implements clzlslService {

    @Autowired
    private clzlslDao clzlslDao;

    @Autowired
    private CLZLDao clzlDao;

    // 配方類查詢條件設定
    CLZLQueryParams clzlQueryParams = new CLZLQueryParams();

    // Get Data of clzlsl
    @Override
    public List<clzlsl> getclzlsl(String cldh) {
        // 取得配方明細資料
        List<clzlsl> pfList = clzlslDao.getclzlsl(cldh);
        // clzlQueryParams = new CLZLQueryParams();
        // 把 cldh(VRA001) 用原物料中文品名取代掉
        for (clzlsl value : pfList) {
            String zwpm = null;
            clzlQueryParams.setCldh(value.getCldhz());
            List<CLZL> getzwpm = clzlDao.getCLZL(clzlQueryParams);
            for (CLZL value2 : getzwpm) {
                zwpm = value2.getZwpm();
            }
            value.setCldh(zwpm);
        }
        return pfList;
    }

    // Insert clzlsl
    @Override
    public void insertClzlsl(clzlsl clzlsl) {
        clzlslDao.insertclzlsl(clzlsl);
    }

    // Get Data of clzlsz
    @Override
    public List<clzlsl> getclzlsz(String cldh) {
        // 判斷配方代號是*000的是第一版(刪掉版號)
        String realcldh;
        int getStar = cldh.indexOf("*");
        if (Objects.equals(cldh.substring(getStar + 1, getStar + 4), "000")) {
            realcldh = cldh.substring(0, getStar);
        } else {
            realcldh = cldh;
        }
        // 取得配方明細資料
        List<clzlsl> pfList = clzlslDao.getclzlsz(realcldh);
        // 把 cldh(RU001) 用原物料中文品名取代掉
        // 配方類查詢條件設定    // cldj四捨五入到小數點後2位
        for (clzlsl value : pfList) {
            String zwpm = null;
            clzlQueryParams.setCldh(value.getCldhz());
            List<CLZL> getzwpm = clzlDao.getCLZL(clzlQueryParams);
            for (CLZL value2 : getzwpm) {
                zwpm = value2.getZwpm();
            }
            value.setCldh(zwpm);

            // cldj四捨五入到小數點後2位
            Double cldjRound = Double.valueOf(Math.round(value.getCldj()*100)/100.0);
            value.setCldj(cldjRound);

        }
        return pfList;
    }

    // Get Real Data of clzlsz
    public List<clzlsl> getRealclzlsz(String cldh) {
        // 取得配方明細資料
        return clzlslDao.getclzlsz(cldh);
    }

    // Delete clzlsz
    @Override
    public void deleteclzlsz(String cldh) {
        if(cldh.contains("*")){
            // 判斷配方代號是*000的是第一版(刪掉版號)
            int getStar = cldh.indexOf("*");
            if (Objects.equals(cldh.substring(getStar + 1, getStar + 4), "000")) {
                clzlslDao.deleteclzlsz(cldh.substring(0, getStar));
            } else {
                clzlslDao.deleteclzlsz(cldh);
            }
        }

    }
}
