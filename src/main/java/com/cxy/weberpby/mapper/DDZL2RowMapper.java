package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.DDZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/9
 * @Description 訂單部份資料
 */
public class DDZL2RowMapper implements RowMapper<DDZL> {
    @Override
    public DDZL mapRow(ResultSet rs, int rowNum) throws SQLException {
        DDZL DDZL = new DDZL();
        DDZL.setDDBH(rs.getString("DDBH"));    // 訂單編號
        DDZL.setXieXing(rs.getString("XieXing")); // 鞋型
        DDZL.setSheHao(rs.getString("SheHao"));  // 色號
        DDZL.setARTICLE(rs.getString("ARTICLE")); // ARTICLE
        DDZL.setDDRQ(rs.getString("DDRQ"));    // 接單日期
        DDZL.setSCRQ(rs.getString("SCRQ"));    // 生產上線日
        DDZL.setDDJQ(rs.getString("DDJQ"));    // 訂單交期
        DDZL.setPairs(rs.getInt("Pairs"));  // 雙數
        DDZL.setUSERID(rs.getString("USERID"));
        DDZL.setUSERDATE(rs.getString("USERDATE"));

        return DDZL;
    }
}
