package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.CLZLDao;
import com.cxy.weberpby.mapper.CLZLRowMapper;
import com.cxy.weberpby.model.CLZL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 材料基本資料讀取
 *
 * List<CLZL> getAllVofCLZL();   // Get All V of CLZL
 */

@Component
public class CLZLDaoImpl implements CLZLDao {

    // LYS_ERP
    // @Autowired
    // @Qualifier("lyserpJdbcTemplate")
    // private NamedParameterJdbcTemplate lyserpJdbcTemplate;

    // LIY_DD
    @Autowired
    @Qualifier("liyddJdbcTemplate")
    private NamedParameterJdbcTemplate liyddJdbcTemplate;

    // LBY_DD
    // @Autowired
    // @Qualifier("lbyddJdbcTemplate")
    // private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    // Get All VR of CLZL
    @Override
    public List<CLZL> getAllVofCLZL() {
        String sql = "SELECT cldh,cllb,zwpm,ywpm,dwbh,zsdh,cldj,clsl,TotKgs " +
                "FROM CLZL " +
                "WHERE cldh LIKE 'V%'";
        Map<String, Object> map = new HashMap<>();

        // LYS_ERP
        // List<CLZL> list = lyserpJdbcTemplate.query(sql, map, new CLZLRowMapper());
        // LIY_DD
        List<CLZL> list = liyddJdbcTemplate.query(sql, map, new CLZLRowMapper());
        // LBY_DD
        // List<CLZL> list = lbyddJdbcTemplate.query(sql, map, new CLZLRowMapper());

        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }
}
