package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.PGZLS1;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/26
 * @Description PGZLS1 - 原料用量(右窗格)
 */
public class PGZLS1RowMapper implements RowMapper<PGZLS1> {
    @Override
    public PGZLS1 mapRow(ResultSet rs, int rowNum) throws SQLException {
        PGZLS1 pgzls1 = new PGZLS1();
        pgzls1.setPGDATE(rs.getString("PGDATE"));   // 派工編號
        pgzls1.setCQDH(rs.getString("CQDH"));    // 廠區
        pgzls1.setCldh(rs.getString("cldh"));    // 材料代號
        pgzls1.setClyl(rs.getDouble("clyl"));    // 用量
        pgzls1.setClll(rs.getDouble("clll"));    // 超領數量
        pgzls1.setUSERID(rs.getString("USERID"));
        pgzls1.setUSERDATE(rs.getString("USERDATE"));
        return pgzls1;
    }
}
