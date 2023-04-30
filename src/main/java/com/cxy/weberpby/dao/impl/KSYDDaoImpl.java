package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.KSYDDao;
import com.cxy.weberpby.model.KSYD;
import com.cxy.weberpby.model.KSYDS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/11/30
 * @Description 入出庫資料
 *
 * 取得 KSYD 資料
 * 取得 KSYDS 資料
 */
@Component
public class KSYDDaoImpl implements KSYDDao {
    // LBY_DD
    @Resource
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;
    Map<String, Object> map;

    // 取得 KSYD 資料
    @Override
    public List<KSYD> getKSYDs() {
        return null;
    }

    // 取得 KSYDS 資料
    @Override
    public List<KSYDS> getKSYDSs() {
        return null;
    }
}
