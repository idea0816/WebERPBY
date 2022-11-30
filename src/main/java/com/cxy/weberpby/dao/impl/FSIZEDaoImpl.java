package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.FSIZEDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/5/16
 * @Description size資料
 */

@Component
public class FSIZEDaoImpl implements FSIZEDao {
    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // Get US Size
    @Override
    public List<String> getUsSize() {
        String sqlgetUsSize = "SELECT size FROM FSIZES " +
                "WHERE bh = 'US' AND size BETWEEN '02.0' AND '18.0' ORDER BY size ";

        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("size");

        return lbyddJdbcTemplate.query(sqlgetUsSize, map, rowMapper);
    }
}
