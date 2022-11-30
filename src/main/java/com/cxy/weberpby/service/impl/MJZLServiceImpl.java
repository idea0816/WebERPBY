package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.MJZLDao;
import com.cxy.weberpby.model.MJZL;
import com.cxy.weberpby.service.MJZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/4
 * @Description 模具明细资料
 * <p>
 * -- 96,底模
 * -- 97,大車
 * -- 98,中底,沒有建部位也沒關係
 * -- 99,合併部件
 * <p>
 * List<String> getErpMJ(); //取得鞋廠的大底&刀模資料
 * List<MJZL> getML();  // 取得模具资料(mjbh, lbdh)
 */

@Component
public class MJZLServiceImpl implements MJZLService {

    @Autowired
    private MJZLDao mjzlDao;

    //取得鞋廠的大底&刀模資料


    @Override
    public List<String> getErpMJ() {
        // 取得鞋廠大底模具
        List<String> getErpMJ = mjzlDao.getErpDD();
        int add = getErpMJ.size() - 1;
        // 取得鞋廠刀模模具
        List<String> getErpDM = mjzlDao.getErpDM();
        for (String value : getErpDM) {
            getErpMJ.add(add, value);
            add = +1;
        }

        return getErpMJ;
    }

    // 取得模具资料(mjbh, lbdh)
    @Override
    public List<MJZL> getML() {
        // 替換模具類別代號
        List<MJZL> newData = mjzlDao.getML();
        for (MJZL value : newData) {
            if (value.getLbdh().equals("96")) {
                value.setLbdh("大底");
            }
            if (value.getLbdh().equals("97")) {
                value.setLbdh("大車");
            }
        }
        return newData;
    }
}
