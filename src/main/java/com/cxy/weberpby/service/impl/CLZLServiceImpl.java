package com.cxy.weberpby.service.impl;

import com.cxy.weberpby.dao.CLZLDao;
import com.cxy.weberpby.model.CLZL;
import com.cxy.weberpby.service.CLZLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 材料基本資料讀取
 *
 * List<CLZL> getAllVofCLZL();   // Get All V of CLZL
 */

@Component
public class CLZLServiceImpl implements CLZLService {

    @Autowired
    private CLZLDao clzlDao;

    // Get All V of CLZL
    @Override
    public List<CLZL> getAllVofCLZL() {
        return clzlDao.getAllVofCLZL();
    }
}
