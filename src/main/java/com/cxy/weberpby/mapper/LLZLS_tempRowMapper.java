package com.cxy.weberpby.mapper;

import com.cxy.weberpby.model.LLZLS_temp;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/6/8
 * @Description 大車領料暫存資料
 */
public class LLZLS_tempRowMapper implements RowMapper<LLZLS_temp> {
    @Override
    public LLZLS_temp mapRow(ResultSet rs, int rowNum) throws SQLException {
        LLZLS_temp llzls_temp = new LLZLS_temp();
        llzls_temp.setLLBH(rs.getString("LLBH"));
        llzls_temp.setPGDATE1(rs.getString("PGDATE1"));
        llzls_temp.setPGDATE2(rs.getString("PGDATE2"));
        llzls_temp.setPGDATE3(rs.getString("PGDATE3"));
        llzls_temp.setPGDATE4(rs.getString("PGDATE4"));
        llzls_temp.setPGDATE5(rs.getString("PGDATE5"));
        return llzls_temp;
    }
}
