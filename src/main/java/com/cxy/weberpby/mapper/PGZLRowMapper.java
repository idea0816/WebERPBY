package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.PGZL;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/25
 * @Description 派工資料表頭
 */
public class PGZLRowMapper implements RowMapper<PGZL> {
    @Override
    public PGZL mapRow(ResultSet rs, int rowNum) throws SQLException {
        PGZL pgzl = new PGZL();
        pgzl.setPGDATE(rs.getString("PGDATE"));  // 派工編號
        pgzl.setCQDH(rs.getString("CQDH"));    // 廠區
        pgzl.setDATE1(rs.getString("DATE1"));   // 派工日期
        pgzl.setBZ(rs.getString("BZ"));  // 備註(替代成單位：ex~WML(萬馬力)
        pgzl.setCFM(rs.getString("CFM")); // CFM
        pgzl.setUSERID(rs.getString("USERID"));
        pgzl.setUSERDATE(rs.getString("USERDATE"));
        return pgzl;
    }
}
