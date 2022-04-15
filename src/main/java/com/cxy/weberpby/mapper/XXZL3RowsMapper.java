package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.XXZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体基本資料(只取 XieXing & SheHao)
 *
 */

public class XXZL3RowsMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        XXZL xxzl2rows = new XXZL();
        xxzl2rows.setXieXing(rs.getString("XieXing"));
        xxzl2rows.setSheHao(rs.getString("SheHao"));
        xxzl2rows.setARTICLE(rs.getString("ARTICLE"));

        return xxzl2rows;
    }
}
