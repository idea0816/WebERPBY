package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.XXZLS;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/18
 * @Description 底厂型体基本資料 - 部位 & 配方
 */
public class XXZLSRowMapper implements RowMapper<XXZLS> {
    @Override
    public XXZLS mapRow(ResultSet rs, int rowNum) throws SQLException {
        XXZLS xxzls = new XXZLS();
        xxzls.setXieXing(rs.getString("XieXing")); // 鞋型
        xxzls.setXieXing(rs.getString("SheHao"));  // 色號
        xxzls.setCQDH(rs.getString("CQDH"));
        xxzls.setXh(rs.getString("xh"));  // 序號A~O
        xxzls.setCldh(rs.getString("cldh"));    // 配方代號
        xxzls.setYSSM(rs.getString("YSSM"));    // 部位說明
        xxzls.setUSERID(rs.getString("USERID"));
        xxzls.setUSERDATE(rs.getString("USERDATE"));
        xxzls.setLB(rs.getString("LB"));  // 类别
        xxzls.setHZS(rs.getDouble("HZS")); // 迴轉數
        xxzls.setDZ(rs.getDouble("DZ"));
        xxzls.setRS(rs.getDouble("RS"));

        return xxzls;
    }


}
