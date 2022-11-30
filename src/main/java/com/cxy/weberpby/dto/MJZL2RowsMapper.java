package com.cxy.weberpby.dto;

import com.cxy.weberpby.model.MJZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/4
 * @Description 取得模具资料(mjbh, lbdh)
 */
public class MJZL2RowsMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        MJZL ml = new MJZL();
        ml.setMjbh(rs.getString("mjbh"));
        ml.setLbdh(rs.getString("lbdh"));

        return ml;
    }
}
