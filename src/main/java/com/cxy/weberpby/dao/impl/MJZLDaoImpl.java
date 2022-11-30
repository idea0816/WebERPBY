package com.cxy.weberpby.dao.impl;

import com.cxy.weberpby.dao.MJZLDao;
import com.cxy.weberpby.dto.MJZL2RowsMapper;
import com.cxy.weberpby.model.MJZL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/5/4
 * @Description 模具明细资料
 *
 * List<String> getErpDD();   // 取得鞋廠大底模具資料
 * List<String> getErpDM();   // 取得鞋廠刀模模具資料
 * List<MJZL> getML();  // 取得模具资料(mjbh, lbdh)
 */

@Component
public class MJZLDaoImpl implements MJZLDao {

    // LBY_DD
    @Autowired
    @Qualifier("lbyddJdbcTemplate")
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 取得鞋廠大底模具資料
    @Override
    public List<String> getErpDD() {
        String sqlgetErpMJ = "SELECT DDMH FROM LBY_ERP.dbo.xxzl GROUP BY DDMH";

        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("DDMH");

        return lbyddJdbcTemplate.query(sqlgetErpMJ, map, rowMapper);
    }

    // 取得鞋廠刀模模具資料
    @Override
    public List<String> getErpDM() {
        String sqlgetErpDM = "SELECT DAOMH FROM LBY_ERP.dbo.xxzl GROUP BY DAOMH";

        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("DAOMH");

        return lbyddJdbcTemplate.query(sqlgetErpDM, map, rowMapper);
    }

    // 取得模具资料(mjbh, lbdh)
    @Override
    public List<MJZL> getML() {
        String sqlgetML = "SELECT mjbh, lbdh " +
                "FROM MJZL " +
                "WHERE lbdh LIKE '9%'";
        map = new HashMap<>();
        List<MJZL> getMLList = lbyddJdbcTemplate.query(sqlgetML, map, new MJZL2RowsMapper());

        if (getMLList.size() > 0) {
            return getMLList;
        } else {
            return null;
        }
    }
}
