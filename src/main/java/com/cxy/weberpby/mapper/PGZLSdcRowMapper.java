package com.cxy.weberpby.mapper;


import com.cxy.weberpby.model.PGZLSdc;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/5/28
 * @Description PGZLSdc - 大車派工輪次 & Size & 雙數
 */
public class PGZLSdcRowMapper implements RowMapper<PGZLSdc> {
    @Override
    public PGZLSdc mapRow(ResultSet rs, int rowNum) throws SQLException {
        PGZLSdc pgzlSdc = new PGZLSdc();
        pgzlSdc.setPGDATE(rs.getString("PGDATE"));  // 派工單號
        pgzlSdc.setCQDH(rs.getString("CQDH"));
        pgzlSdc.setCldh(rs.getString("cldh"));    // 訂單號碼
        pgzlSdc.setLc(rs.getString("lc"));  // 輪次
        pgzlSdc.setXc(rs.getString("xc"));  // 尺寸
        pgzlSdc.setQty(rs.getString("qty")); // 雙數
        pgzlSdc.setBb(rs.getString("bb"));  // 班別
        pgzlSdc.setZb(rs.getString("zb"));  // 鞋廠組別
        pgzlSdc.setTime(rs.getString("time"));    // 時間段
        return pgzlSdc;
    }


}
