package com.cxy.weberpby.dto;

import com.cxy.weberpby.model.XXZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time:2022年2月16日
 * @Description 型体基本資料(XieXing & SheHao & ARTICLE)
 */

public class XXZL3RowsMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        XXZL xxzl3rows = new XXZL();
        xxzl3rows.setXieXing(rs.getString("XieXing"));
        xxzl3rows.setSheHao(rs.getString("SheHao"));
        xxzl3rows.setARTICLE(rs.getString("ARTICLE"));

        return xxzl3rows;
    }
}
